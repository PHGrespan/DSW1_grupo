package br.ufscar.dc.dsw.domain;

public class User{

	private Long id;
	private String email;
	private String senha;
    private boolean isAdm;
    private String func;
    
    public User(Long id) {
		this.id = id;
	}

	public User(String email, String senha, boolean isAdm, String func) {
		super();
		this.email = email;
		this.senha = senha;
		this.isAdm = isAdm;
		this.func = func;
	}
	
	public User(Long id, String email, String senha, boolean isAdm, String func) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.isAdm = isAdm;
		this.func = func;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public boolean getIsAdm() {
		return isAdm;
	}

	public void setIsAdm(boolean isAdm) {
		this.isAdm = isAdm;
	}
	
	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}
}
