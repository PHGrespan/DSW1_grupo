package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long>{
    
    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String senha;
       
    @NotBlank
    @Column(nullable = false, length = 60)
    private String cpf;
    
    @NotBlank
    @Column(nullable = false, length = 14)
    private String id_adm;
    
    @NotBlank
    @Column(nullable = false, length = 10)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled;
}
