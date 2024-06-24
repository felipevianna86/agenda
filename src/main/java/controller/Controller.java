package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Agenda;
import model.GenericDao;

import java.io.IOException;
import java.util.List;

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
			listar(request, response);
		}
				
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GenericDao dao = new GenericDao();
		List<Agenda> contatos = dao.listarContatos();
		
		request.setAttribute("contatos", contatos);
		
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response);
	}
	
	protected void salvarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Agenda novoContato = new Agenda();
		
		novoContato.setNome(request.getParameter("nome"));
		novoContato.setTelefone(request.getParameter("telefone"));
		novoContato.setEmail(request.getParameter("email"));
		
		GenericDao dao = new GenericDao();
		dao.salvarContato(novoContato);
		
		listar(request, response);
	}

}
