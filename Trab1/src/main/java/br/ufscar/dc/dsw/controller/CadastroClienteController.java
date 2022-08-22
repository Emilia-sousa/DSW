package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Util;

@WebServlet(urlPatterns = { "/cadastroClienteController" })
public class CadastroClienteController extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("bOK") != null) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            Timestamp dataNascimento = Util.convertStringToTimestamp(request.getParameter("data-nascimento"));
            String senha = request.getParameter("senha");

            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = new Cliente(nome, email, senha, "Cliente", cpf, telefone, sexo, dataNascimento);

            UsuarioDAO usuarioDao = new UsuarioDAO();
            
            dao.insert(cliente);

            Long idCliente = usuarioDao.getIdByEmail(email);
            cliente.setId(idCliente);

            request.getSession().setAttribute("usuarioLogado", cliente);
            response.sendRedirect("index.jsp");
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
