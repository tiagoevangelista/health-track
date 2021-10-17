package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.healthtrack.bean.Atividade;
import br.com.fiap.healthtrack.bean.Treino;
import br.com.fiap.healthtrack.dao.TreinoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OracleTreinoDAO implements TreinoDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Treino treino) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_TREINO (CD_TREINO, T_HT_USUARIO_ID_USUARIO, T_HT_ATVIDADE_CD_ATIVIDADE, DT_ATIVIDADE, TMP_DURACAO) VALUES (SQ_HT_ID_GERAL.NEXTVAL, 10, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, treino.getAtividade().getCodigo());
			java.sql.Date data = new java.sql.Date(treino.getData().getTimeInMillis());			
			stmt.setDate(2, data);
			stmt.setInt(3, treino.getDuracao());

			stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void atualizar(Treino treino) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_TREINO SET DT_ATIVIDADE = ?, TMP_DURACAO = ? WHERE CD_TREINO = ?";
			stmt = conexao.prepareStatement(sql);
			java.sql.Date data = new java.sql.Date(treino.getData().getTimeInMillis());
			stmt.setDate(1, data);
			stmt.setInt(2, treino.getDuracao());	
			stmt.setInt(3, treino.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void remover(Integer codigo) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HT_TREINO WHERE CD_TREINO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Treino buscar(Integer id) {
		Treino treino = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_TREINO WHERE CD_TREINO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_TREINO");
				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				Integer duracao = rs.getInt("TMP_DURACAO");

				treino = new Treino(codigo, dataInclusao, duracao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return treino;
	}
	
	@Override
	public List<Treino> listar() {
		List<Treino> lista = new ArrayList<Treino>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_TREINO T, T_HT_ATIVIDADE A WHERE T.T_HT_ATVIDADE_CD_ATIVIDADE = A.CD_ATIVIDADE ORDER BY  T.DT_ATIVIDADE DESC, A.CD_ATIVIDADE DESC");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_TREINO");
				Integer idUsuario = rs.getInt("T_HT_USUARIO_ID_USUARIO");
				Integer idAtividade = rs.getInt("CD_ATIVIDADE");
				String nomeAtividade = rs.getString("NM_ATIVIDADE");
				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				Integer duracao = rs.getInt("TMP_DURACAO");

				Atividade atividade = new Atividade(idAtividade, nomeAtividade);
				
				Treino treino = new Treino(codigo, dataInclusao, duracao);
				treino.setUsuario(10);
				treino.setAtividade(atividade);
				lista.add(treino);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
	
	
	public List<Treino> resumir() {
		List<Treino> lista = new ArrayList<Treino>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_TREINO T, T_HT_ATIVIDADE A WHERE T.T_HT_ATVIDADE_CD_ATIVIDADE = A.CD_ATIVIDADE ORDER BY  T.DT_ATIVIDADE DESC, A.CD_ATIVIDADE DESC) WHERE ROWNUM <= 3");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_TREINO");
				Integer idUsuario = rs.getInt("T_HT_USUARIO_ID_USUARIO");
				Integer idAtividade = rs.getInt("CD_ATIVIDADE");
				String nomeAtividade = rs.getString("NM_ATIVIDADE");
				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				Integer duracao = rs.getInt("TMP_DURACAO");

				Atividade atividade = new Atividade(idAtividade, nomeAtividade);
				
				Treino treino = new Treino(codigo, dataInclusao, duracao);
				treino.setUsuario(10);
				treino.setAtividade(atividade);
				lista.add(treino);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
}
