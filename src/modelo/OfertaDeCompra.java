package modelo;

public class OfertaDeCompra {
	private long id;
	private Usuario usuario;
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
