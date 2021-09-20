package br.ufscar.dc.dsw.domain;

public class DestinoPacote{

	private Long id;
    private Destino destino;
    private PacoteTuristico pacote;

    public DestinoPacote(Long id, Destino destino, PacoteTuristico pacote) {
        this.id = id;
        this.destino = destino;
        this.pacote = pacote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public PacoteTuristico getPacoteTuristico() {
        return pacote;
    }

    public void setPacoteTuristico(PacoteTuristico pacote) {
        this.pacote = pacote;
    }
}
