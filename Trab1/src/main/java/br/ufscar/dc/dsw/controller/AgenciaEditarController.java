package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.dao.AgenciaDAO;


@WebServlet(urlPatterns = { "/EditarAgencia" })
public class AgenciaEditarController extends HttpServlet{
    private static final long serialVersionUID = 1L;
	private AgenciaDAO dao;

	@Override
    public void init() {
        dao = new AgenciaDAO();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			doGet(request, response);
		}
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

			if (usuario == null) {
				response.sendRedirect("/JamesVacations/index.jsp");
			} else if (usuario.getTipo().equals("admin")){
			Long id = Long.parseLong(request.getParameter("id")); 

			Agencia agencia = dao.getById(id);
			request.setAttribute("agencia", agencia);

			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/FormularioEdicaoAgencia.jsp");
			dispatcher.forward(request, response);
			} else {
				response.sendRedirect("/JamesVacations/index.jsp");
			}
		}
	}