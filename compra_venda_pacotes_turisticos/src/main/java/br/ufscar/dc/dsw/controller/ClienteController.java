package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.CompraDAO;
import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO dao;
    private PacoteTuristicoDAO dao_pacotes;
    private CompraDAO dao_compra;

    @Override
    public void init() {
        dao = new ClienteDAO();
        dao_pacotes = new PacoteTuristicoDAO();
        dao_compra = new CompraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (cliente == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} 
		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                // case "/cadastro":
                //     apresentaFormCadastro(request, response);
                //     break;
                case "/compra":
                	compraPacote(request, response);
                    break;
                //case "/remocao":
                //    removePacote(request, response);
                //    break;
                // case "/edicao":
                //     apresentaFormEdicao(request, response);
                //     break;
                case "/edicao":
                	apresentaFormEdicaoUser(request, response);
                	break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                	listaPacotes(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaPacotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");        
    	List<Compra> listaPacotes = dao_compra.getAllbyCPF(cliente.getCpf());
        request.setAttribute("listaPacotes", listaPacotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormEdicaoUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		Cliente usuario = dao.getByCpf(cpf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/formulario.jsp");
		request.setAttribute("usuario", usuario);
		dispatcher.forward(request, response);
	}

    // private Map<Long, String> getUsers() {
    //     Map<Long, String> usuarios = new HashMap<>();
    //     for (User usuario : new UserDAO().getAll()) {
    //         usuarios.put(usuario.getId(), usuario.getEmail());
    //     }
    //     return usuarios;
    // }

    // private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     request.setAttribute("usuarios", getUsers());
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
    //     dispatcher.forward(request, response);
    // }

    // private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     String cnpj = request.getParameter("cnpj");
    //     AgenciaTurismo agencia = dao.getByCnpj(cnpj);
    //     request.setAttribute("agencia", agencia);
    //     request.setAttribute("usuarios", getUsers());
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
    //     dispatcher.forward(request, response);
    // }

    private void compraPacote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");
        /*String email = cliente.getEmail();
        String senha = cliente.getSenha();
        String cpf = cliente.getCpf();
        String isAdm = cliente.getIsAdm();
        String nome = cliente.getNome();
        String tel = cliente.getTelefone();
        String sexo = cliente.getSexo();
        String dataNasc = cliente.getDataNasc();*/
        
        String nome = request.getParameter("nome");
        
        PacoteTuristico pacote = dao_pacotes.getByNome(nome);

        Compra compra = new Compra(cliente, pacote);
        dao_compra.insert(compra);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String isAdm = request.getParameter("isAdm");
        String nome = request.getParameter("nome");
        String tel = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNasc = request.getParameter("dataNasc");
        
        
        Cliente cliente = new Cliente(email, senha, cpf, isAdm, nome, tel, sexo, dataNasc);
        dao.update(cliente);
        response.sendRedirect("lista");
    }
}