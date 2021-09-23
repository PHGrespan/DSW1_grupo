package br.ufscar.dc.dsw.domain;

import java.util.Date;
public class PacoteTuristico{

	private Long id;
	private AgenciaTurismo agencia;
    private Date dataPartida;
    private Date dataChegada;
    private Float valor;
    private String descricao;

    
    public PacoteTuristico(Long id, AgenciaTurismo agencia, Date dataPartida, Date dataChegada, Float valor, String descricao) {
        this.id = id;
        this.agencia = agencia;
        this.dataPartida = dataPartida;
        this.dataChegada = dataChegada;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AgenciaTurismo getAgenciaTurismo() {
        return agencia;
    }

    public void setAgenciaTurismo(AgenciaTurismo agencia) {
        this.agencia = agencia;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
