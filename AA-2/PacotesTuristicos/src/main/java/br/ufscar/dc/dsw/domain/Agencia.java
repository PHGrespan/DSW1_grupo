package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "AGENCIA")
public class Agencia extends Usuario{

    @NotBlank
    @Column(nullable = false, length = 50)
	private String cnpj;

    @NotBlank
    @Column(nullable = false, length = 300)
	private String nome;

    @Column(nullable = true, length = 255)
	private String descricao;

}
