package br.com.fiap.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Peso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Double peso;
	private Calendar data;
	
	
	public Peso() {
	}

	public Peso(Integer codigo, Double peso, Calendar data) {
		super();
		this.codigo = codigo;
		this.peso = peso;
		this.data = data;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	
}
