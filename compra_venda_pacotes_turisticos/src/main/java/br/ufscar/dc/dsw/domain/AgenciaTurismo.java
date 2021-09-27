package br.ufscar.dc.dsw.domain;

public class AgenciaTurismo{

	private Long id;
	private String email;
	private String senha;
	private String isAdm;
	private String cnpj;
	private String nome;
	private String descricao;
	
	 public AgenciaTurismo(Long id) {
	        this.id = id;
	    }

    public AgenciaTurismo(String email, String senha, String isAdm, String cnpj, String nome, String descricao) {
        super();
    	this.email = email;
    	this.senha = senha;
    	this.isAdm = isAdm;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public AgenciaTurismo(Long id, String email, String senha, String isAdm, String cnpj, String nome, String descricao) {
        super();
        this.id = id;
        this.email = email;
    	this.senha = senha;
    	this.isAdm = isAdm;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
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
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getIsAdm() {
    	return isAdm;
    }
    
    public void setIsAdm(String isAdm) {
        this.isAdm = isAdm;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
