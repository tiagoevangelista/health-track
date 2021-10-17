package br.com.fiap.healthtrack.dao;

import java.util.List;

import br.com.fiap.healthtrack.bean.Peso;
import br.com.fiap.healthtrack.exception.DBException;

public interface PesoDAO {
	void cadastrar(Peso peso) throws DBException;
	void atualizar(Peso peso) throws DBException;
	void remover(Integer codigo) throws DBException;
	Peso buscar(Integer id);
	List<Peso> listar();
	List<Peso> resumir();
}
