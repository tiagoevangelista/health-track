package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.healthtrack.bean.Atividade;
import br.com.fiap.healthtrack.dao.AtividadeDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OracleAtividadeDAO implements AtividadeDAO {
	private Connection conexao;
	
	@Override
	public void cadastrar(Atividade atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_ATIVIDADE (CD_ATIVIDADE, NM_ATIVIDADE) VALUES (SQ_HT_ID_GERAL.NEXTVAL, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, atividade.getNome());
			
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
	public void atualizar(Atividade atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_ATIVIDADE SET NM_ATIVIDADE = ? WHERE CD_ATIVIDADE = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, atividade.getNome());
			stmt.setInt(2, atividade.getCodigo());

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
			String sql = "DELETE FROM T_HT_ATIVIDADE WHERE CD_ATIVIDADE = ?";
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
	public Atividade buscar(Integer id) {
		Atividade atividade = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ATIVIDADE WHERE CD_ATIVIDADE = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_ATIVIDADE");
				String nome = rs.getString("NM_ATIVIDADE");

				atividade = new Atividade(codigo, nome);
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
		return atividade;
	}
	
	@Override
	public List<Atividade> listar() {
		List<Atividade> lista = new ArrayList<Atividade>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ATIVIDADE");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_ATIVIDADE");
				String nome = rs.getString("NM_ATIVIDADE");

				Atividade atividade= new Atividade(codigo, nome);
				lista.add(atividade);
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
	
	
	public List<Atividade> resumir() {
		List<Atividade> lista = new ArrayList<Atividade>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_ATIVIDADE ORDER BY CD_ATIVIDADE DESC) WHERE ROWNUM <= 3");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_ATIVIDADE");
				String nome = rs.getString("NM_ATIVIDADE");

				Atividade atividade= new Atividade(codigo, nome);
				lista.add(atividade);
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
	
	public Atividade ultimo() {
		Atividade atividade = new Atividade();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_ATIVIDADE ORDER BY CD_ATIVIDADE DESC) WHERE ROWNUM <= 1");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Integer codigo = rs.getInt("CD_ATIVIDADE");
				String nome = rs.getString("NM_ATIVIDADE");

				atividade = new Atividade(codigo, nome);
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

		return atividade;
	}
}
