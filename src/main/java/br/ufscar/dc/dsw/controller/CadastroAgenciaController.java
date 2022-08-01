package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Agencia;

@WebServlet(urlPatterns = { "/cadastroAgenciaController" })
public class CadastroAgenciaController extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    if (request.getParameter("bOK") != null) {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        String senha = request.getParameter("senha");

        AgenciaDAO dao = new AgenciaDAO();
        Agencia agencia = new Agencia(nome, email, senha, "Agencia", cnpj, descricao);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();

        dao.insert(agencia);

        Long idAgencia = usuarioDao.getIdByEmail(email);
        agencia.setId(idAgencia);

        request.getSession().setAttribute("usuarioLogado", agencia);
        response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}