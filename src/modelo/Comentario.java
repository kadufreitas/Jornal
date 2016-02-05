package modelo;

public class Comentario {
	private long id;
	private Usuario usuario;
	private long idNoticia;
	private String conteudo;
	
	public Comentario(){
		
	}
	
	public Comentario(long id, Usuario usuario, long idNoticia, String conteudo){
		this.conteudo = conteudo;
		this.id = id;
		this.usuario = usuario;
		this.conteudo = conteudo;
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
	public long getIdNoticia() {
		return idNoticia;
	}
	public void setIdNoticia(long idNoticia) {
		this.idNoticia = idNoticia;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
