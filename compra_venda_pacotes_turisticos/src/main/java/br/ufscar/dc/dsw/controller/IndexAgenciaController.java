package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AgenciaTurismoDAO;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index_agencia", urlPatterns = { "/log_agencia.jsp", "/logout_agencia.jsp" })
public class IndexAgenciaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		
		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				AgenciaTurismoDAO dao = new AgenciaTurismoDAO();
				AgenciaTurismo agencia = dao.getByEmail(login);
				if (agencia != null) {
					if (agencia.getSenha().equalsIgnoreCase(senha)) {
						request.getSession().setAttribute("agenciaLogada", agencia);
						response.sendRedirect("agencia/");
						
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String URL = "/login_agencia.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}