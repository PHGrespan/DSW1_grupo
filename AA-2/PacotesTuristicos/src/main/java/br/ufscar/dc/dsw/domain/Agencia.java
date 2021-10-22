package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_AGENCIA")
public class Agencia extends AbstractEntity<Long>{
    
    @NotBlank
    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 128)
	private String senha;

    @NotBlank
    @Column(nullable = false, length = 50)
	private String cnpj;

    @NotBlank
    @Column(nullable = false, length = 300)
	private String nome;

    @Column(nullable = true, length = 255)
	private String descricao;


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
