package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Util;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.dao.CompraDAO;
import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;

@WebServlet(urlPatterns = "/compra")
public class CompraController extends HttpServlet {
	private PacoteTuristicoDAO pDAO;
	private CompraDAO coDAO;

	@Override
    public void init() {
		pDAO = new PacoteTuristicoDAO();
        coDAO = new CompraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        PacoteTuristico pacote = pDAO.getById(id);
        request.setAttribute("pacote", pacote);

        String comprou = request.getParameter("comprou");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (usuario.getTipo().equals("Cliente")) {
            Cliente cliente = new Cliente(usuario.getId());

            Boolean clienteComprou = coDAO.verificaCompraClienteByIdPacote(cliente, id);

            request.setAttribute("jacomprou", clienteComprou);

            if (comprou != null && comprou.equals("1") && !clienteComprou) {
    
                Compra compra = new Compra(cliente, pacote);
                coDAO.insert(compra);



                request.setAttribute("jacomprou", true);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("pacote.jsp");
        dispatcher.forward(request, response);
    }
}
