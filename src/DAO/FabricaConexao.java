package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//alterar tabela
			return DriverManager.getConnection("jdbc:mysql://localhost/TrabalhoWEB","root","123");
		} catch (ClassNotFoundException|SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}
}