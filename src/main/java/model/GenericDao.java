package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "INSERT INTO agenda(nome, telefone, email) VALUES (?,?,?)";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
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
		
		String sql = "SELECT * FROM agenda ORDER BY nome";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
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
			
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}		
	}
	
	
	public Agenda findContatoById(Long id) throws SQLException {
		String sql = "SELECT * FROM agenda WHERE id = ? ";
		Connection con = conectar();
		try {			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Agenda agenda = new Agenda();
				agenda.setId(rs.getLong("id"));
				agenda.setNome(rs.getString("nome"));
				agenda.setTelefone(rs.getString("telefone"));
				agenda.setEmail(rs.getString("email"));
				
				return agenda;
			}			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		finally {
			con.close();
		}
		return null;
	}
	
	public void atualizarContato(Agenda agenda) {
		String sql = "UPDATE agenda SET nome = ?, telefone = ?, email = ? WHERE id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, agenda.getNome());
			preparedStatement.setString(2, agenda.getTelefone());
			preparedStatement.setString(3, agenda.getEmail());
			preparedStatement.setLong(4, agenda.getId());
			
			preparedStatement.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerContato(Long id) {
		String sql = "DELETE FROM agenda WHERE id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setLong(1, id);
			
			preparedStatement.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
