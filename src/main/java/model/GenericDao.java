package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
}
