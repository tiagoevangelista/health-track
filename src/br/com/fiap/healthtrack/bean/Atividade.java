package br.com.fiap.healthtrack.bean;

import java.io.Serializable;

public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	
	
	public Atividade() {
		super();
	}


	public Atividade(Integer codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
