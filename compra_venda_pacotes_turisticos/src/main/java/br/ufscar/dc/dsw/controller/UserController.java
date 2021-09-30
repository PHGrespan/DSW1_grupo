package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/usuarios/*")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ClienteDAO dao;

	@Override
	public void init() {
		dao = new ClienteDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente usuario = (Cliente) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getIsAdm().equalsIgnoreCase("ADM")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}
		
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
			case "/cadastro":
				apresentaFormCadastro(request, response);
				break;
			case "/insercao":
				insere(request, response);
				break;
			case "/remocao":
				remove(request, response);
				break;
			case "/edicao":
				apresentaFormEdicao(request, response);
				break;
			case "/atualizacao":
				atualize(request, response);
				break;
			default:
				lista(request, response);
				break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> listaUsuarios = dao.getAll();
		request.setAttribute("listaUsuarios", listaUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		Cliente usuario = dao.getByCpf(cpf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuario/formulario.jsp");
		request.setAttribute("usuario", usuario);
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		String isAdm = request.getParameter("isAdm");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		String data = request.getParameter("dataNasc");
		
		Cliente usuario = new Cliente(email, senha, cpf, isAdm, nome, telefone, sexo, data);

		dao.insert(usuario);
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
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		String dataNasc = request.getParameter("data");
		
		Cliente cliente = new Cliente(email, senha, cpf, isAdm, nome, telefone, sexo, dataNasc);

		dao.update(cliente);
		response.sendRedirect("lista");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cpf = request.getParameter("cpf");
		dao.delete(dao.getByCpf(cpf));
		response.sendRedirect("lista");
	}
}