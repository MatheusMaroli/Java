package venda.maluca.model;

import venda.maluca.dao.UsuarioDAO;

public class Usuario {
	
	private Long codigo;	
	private String nome;	
	private String senha;	
	private String email;	
	private String login;
	
	public Usuario(String nome, String senha, String email, String login) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.login = login;
	}
	public Usuario() {
		
	}	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String s) {
		this.email = s;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Usuario logar(String user, String senha){
		return new UsuarioDAO().buscar(user, senha);
	}
	
	public String toString(){
		return this.getNome();
	}
}
