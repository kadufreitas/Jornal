package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excecao.FalhaNoBanco;
import modelo.Categoria;

public class CategoriaDAO {
	private Connection conexao;
	
	public CategoriaDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	public void cadastrar(Categoria categoria) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = conexao.prepareStatement("insert into categoria(nome) values (?)");
			pstm.setString(1, categoria.getNome());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public Categoria pegarPorId(long idCategoria) throws FalhaNoBanco{
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt = conexao.prepareStatement("Select * from categoria where id=?");
			stmt.setLong(1, idCategoria);
			rs = stmt.executeQuery();
			Categoria categoria = new Categoria();
			
			if(rs.first()){
				categoria.setId(rs.getLong("id"));
				categoria.setNome(rs.getString("nome"));
				return categoria;
			}
			
			return null;
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		} 
	}
	
	public List<Categoria> pegarTodas() throws FalhaNoBanco{
		List<Categoria> all = new ArrayList<Categoria>();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt =	conexao.prepareStatement("Select * from categoria");
			rs = stmt.executeQuery();
			while(rs.next()){
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id"));
				categoria.setNome(rs.getString("nome"));
				all.add(categoria);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
		return all;
	}
}
