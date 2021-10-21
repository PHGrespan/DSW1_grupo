package br.ufscar.dc.dsw.domain;

public class Compra{
	
	private Long id;
    private Cliente cliente;
    private PacoteTuristico pacote;
    
    public Compra(Long id) {
		this.id = id;
	}

    public Compra(Cliente cliente, PacoteTuristico pacote) {
        this.cliente = cliente;
        this.pacote = pacote;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
