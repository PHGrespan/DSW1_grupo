package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/pacotes/*"})
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
    	request.getSession();
    	Erro erros = new Erro();
		
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
		request.setAttribute("listaPacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_pacotes.jsp");
		dispatcher.forward(request, response);
	}
}
    