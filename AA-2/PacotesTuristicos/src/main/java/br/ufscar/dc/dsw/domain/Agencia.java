package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Agencia")
public class Agencia extends Usuario{

    @NotBlank
    @Column(nullable = false, length = 50)
	private String cnpj;

    @NotBlank
    @Column(nullable = false, length = 300)
	private String nome;

    @Column(nullable = true, length = 255)
	private String descricao;

    @OneToMany(mappedBy = "agencia")
	private List<Pacote> pacotes;
}
