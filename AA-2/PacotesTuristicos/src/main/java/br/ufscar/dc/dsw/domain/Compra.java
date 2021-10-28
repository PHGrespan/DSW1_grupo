package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "COMPRA")
public class Compra {

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
}
