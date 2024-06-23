package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Agenda;

import java.io.IOException;

@WebServlet(urlPatterns = {"/listar", "/salvar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/listar")) {
			listar(request, response);
		}
		else if(action.equals("/salvar")) {
			salvarContato(request, response);
		}
		else {
			response.sendRedirect("agenda.jsp");
		}
				
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("agenda.jsp");
	}
	
	protected void salvarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Agenda novoContato = new Agenda();
		
		novoContato.setNome(request.getParameter("nome"));
		novoContato.setTelefone(request.getParameter("telefone"));
		novoContato.setEmail(request.getParameter("email"));
		
		
		response.sendRedirect("agenda.jsp");
	}

}
