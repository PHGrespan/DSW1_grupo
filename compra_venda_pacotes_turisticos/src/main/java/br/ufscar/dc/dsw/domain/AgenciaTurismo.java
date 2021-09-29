package br.ufscar.dc.dsw.domain;

public class AgenciaTurismo{

	private String email;
	private String senha;
	private String cnpj;
	private String nome;
	private String descricao;
	
	public AgenciaTurismo(String email, String senha, String cnpj, String nome, String descricao) {
		this.email = email;
		this.senha = senha;
		this.cnpj = cnpj;
		this.nome = nome;
		this.descricao = descricao;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
