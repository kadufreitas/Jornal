package modelo;

import java.util.List;

public class Noticia {
	private long id;
	private String titulo;
	private String conteudo;
	private long categoria;
	private List<Comentario> comentarios;

	public Noticia(){
		
	}
	
	public Noticia(long id, String conteudo, long categoria, List<Comentario> comentarios){
		this.id = id;
		this.conteudo = conteudo;
		this.categoria = categoria;
		this.comentarios = comentarios;
	}
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public long getCategoria() {
		return categoria;
	}
	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}
}
