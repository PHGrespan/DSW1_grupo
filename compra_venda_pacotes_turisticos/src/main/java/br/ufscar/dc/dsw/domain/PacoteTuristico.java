package br.ufscar.dc.dsw.domain;

public class PacoteTuristico{

	private Long id;
	private AgenciaTurismo agencia;
    private String dataPartida;
    private String dataChegada;
    private Float valor;
    private String descricao;

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

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
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
