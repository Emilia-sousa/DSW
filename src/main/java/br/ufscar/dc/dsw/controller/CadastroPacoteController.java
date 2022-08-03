package br.ufscar.dc.dsw.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Destino;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.Util;


import org.apache.commons.fileupload.servlet.ServletFileUpload;

import static br.ufscar.dc.dsw.util.Constants.*;

@WebServlet(urlPatterns = { "/cadastroPacoteController" })
@MultipartConfig
public class CadastroPacoteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String pais = request.getParameter("pais");
            Timestamp dataPartida = Util.convertStringToTimestamp(request.getParameter("data-partida"));
            String duracaoDiasParameter = request.getParameter("duracao-dias");
            Integer duracaoDias = Integer.parseInt(duracaoDiasParameter == null || duracaoDiasParameter.isEmpty() ? "0" : duracaoDiasParameter);
            String valorParameter = request.getParameter("valor");
            Float valor = Float.parseFloat(valorParameter == null || valorParameter.isEmpty() ? "0" : valorParameter);
            Part descricao = request.getPart("descricao");
            List<Part> fileParts = request.getParts().stream().filter(part -> "fotos".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
             

            String descricaoPath = null;

            List<Foto> fotos = new ArrayList<Foto>();

            if (ServletFileUpload.isMultipartContent(request)) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
    
                try {
                    descricaoPath = uploadPath + File.separator + descricao.getSubmittedFileName();
                    descricao.write(descricaoPath);

                    for (Part filePart : fileParts) {
                        String fotoPath = uploadPath + File.separator + filePart.getSubmittedFileName();
                        filePart.write(fotoPath);
                        fotos.add(new Foto(UPLOAD_DIRECTORY + "/" + filePart.getSubmittedFileName()));
                    }
                } catch (Exception ex) {
                    erros.add("Erro no upload: " + ex.getMessage());
                    request.setAttribute("mensagens", erros);
                    RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
            }
            

            if (!erros.isExisteErros()) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
                AgenciaDAO adao = new AgenciaDAO();
                Agencia agencia = adao.getById(usuario.getId());
                Destino destino = new Destino(cidade, estado, pais);
                PacoteTuristicoDAO dao = new PacoteTuristicoDAO();
                PacoteTuristico pacoteTuristico = new PacoteTuristico(agencia, destino, dataPartida, duracaoDias, valor, UPLOAD_DIRECTORY + "/" + descricao.getSubmittedFileName(), fotos.size(), fotos);
        
                dao.insert(pacoteTuristico);

                response.sendRedirect("perfil");
            } else {
                request.setAttribute("mensagens", erros);

                
                request.setAttribute("cidade", cidade);
                request.setAttribute("estado", estado);
                request.setAttribute("pais", pais);
                if (dataPartida != null) {
                    request.setAttribute("dataPartida", request.getParameter("data-partida"));
                }
                request.setAttribute("duracaoDias", duracaoDias);
                request.setAttribute("valor", valor);
                request.setAttribute("descricao", descricao);

                RequestDispatcher rd = request.getRequestDispatcher("/adicionarPacote.jsp");
                rd.forward(request, response);
            }
        }
    }
}
