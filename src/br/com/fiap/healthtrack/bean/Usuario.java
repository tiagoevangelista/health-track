package br.com.fiap.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

import br.com.fiap.healthtrack.util.CriptografiaUtils;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String senha;
	private String email;
	private String nome;
	private String sobrenome;
	private Double altura;
	private Calendar dataNacimento;

	
	public Usuario() {
		super();
	}
	
	

	public Usuario(String senha, String email) {
		super();
		this.senha = senha;
		this.email = email;
	}



	public Usuario(int id, String username, String senha, String email, String nome, String sobrenome, Double altura,
			Calendar dataNacimento) {
		super();
		this.id = id;
		this.username = username;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.altura = altura;
		this.dataNacimento = dataNacimento;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Calendar getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(Calendar dataNacimento) {
		this.dataNacimento = dataNacimento;
	}	

}
