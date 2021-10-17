package br.com.fiap.healthtrack.dao;

import java.util.List;

import br.com.fiap.healthtrack.bean.Treino;
import br.com.fiap.healthtrack.exception.DBException;

public interface TreinoDAO {
	void cadastrar(Treino treino) throws DBException;
	void atualizar(Treino treino) throws DBException;
	void remover(Integer codigo) throws DBException;
	Treino buscar(Integer id);
	List<Treino> listar();
	List<Treino> resumir();
}
