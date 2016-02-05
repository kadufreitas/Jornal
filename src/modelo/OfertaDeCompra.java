package modelo;

public class OfertaDeCompra {
	private long id;
	private long idUsuario;
	private long idClassificado;
	private String conteudo;
	
	public OfertaDeCompra(){
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdClassificado() {
		return idClassificado;
	}
	public void setIdClassificado(long idClassificado) {
		this.idClassificado = idClassificado;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
