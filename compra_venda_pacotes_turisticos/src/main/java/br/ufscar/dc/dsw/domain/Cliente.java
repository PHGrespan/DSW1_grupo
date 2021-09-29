package br.ufscar.dc.dsw.domain;

public class Cliente{

	private String email;
	private String senha;
	private String cpf;
	private String isAdm;
	private String nome;
	private String telefone;
    private String sexo;
	private String dataNasc;
    
    public Cliente(String email, String senha, String cpf, String isAdm, String nome, String telefone, String sexo, String dataNasc) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.isAdm = isAdm;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
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

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIsAdm() {
        return this.isAdm;
    }

    public void setIsAdm(String isAdm) {
        this.isAdm = isAdm;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    
}
