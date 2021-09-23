package br.ufscar.dc.dsw.domain;

public class User{

	private Long id;
	private String email;
	private String senha;
    private boolean isAdm;

	public User(Long id, String email, String senha, boolean isAdm) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.isAdm = isAdm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
