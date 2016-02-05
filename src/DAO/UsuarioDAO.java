package DAO;

import modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO(){
		this.connection = new FabricaConexao().getConnection();
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
		
	public Usuario pegarPorId(long idUsuario){
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
				return usuario;
			}
			
			return null;
		} catch (SQLException e) {
			//throw new ErroNoDao();
		} 
		return null;
	}
	
}
