package modelo;

import java.util.List;

public class Classificado {
	private long id;
	private String conteudo;
	private List<OfertaDeCompra> ofertas;
	
	public Classificado(){
		
	}
	
	public Classificado(long id, String conteudo, List<OfertaDeCompra> ofertas){
		this.ofertas = ofertas;
		this.conteudo = conteudo;
		this.id = id;
	}
	
	public List<OfertaDeCompra> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<OfertaDeCompra> ofertas) {
		this.ofertas = ofertas;
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
}
