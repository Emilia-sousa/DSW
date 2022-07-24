package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.Validator;


@WebServlet(name = "login", urlPatterns = { "/loginController" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			erros = new Validator<String>("Email", email).required().addErro(erros);
			erros = new Validator<String>("Senha", senha).required().addErro(erros);

			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getByEmail(email);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
						response.sendRedirect("index.jsp");
						return;
					} else {
						erros.add("Senha inválida!");
						request.setAttribute("email", email);
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
