package br.com.fiap.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;
import br.com.fiap.healthtrack.bean.Atividade;

public class Treino implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Calendar data;
	private Integer duracao;
	private Atividade atividade;
	private Integer usuario;
	
	public Treino() {
		super();
	}


	public Treino(Integer codigo, Calendar data, Integer duracao) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.duracao = duracao;
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


	public Integer getDuracao() {
		return duracao;
	}


	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}


	public Atividade getAtividade() {
		return atividade;
	}


	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}


	public Integer getUsuario() {
		return usuario;
	}


	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	

}
