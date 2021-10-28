package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENTE")
public class Cliente extends Usuario{
       
    @NotBlank
    @Column(nullable = false, length = 14)
    private String cpf;
    
    @NotBlank
    @Column(nullable = false, length = 300)
    private String nome;
    
    @Column(nullable = true, length = 15)
    private String telefone;

    @Column(nullable = true, length = 50)
    private String sexo;

    @Column(nullable = true, length = 12)
    private String dataNasc;
}
