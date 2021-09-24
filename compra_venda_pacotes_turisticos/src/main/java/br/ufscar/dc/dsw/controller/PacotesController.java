package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pacotes/*")
public class PacotesController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PacoteTuristicoDAO dao;

    @Override
    public void init() {
        dao = new PacoteTuristicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PacoteTuristico pacote = (PacoteTuristico) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (pacote == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} 
		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                default:
                    lista(request, response);
                    break;
                
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PacoteTuristico> listaPacotes = dao.getAll();
		request.setAttribute("listapacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacotes/lista.jsp");
		dispatcher.forward(request, response);
	}
}
    