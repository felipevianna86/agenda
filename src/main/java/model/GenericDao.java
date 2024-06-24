package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenericDao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	private String user = "root";
	private String password = "root";
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void salvarContato(Agenda agenda) {
		String create = "INSERT INTO agenda(nome, telefone, email) VALUES (?,?,?)";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(create);
			preparedStatement.setString(1, agenda.getNome());
			preparedStatement.setString(2, agenda.getTelefone());
			preparedStatement.setString(3, agenda.getEmail());
			
			preparedStatement.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Agenda> listarContatos(){
		
		String read = "SELECT * FROM agenda ORDER BY nome";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(read);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			List<Agenda> contatos = new ArrayList<>();
			
			while(rs.next()) {
				Agenda agenda = new Agenda();
				agenda.setId(rs.getLong("id"));
				agenda.setNome(rs.getString("nome"));
				agenda.setTelefone(rs.getString("telefone"));
				agenda.setEmail(rs.getString("email"));
				
				contatos.add(agenda);
			}
			
			System.out.println("Contatos "+contatos);
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
