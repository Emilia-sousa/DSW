package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

//import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/perfil")
public class PerfilController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ClienteDAO cDAO;
	private AgenciaDAO aDAO;
	private PacoteTuristicoDAO pDAO;
    private UsuarioDAO uDAO;

	@Override
    public void init() {
		cDAO = new ClienteDAO();
		aDAO = new AgenciaDAO();
        pDAO = new PacoteTuristicoDAO();
        uDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	//Erro erros = new Erro();

    	
    	if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getTipo().equals("Admin")) {

            List<Cliente> listaCliente = cDAO.getAll();
            request.setAttribute("listaCliente", listaCliente);
            List<Agencia> listaAgencia = aDAO.getAll();
            request.setAttribute("listaAgencia", listaAgencia);

    		RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
            dispatcher.forward(request, response);
    	} else if(usuario.getTipo().equals("Cliente")){
            Cliente cliente = cDAO.getById(usuario.getId());
            List<PacoteTuristico> listaPacotes = pDAO.getByCliente(cliente);
            request.setAttribute("listaPacotes", listaPacotes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
            dispatcher.forward(request, response);     
    	}else if (usuario.getTipo().equals("Agencia")){
            Agencia agencia =  aDAO.getById(usuario.getId());
            String vigenteParam = request.getParameter("vigente");
            Boolean vigente = false;
            if (vigenteParam != null && !vigenteParam.isEmpty()) {
                vigente = vigenteParam.equals("1");
            }
            List<PacoteTuristico> listaPacotes = pDAO.getByAgencia(agencia, vigente);
            request.setAttribute("listaPacotes", listaPacotes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
            dispatcher.forward(request, response);
        }
    }
}
