package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.CompraDAO;
import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.util.Mail;

import java.io.IOException;
import java.util.List;
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
                case "/compra":
                	compraPacote(request, response);
                    break;
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

    private void compraPacote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogado");

        String nome = request.getParameter("nome");
        
        PacoteTuristico pacote = dao_pacotes.getByNome(nome);

        try {
            // Envia e-mail
            Mail email = new Mail();
            email.sendMail(cliente.getEmail());
        } catch (Exception e){
        }
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