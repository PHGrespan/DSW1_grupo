package br.ufscar.dc.dsw.domain;

public class Destino{

	private Long id;
	private String nome;
    private PacoteTuristico pacote;

    public Destino(Long id, String nome, PacoteTuristico pacote) {
        this.id = id;
        this.nome = nome;
        this.pacote = pacote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PacoteTuristico getPacote() {
        return this.pacote;
    }

    public void setPacote(PacoteTuristico pacote) {
        this.pacote = pacote;
    }

}
