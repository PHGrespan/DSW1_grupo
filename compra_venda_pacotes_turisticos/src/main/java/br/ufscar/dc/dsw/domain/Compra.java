package br.ufscar.dc.dsw.domain;

public class Compra{

	private Long id;
    private Cliente cliente;
    private AgenciaTurismo agencia;
    private PacoteTuristico pacote;
    private Float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public AgenciaTurismo getAgenciaTurismo() {
        return agencia;
    }

    public void setAgenciaTurismo(AgenciaTurismo agencia) {
        this.agencia = agencia;
    }

    public PacoteTuristico getIdPacoteTuristico() {
        return pacote;
    }

    public void setPacoteTuristico(PacoteTuristico pacote) {
        this.pacote = pacote;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
