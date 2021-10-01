package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.AgenciaTurismoDAO;
import br.ufscar.dc.dsw.dao.PacoteTuristicoDAO;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import java.util.Date;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/agencia/*")
public class AgenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AgenciaTurismoDAO dao;
    private PacoteTuristicoDAO dao_pacotes;
    

    @Override
    public void init() {
        dao = new AgenciaTurismoDAO();
        dao_pacotes = new PacoteTuristicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AgenciaTurismo agencia = (AgenciaTurismo) request.getSession().getAttribute("agenciaLogada");

		if (agencia == null) {
			response.sendRedirect(request.getContextPath());
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
                    inserePacote(request, response);
                    break;
                case "/remocao":
                    removePacote(request, response);
                    break;
                case "/edicao":
                     apresentaFormEdicao(request, response);
                     break;
                case "/atualizacao":
                    atualizePacote(request, response);
                    break;
                case "/filtrarPacotes":
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
    	AgenciaTurismo agencia = (AgenciaTurismo) request.getSession().getAttribute("agenciaLogada");
    	List<PacoteTuristico> listaPacotes = dao_pacotes.getAllByCnpj(agencia.getCnpj());
        request.setAttribute("listaPacotes", listaPacotes);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/lista.jsp");
        dispatcher.forward(request, response);
    }

    // private Map<Long, String> getUsers() {
    //     Map<Long, String> usuarios = new HashMap<>();
    //     for (User usuario : new UserDAO().getAll()) {
    //         usuarios.put(usuario.getId(), usuario.getEmail());
    //     }
    //     return usuarios;
    // }

     private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
         dispatcher.forward(request, response);
     }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         String nome = request.getParameter("nome");
         PacoteTuristico pacote = dao_pacotes.getByNome(nome);
         request.setAttribute("pacote", pacote);
         RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
         dispatcher.forward(request, response);
    }

    private void inserePacote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        String dataPartida = request.getParameter("dataPartida");
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        String destinos = request.getParameter("destinos");
        String fotos = request.getParameter("fotos");
        AgenciaTurismo agencia = (AgenciaTurismo) request.getSession().getAttribute("agenciaLogada");
        PacoteTuristico pacote = new PacoteTuristico(nome, agencia, dataPartida, duracao, valor, descricao, destinos, fotos);
        dao_pacotes.insert(pacote);
        response.sendRedirect("lista");
    }

    private void atualizePacote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String dataPartida = request.getParameter("dataPartida");
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        float valor = Float.parseFloat(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        String destinos = request.getParameter("destinos");
        String fotos = request.getParameter("fotos");
        AgenciaTurismo agencia = (AgenciaTurismo) request.getSession().getAttribute("agenciaLogada");
        
        
        PacoteTuristico pacote = new PacoteTuristico(nome, agencia, dataPartida, duracao, valor, descricao, destinos, fotos);
        dao_pacotes.update(pacote);
        response.sendRedirect("lista");
    }

    private void removePacote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");

        dao_pacotes.delete(dao_pacotes.getByNome(nome));
        response.sendRedirect("lista");
    }
    
    private void listaFiltrada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		List<PacoteTuristico> listaPacotes = null;
		try {
			listaPacotes = dao_pacotes.getAllCurrent();
			request.setAttribute("listaPacotes", listaPacotes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/lista.jsp");
	        dispatcher.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}
}