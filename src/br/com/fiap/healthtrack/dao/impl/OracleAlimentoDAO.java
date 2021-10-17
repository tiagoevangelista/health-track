package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.healthtrack.bean.Alimento;
import br.com.fiap.healthtrack.bean.Atividade;
import br.com.fiap.healthtrack.dao.AlimentoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OracleAlimentoDAO implements AlimentoDAO {
	private Connection conexao;
	
	@Override
	public void cadastrar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_ALIMENTO (CD_ALIMENTO, QT_CALORIA, NM_ALIMENTO) VALUES (SQ_HT_ID_GERAL.NEXTVAL, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, alimento.getCaloria());
			stmt.setString(2,  alimento.getNome());
			
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
	public void atualizar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_ALIMENTO SET NM_ALIMENTO = ?, QT_CALORIA = ? WHERE CD_ALIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, alimento.getNome());
			stmt.setDouble(2, alimento.getCaloria());
			stmt.setInt(3, alimento.getCodigo());

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
			String sql = "DELETE FROM T_HT_ALIMENTO WHERE CD_ALIMENTO = ?";
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
	public Alimento buscar(Integer id) {
		Alimento alimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ALIMENTO WHERE CD_ALIMENTO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_ALIMENTO");
				Double caloria = rs.getDouble("QT_CALORIA");
				String nome = rs.getString("NM_ALIMENTO");

				alimento = new Alimento(codigo, caloria, nome);
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
		return alimento;
	}
	
	@Override
	public List<Alimento> listar() {
		List<Alimento> lista = new ArrayList<Alimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_ALIMENTO ORDER BY CD_ALIMENTO DESC");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_ALIMENTO");
				Double caloria = rs.getDouble("QT_CALORIA");
				String nome = rs.getString("NM_ALIMENTO");

				Alimento alimento = new Alimento(codigo, caloria, nome);
				lista.add(alimento);
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
	
	public List<Alimento> resumir() {
		List<Alimento> lista = new ArrayList<Alimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_ALIMENTO ORDER BY CD_ALIMENTO DESC) WHERE ROWNUM <= 3");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_ALIMENTO");
				Double caloria = rs.getDouble("QT_CALORIA");
				String nome = rs.getString("NM_ALIMENTO");

				Alimento alimento = new Alimento(codigo, caloria, nome);
				lista.add(alimento);
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
	
	public Alimento ultimo() {
		Alimento alimento = new Alimento();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_ALIMENTO ORDER BY CD_ALIMENTO DESC) WHERE ROWNUM <= 1");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Integer codigo = rs.getInt("CD_ALIMENTO");
				Double caloria = rs.getDouble("QT_CALORIA");
				String nome = rs.getString("NM_ALIMENTO");

				alimento = new Alimento(codigo, caloria, nome);
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

		return alimento;
	}
}