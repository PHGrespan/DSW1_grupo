package br.ufscar.dc.dsw.controller;


import br.ufscar.dc.dsw.dao.AgenciaTurismoDAO;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.domain.AgenciaTurismo;
import br.ufscar.dc.dsw.domain.User;
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

@WebServlet(urlPatterns = "/agencia/*")
public class AgenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AgenciaTurismoDAO dao;

    @Override
    public void init() {
        dao = new AgenciaTurismoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AgenciaTurismo agencia = (AgenciaTurismo) request.getSession().getAttribute("agenciaLogada");
		Erro erros = new Erro();

		if (agencia == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!agencia.getIsAdm().equalsIgnoreCase("ADM")) {
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
        List<AgenciaTurismo> listaAgencia = dao.getAll();
        request.setAttribute("listaAgencia", listaAgencia);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getUsers() {
        Map<Long, String> usuarios = new HashMap<>();
        for (User usuario : new UserDAO().getAll()) {
            usuarios.put(usuario.getId(), usuario.getEmail());
        }
        return usuarios;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("usuarios", getUsers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        AgenciaTurismo agencia = dao.get(id);
        request.setAttribute("agencia", agencia);
        request.setAttribute("usuarios", getUsers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String isAdm = request.getParameter("isAdm");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String desc = request.getParameter("descricao");
        
        AgenciaTurismo agencia = new AgenciaTurismo(email, senha, isAdm, cnpj, nome, desc);
        dao.insert(agencia);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String isAdm = request.getParameter("isAdm");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String desc = request.getParameter("descricao");
        
        
        AgenciaTurismo agencia = new AgenciaTurismo(id, email, senha, isAdm, cnpj, nome, desc);
        dao.update(agencia);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        AgenciaTurismo agencia = new AgenciaTurismo(id);
        dao.delete(agencia);
        response.sendRedirect("lista");
    }
}