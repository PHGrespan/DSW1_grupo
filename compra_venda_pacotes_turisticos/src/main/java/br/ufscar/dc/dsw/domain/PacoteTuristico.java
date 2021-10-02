package br.ufscar.dc.dsw.domain;

public class PacoteTuristico{

    private String nome;
	private AgenciaTurismo agencia;
    private String dataPartida;
    private Integer duracao;
    private Float valor;
    private String descricao;
    private String destinos;
    private String fotos;
    

    public PacoteTuristico(String nome, AgenciaTurismo agencia, String dataPartida, Integer duracao, Float valor, String descricao, String destinos, String fotos) {
        this.nome = nome;
        this.agencia = agencia;
        this.dataPartida = dataPartida;
        this.duracao = duracao;
        this.valor = valor;
        this.descricao = descricao;
        this.destinos = destinos;
        this.fotos = fotos;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AgenciaTurismo getAgencia() {
        return this.agencia;
    }

    public void setAgencia(AgenciaTurismo agencia) {
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
