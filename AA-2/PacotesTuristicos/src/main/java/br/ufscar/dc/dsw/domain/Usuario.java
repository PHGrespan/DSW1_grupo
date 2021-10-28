package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long>{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String senha;
       
    @NotBlank
    @Column(nullable = false, length = 10)
    private String role;

    @Column(nullable = false)
    private boolean ativo;
}
