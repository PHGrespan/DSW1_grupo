package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Compra")
public class Compra {

    @NotBlank
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal preco;
    
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;
}
