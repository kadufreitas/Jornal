package modelo;

public class Usuario {
	private long id;
	private String nome;
	private String email;
	private String senha;
	private int tipo;
	
	public Usuario(){
		
	}
	
	public Usuario(long id, String nome, String email, String senha, int tipo){
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
}
