package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_PACOTE")
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


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getDataPartida() {
        return this.dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Integer getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Float getValor() {
        return this.valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestinos() {
        return this.destinos;
    }

    public void setDestinos(String destinos) {
        this.destinos = destinos;
    }

    public String getFotos() {
        return this.fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }
}
