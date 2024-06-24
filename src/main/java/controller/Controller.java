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
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/listar", "/salvar", "/editar", "/atualizar", "/remover"})
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
		else if(action.equals("/editar")) {
			try {
				editarContato(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("/atualizar")) {
			atualizarContato(request, response);
		}
		else if(action.equals("/remover")) {
			removerContato(request, response);
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
	
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String idContato = request.getParameter("id");
		Long id = Long.valueOf(idContato);
		
		GenericDao dao = new GenericDao();
		Agenda contato = dao.findContatoById(id);
				
		request.setAttribute("contato", contato);
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		
		rd.forward(request, response);
	}
	
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Agenda novoContato = new Agenda();
		
		novoContato.setId(Long.valueOf(request.getParameter("id")));
		novoContato.setNome(request.getParameter("nome"));
		novoContato.setTelefone(request.getParameter("telefone"));
		novoContato.setEmail(request.getParameter("email"));
		
		GenericDao dao = new GenericDao();
		dao.atualizarContato(novoContato);
		
		listar(request, response);
	}
	
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		System.out.println("ID "+id);
		GenericDao dao = new GenericDao();
		dao.removerContato(id);
		
		listar(request, response);
	}

}
