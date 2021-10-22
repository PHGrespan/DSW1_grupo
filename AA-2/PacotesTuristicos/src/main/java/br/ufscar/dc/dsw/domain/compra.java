package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_COMPRA")
public class compra {

    @NotBlank
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank
    @ManyToMany
    @JoinColumn(name = "CPF")
    private Cliente cliente;

    @NotBlank
    @ManyToMany
    @JoinColumn(name = "ID_PACOTE")
    private Pacote pacote;


    public Long getId() {
        return this.id;
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

    public Pacote getPacote() {
        return this.pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }    
}
