package br.ufscar.dc.dsw.domain;

public class Foto{

	private Long id;
    private Destino destino;
	private String link;

    public Foto(Long id, Destino destino, String link) {
        this.id = id;
        this.destino = destino;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
