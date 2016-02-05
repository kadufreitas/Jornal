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
import modelo.OfertaDeCompra;

public class OfertaDeCompraDAO {
	private Connection conexao;
	private UsuarioDAO usuarioDAO;
	
	public OfertaDeCompraDAO(Connection conexao){
		this.conexao = conexao;
		this.usuarioDAO = new UsuarioDAO(conexao);
	}
	
	public void cadastrar(OfertaDeCompra ofertaDeCompra) throws FalhaNoBanco{
		try {
			PreparedStatement pstm = conexao.prepareStatement("insert into oferta_compra(id_classificado, id_usuario, conteudo) values (?, ?, ?)");
			pstm.setLong(1, ofertaDeCompra.getIdClassificado());
			pstm.setLong(2, ofertaDeCompra.getUsuario().getId());
			pstm.setString(3, ofertaDeCompra.getConteudo());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
	}
	
	public List<OfertaDeCompra> pegarTodasPorIdClassificado(long idClassificado) throws FalhaNoBanco{
		List<OfertaDeCompra> all = new ArrayList<OfertaDeCompra>();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		try {
			stmt =	conexao.prepareStatement("Select * from oferta_compra where id_classificado = ?");
			stmt.setLong(1, idClassificado);
			rs = stmt.executeQuery();
			while(rs.next()){
				OfertaDeCompra ofertaDeCompra = new OfertaDeCompra();
				ofertaDeCompra.setId(rs.getLong("id"));
				ofertaDeCompra.setConteudo(rs.getString("conteudo"));
				ofertaDeCompra.setIdClassificado(idClassificado);
				long idUsuario = rs.getLong("id_usuario");
				ofertaDeCompra.setUsuario(this.usuarioDAO.pegarPorId(idUsuario));
				all.add(ofertaDeCompra);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new FalhaNoBanco();
		}
		return all;
	}
}
