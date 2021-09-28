package br.ufscar.dc.dsw.domain;

public class Cliente{

	private Long id;
	private String email;
	private String senha;
	private String cpf;
	private String isAdm;
	private String nome;
	private String telefone;
    private String sexo;
	private String dataNasc;
	
	public Cliente(Long id) {
		this.id = id;
	}
	
    public Cliente(String email, String senha, String cpf, String isAdm, String nome, String telefone, String sexo, String dataNasc) {
    	super();
    	this.email = email;
    	this.senha = senha;
        this.cpf = cpf;
        this.isAdm = isAdm;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }
    
    public Cliente(Long id, String email, String senha, String cpf, String isAdm, String nome, String telefone, String sexo, String dataNasc) {
    	super();
    	this.id = id;
    	this.email = email;
    	this.senha = senha;
        this.cpf = cpf;
        this.isAdm = isAdm;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

}
