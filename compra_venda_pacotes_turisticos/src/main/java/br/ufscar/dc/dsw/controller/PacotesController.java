package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.PacoteTuristico;

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
		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/filtrar":
                    listaFiltrada(request, response);
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
        request.setCharacterEncoding("UTF-8");
		List<PacoteTuristico> listaPacotes = null;
        listaPacotes = dao.getAllDisponiveis();
		request.setAttribute("listaPacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_pacotes.jsp");
		dispatcher.forward(request, response);
	}

    private void listaFiltrada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		List<PacoteTuristico> listaPacotes = null;
        String tipo = request.getParameter("tipo");
        String filtro = request.getParameter("filtro");
        switch (tipo) {
            case "agencia":
                listaPacotes = dao.getAllByCnpj(filtro);
            break;
            case "destinos":
                listaPacotes = dao.getAllByDestinos(filtro);
            break;
            case "data":
                listaPacotes = dao.getAllByData(filtro);
            break;
        }
		request.setAttribute("listaPacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_pacotes.jsp");
		dispatcher.forward(request, response);
	}

}
    