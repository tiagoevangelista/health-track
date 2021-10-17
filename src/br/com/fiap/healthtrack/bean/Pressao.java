package br.com.fiap.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Pressao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Calendar data;
	private Integer sistolica;
	private Integer diastolica;
	
	
	public Pressao() {
		super();
	}

	public Pressao(Integer codigo, Calendar data, Integer sistolica, Integer diastolica) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.sistolica = sistolica;
		this.diastolica = diastolica;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Integer getSistolica() {
		return sistolica;
	}

	public void setSistolica(Integer sistolica) {
		this.sistolica = sistolica;
	}

	public Integer getDiastolica() {
		return diastolica;
	}

	public void setDiastolica(Integer diastolica) {
		this.diastolica = diastolica;
	}	
	
		
}
