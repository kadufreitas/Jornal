package DAO;

import modelo.Usuario;
import java.sql.*;
import excecao.FalhaNoBanco;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO(Connection conexao){
		this.connection = conexao;
	}
	
	public void cadastrar(Usuario usuario){
		
		 String sql = "insert into usuario " +
				  "(nome,email,senha)" +
				  " values (?,?,?)";
				 
				  try {
				  PreparedStatement stmt = connection.prepareStatement(sql);
				 
				  // seta os valores
				  stmt.setString(1,usuario.getNome());
				  stmt.setString(2,usuario.getEmail());
				  stmt.setString(3,usuario.getSenha());
				  
				  // executa
				  stmt.execute();
				  stmt.close();
				  } catch (SQLException e) {
					  throw new RuntimeException(e);
				  }
	}
		
	public Usuario pegarPorId(long idUsuario) throws FalhaNoBanco{
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt = connection.prepareStatement("Select * from admin where id=?");
			stmt.setLong(1, idUsuario);
			rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			
			if(rs.first()){
				usuario.setEmail(rs.getString("email"));
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setTipo(rs.getInt("tipo"));
				return usuario;
			}
			
			return null;
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public Usuario ObterPorEmail(String email) throws FalhaNoBanco{
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt = connection.prepareStatement("Select * from usuario where email=?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			
			if(rs.first()){
				usuario.setEmail(rs.getString("email"));
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setTipo(rs.getInt("tipo"));
				
				return usuario;
			}
			
			return null;
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		} 
	}
	
}
