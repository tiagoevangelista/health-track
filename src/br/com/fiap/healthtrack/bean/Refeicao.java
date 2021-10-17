package br.com.fiap.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

import br.com.fiap.healthtrack.bean.Alimento;

public class Refeicao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Calendar data;
	
	private Alimento alimento;
	
	private Integer usuario;
	
	
	public Refeicao() {
		super();
	}


	public Refeicao(Integer codigo, Calendar data) {
		super();
		this.codigo = codigo;
		this.data = data;
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


	public Alimento getAlimento() {
		return alimento;
	}


	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}


	public Integer getUsuario() {
		return usuario;
	}


	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	
}
