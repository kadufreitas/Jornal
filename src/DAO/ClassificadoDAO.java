package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excecao.FalhaNoBanco;
import modelo.Categoria;
import modelo.Classificado;

public class ClassificadoDAO {
	private Connection conexao;
	private OfertaDeCompraDAO ofertaDeCompraDAO;
	
	public ClassificadoDAO(Connection conexao){
		this.conexao = conexao;
		this.ofertaDeCompraDAO = new OfertaDeCompraDAO(conexao);
	}
	
	public void cadastrar(Classificado classificado) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = conexao.prepareStatement("insert into classificado(conteudo) values (?)");
			pstm.setString(1, classificado.getConteudo());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public List<Classificado> pegarTodos() throws FalhaNoBanco{
		List<Classificado> all = new ArrayList<Classificado>();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt =	conexao.prepareStatement("Select * from classificado");
			rs = stmt.executeQuery();
			while(rs.next()){
				Classificado classificado = new Classificado();
				classificado.setId(rs.getLong("id"));
				classificado.setConteudo(rs.getString("conteudo"));
				classificado.setOfertas(this.ofertaDeCompraDAO.pegarTodasPorIdClassificado(classificado.getId()));
				all.add(classificado);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
		return all;
	}
}
