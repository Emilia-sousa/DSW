package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.dao.AgenciaDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AgenciaDAO adao;
    private ClienteDAO cdao;
    private UsuarioDAO udao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init() {
        adao = new AgenciaDAO();
        cdao = new ClienteDAO();
        udao = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

    	
    	if (usuario == null) {
            response.sendRedirect(request.getContextPath());
    	} else if (usuario.getTipo().equals("Admin")) {

            switch (action) {
                case "/remocaoUsuario":
                    removeUsuario(request, response);
                    break;
                case "/edicaoUsuario":
                    editaUsuario(request, response);
                    break;
                case "/updateCliente":
                    updateCliente(request, response);
                    break;
                case "/updateAgencia":
                    updateAgencia(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }

    	} else{
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
            erros.add(usuario.getTipo());
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaUsuarios = udao.getAll();
        request.setAttribute("listaUsuarios", listaUsuarios);
        response.sendRedirect("../perfil");
    }

    private void removeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Erro erros = new Erro();
        Long id = Long.parseLong(request.getParameter("id"));
        
        /*
        erros.add("usuario" + id + " nao encontrado");
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        rd.forward(request, response);
        */
           
        Usuario usuario = udao.getById(id);
        if (usuario.getTipo().equals("Cliente")) {
            Cliente cliente = cdao.getById(usuario.getId());
            cdao.delete(cliente);
        }
        else if (usuario.getTipo().equals("Agencia")) {
            Agencia agencia = adao.getById(usuario.getId());
            adao.delete(agencia);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista");
        dispatcher.forward(request, response);
    }

    private void editaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = udao.getById(id);
        if (usuario.getTipo().equals("Cliente")) {
            Cliente cliente = cdao.getById(id);

            request.setAttribute("cliente", cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/FormularioEdicaoCliente.jsp");
            dispatcher.forward(request, response);
        }
        else if (usuario.getTipo().equals("Agencia")) {
            Agencia agencia = adao.getById(id);

            request.setAttribute("agencia", agencia);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/FormularioEdicaoAgencia.jsp");
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista");
        dispatcher.forward(request, response);

    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        Erro erros = new Erro();
        
       
        Long id = Long.parseLong(request.getParameter("id")); 
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone"); 
        String sexo = request.getParameter("sexo"); 
        String dataNascimentoParam = request.getParameter("data-nascimento");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmar-senha");
    
        Timestamp dataNascimento = null;
    
        if (dataNascimentoParam != null && !dataNascimentoParam.isEmpty()) {
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dataNascimentoParam);
                dataNascimento = new Timestamp(date.getTime());
            } catch (java.text.ParseException e) {
                erros.add(e.toString());
                request.setAttribute("mensagens", erros);
                RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
                rd.forward(request, response);
            }
        }

           
            /*
            erros.add("nome " + nome);
            erros.add("email " + email);
            erros.add("cpf " + cpf);
            erros.add("telefone " + telefone);
            erros.add("sexo " + sexo);
            erros.add("dataNascimento " + dataNascimento);
            erros.add("senha " + senha);
            */
        
        Cliente cliente = new Cliente(id, nome, email, senha, "Cliente", cpf, telefone, sexo, dataNascimento);
        cdao.update(cliente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista");
        dispatcher.forward(request, response);

    }

    private void updateAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Erro erros = new Erro();
        erros.add("teste");
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        rd.forward(request, response);
        */
        
        Long id = Long.parseLong(request.getParameter("id")); 
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj");
        String descricao = request.getParameter("descricao");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmar-senha");
        

            /*
            erros.add("nome " + nome);
            erros.add("email " + email);
            erros.add("cpf " + cpf);
            erros.add("telefone " + telefone);
            erros.add("sexo " + sexo);
            erros.add("dataNascimento " + dataNascimento);
            erros.add("senha " + senha);
            */

        Agencia agencia = new Agencia(id, nome, email, senha, "Agencia", cnpj, descricao);
        adao.update(agencia);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista");
        dispatcher.forward(request, response);
        
    }

    
}
