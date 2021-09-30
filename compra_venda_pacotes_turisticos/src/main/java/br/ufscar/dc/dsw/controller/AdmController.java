package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AgenciaTurismoDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/adm/*")
public class AdmController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ClienteDAO clienteDao;
	private AgenciaTurismoDAO agenciaDao;

	@Override
	public void init() {
		clienteDao = new ClienteDAO();
		agenciaDao = new AgenciaTurismoDAO();
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
		} else if (!usuario.getIsAdm().equalsIgnoreCase("SIM")) {
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
			case "/usuario/cadastro":
				apresentaFormCadastroUser(request, response);
				break;
			case "/usuario/insercao":
				insereUser(request, response);
				break;
			case "/usuario/remocao":
				removeUser(request, response);
				break;
			case "/usuario/edicao":
				apresentaFormEdicaoUser(request, response);
				break;
			case "/usuario/atualizacao":
				atualizeUser(request, response);
				break;
				case "/agencia/cadastro":
				apresentaFormCadastroAgencia(request, response);
				break;
			case "/agencia/insercao":
				insereAgencia(request, response);
				break;
			case "/agencia/remocao":
				removeAgencia(request, response);
				break;
			case "/agencia/edicao":
				apresentaFormEdicaoAgencia(request, response);
				break;
			case "/agencia/atualizacao":
				atualizeAgencia(request, response);
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
		List<Cliente> listaUsuarios = clienteDao.getAll();
		request.setAttribute("listaUsuarios", listaUsuarios);
		List<AgenciaTurismo> listaAgencias = agenciaDao.getAll();
		request.setAttribute("listaAgencias", listaAgencias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/administrador/lista.jsp");
		dispatcher.forward(request, response);
	}

	/*
	*  
	* CLIENTE 
	* 
	*/

	private void apresentaFormCadastroUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/administrador/usuario/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicaoUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		Cliente usuario = clienteDao.getByCpf(cpf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/administrador/usuario/formulario.jsp");
		request.setAttribute("usuario", usuario);
		dispatcher.forward(request, response);
	}

	private void insereUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		clienteDao.insert(usuario);
		response.sendRedirect("lista");
	}

	private void atualizeUser(HttpServletRequest request, HttpServletResponse response)
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

		clienteDao.update(cliente);
		response.sendRedirect("lista");
	}

	private void removeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cpf = request.getParameter("cpf");
		clienteDao.delete(clienteDao.getByCpf(cpf));
		response.sendRedirect("lista");
	}

	/*
	*  
	* AGENCIA 
	* 
	*/
	private void apresentaFormCadastroAgencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/administrador/agencia/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicaoAgencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cnpj = request.getParameter("cnpj");
		AgenciaTurismo agencia = agenciaDao.getByCnpj(cnpj);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/administrador/agencia/formulario.jsp");
		request.setAttribute("agencia", agencia);
		dispatcher.forward(request, response);
	}

	private void insereAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		
		AgenciaTurismo agencia = new AgenciaTurismo(email, senha, cnpj, nome, descricao);

		agenciaDao.insert(agencia);
		response.sendRedirect("lista");
	}

	private void atualizeAgencia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		
		AgenciaTurismo agencia = new AgenciaTurismo(email, senha, cnpj, nome, descricao);

		agenciaDao.update(agencia);
		response.sendRedirect("lista");
	}

	private void removeAgencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnpj = request.getParameter("cnpj");
		agenciaDao.delete(agenciaDao.getByCnpj(cnpj));
		response.sendRedirect("lista");
	}
}