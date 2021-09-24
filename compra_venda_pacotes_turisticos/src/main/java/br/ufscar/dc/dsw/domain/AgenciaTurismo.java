package br.ufscar.dc.dsw.domain;

public class AgenciaTurismo{

	private Long id;
	private User user;
	private String cnpj;
	private String nome;
	private String descricao;
	
	 public AgenciaTurismo(Long id) {
	        this.id = id;
	    }

    public AgenciaTurismo(User user, String cnpj, String nome, String descricao) {
        this.user = user;
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public AgenciaTurismo(Long id, User user, String cnpj, String nome, String descricao) {
        this(user, cnpj, nome, descricao);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
