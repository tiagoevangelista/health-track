package br.com.fiap.healthtrack.dao;

import java.util.List;

import br.com.fiap.healthtrack.bean.Alimento;
import br.com.fiap.healthtrack.exception.DBException;

public interface AlimentoDAO {
	void cadastrar(Alimento alimento) throws DBException;
	void atualizar(Alimento alimento) throws DBException;
	void remover(Integer codigo) throws DBException;
	Alimento buscar(Integer id);
	List<Alimento> listar();
	List<Alimento> resumir();
	Alimento ultimo();
}
