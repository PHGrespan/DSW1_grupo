package br.ufscar.dc.dsw.domain;

public class Compra{

    private Cliente cliente;
    private PacoteTuristico pacote;

    public Compra(Cliente cliente, PacoteTuristico pacote) {
        this.cliente = cliente;
        this.pacote = pacote;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PacoteTuristico getPacote() {
        return this.pacote;
    }

    public void setPacote(PacoteTuristico pacote) {
        this.pacote = pacote;
    }

}
