package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "PACOTE")
public class Pacote  extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255, unique = true)
    private String nome;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CNPJ_AGENCIA")
    @Column(nullable = false, length = 50)
	private Agencia agencia;

    @NotBlank
    @Column(nullable = false, length = 12)
    private String dataPartida;

    @NotBlank
    @Column(nullable = false)
    private Integer duracao;

    @NotBlank
    @Column(nullable = false)
    private Float valor;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String destinos;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String fotos;
}
