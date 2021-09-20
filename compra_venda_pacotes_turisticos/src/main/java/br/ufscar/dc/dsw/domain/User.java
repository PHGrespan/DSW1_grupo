package br.ufscar.dc.dsw.domain;

public class User{

	private Long id;
	private String nome;
	private String login;
	private String senha;
    private boolean isAdm;

	public User(Long id, String nome, String login, String senha, boolean isAdm) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.isAdm = isAdm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public boolean getIsAdm() {
		return isAdm;
	}

	public void setIsAdm(boolean isAdm) {
		this.isAdm = isAdm;
	}
}
