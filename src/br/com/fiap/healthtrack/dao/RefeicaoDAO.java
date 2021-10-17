package br.com.fiap.healthtrack.dao;

import java.util.List;

import br.com.fiap.healthtrack.bean.Refeicao;
import br.com.fiap.healthtrack.exception.DBException;

public interface RefeicaoDAO {
	void cadastrar(Refeicao refeicao) throws DBException;
	void atualizar(Refeicao refeicao) throws DBException;
	void remover(Integer codigo) throws DBException;
	Refeicao buscar(Integer id);
	List<Refeicao> listar();
	List<Refeicao> resumir();
}
