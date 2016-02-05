package DAO;

import modelo.Comentario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import excecao.FalhaNoBanco;

public class ComentarioDAO {
	
	private Connection connection;
	private UsuarioDAO usuarioDAO;

	public ComentarioDAO(Connection conexao){
		this.connection = conexao;
		this.usuarioDAO = new UsuarioDAO(conexao);
	}
	
	public void cadastrar(Comentario comentario) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = connection.prepareStatement("insert into comentario(id_noticia, conteudo, id_usuario) values (?, ?, ?)");
			pstm.setLong(1, comentario.getIdNoticia());
			pstm.setString(2, comentario.getConteudo());
			pstm.setLong(3, comentario.getUsuario().getId());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}	
	}
	
	public List<Comentario> obterTodos(long idNoticia)throws FalhaNoBanco{
		List<Comentario> all = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt =	connection.prepareStatement("Select * from comentario where id_noticia=?");
			stmt.setLong(1, idNoticia);
			rs = stmt.executeQuery();
			while(rs.next()){
				Comentario comentario = new Comentario();
				comentario.setId(rs.getInt("id"));
				comentario.setConteudo(rs.getString("conteudo"));
				comentario.setIdNoticia(rs.getLong("id_noticia"));
				
				comentario.setUsuario(usuarioDAO.pegarPorId(rs.getLong("id_usuario")));
								
				all.add(comentario);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
		return all;
	}
	
}
