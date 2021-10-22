package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente extends AbstractEntity<Long>{
    
    @NotBlank
    @Column(nullable = false, length = 255, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 128)
    private String senha;
       
    @NotBlank
    @Column(nullable = false, length = 14)
    private String cpf;
    
    @NotBlank
    @Column(nullable = false, length = 3)
    private String id_adm;
    
    @NotBlank
    @Column(nullable = false, length = 300)
    private String nome;
    
    @Column(nullable = true, length = 15)
    private String telefone;

    @Column(nullable = true, length = 50)
    private String sexo;

    @Column(nullable = true, length = 12)
    private String dataNasc;


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

    public String getId_adm() {
        return this.id_adm;
    }

    public void setId_adm(String id_adm) {
        this.id_adm = id_adm;
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
