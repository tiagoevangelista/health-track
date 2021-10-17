package br.com.fiap.healthtrack.bean;

import java.io.Serializable;

public class Alimento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Double caloria;
	private String nome;
	
	
	public Alimento() {
		super();
	}


	public Alimento(Integer codigo, Double caloria, String nome) {
		super();
		this.codigo = codigo;
		this.caloria = caloria;
		this.nome = nome;
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public Double getCaloria() {
		return caloria;
	}


	public void setCaloria(Double caloria) {
		this.caloria = caloria;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

}
