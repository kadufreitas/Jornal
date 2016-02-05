package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excecao.FalhaNoBanco;
import modelo.Classificado;
import modelo.Noticia;

public class NoticiaDAO {
	private Connection conexao;
	private ComentarioDAO comentarioDAO;
	
	public NoticiaDAO(Connection conexao){
		this.conexao = conexao;
		this.comentarioDAO = new ComentarioDAO(conexao);
	}
	
	public void cadastrar(Noticia noticia) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = conexao.prepareStatement("insert into noticia(conteudo, id_categoria) values (?, ?)");
			pstm.setString(1, noticia.getConteudo());
			pstm.setLong(2, noticia.getCategoria());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public void remover(long idNoticia) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = conexao.prepareStatement("delete from noticia where id=?");
			pstm.setLong(1, idNoticia);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public Noticia pegarPorId(long idNoticia) throws FalhaNoBanco{
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt = conexao.prepareStatement("Select * from noticia where id=?");
			stmt.setLong(1, idNoticia);
			rs = stmt.executeQuery();
			Noticia noticia = new Noticia();
			
			if(rs.first()){
				noticia.setCategoria(rs.getLong("id_categoria"));
				noticia.setId(rs.getLong("id"));
				noticia.setConteudo(rs.getString("conteudo"));
				noticia.setComentarios(this.comentarioDAO.obterTodos(noticia.getId()));
				return noticia;
			}
			
			return null;
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		} 
	}
	
	public List<Noticia> pegarTodas(long idCategoria) throws FalhaNoBanco{
		List<Noticia> all = new ArrayList<Noticia>();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt =	conexao.prepareStatement("Select * from noticia");
			rs = stmt.executeQuery();
			while(rs.next()){
				Noticia noticia = new Noticia();
				noticia.setId(rs.getLong("id"));
				noticia.setConteudo(rs.getString("conteudo"));
				noticia.setComentarios(this.comentarioDAO.obterTodos(noticia.getId()));
				all.add(noticia);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
		return all;
	}
}
