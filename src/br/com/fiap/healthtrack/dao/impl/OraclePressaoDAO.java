package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.healthtrack.bean.Pressao;
import br.com.fiap.healthtrack.dao.PressaoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OraclePressaoDAO implements PressaoDAO {
private Connection conexao;
	
	@Override
	public void cadastrar(Pressao pressao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_PRESSAO (CD_PRESSAO, DT_PRESSAO, NR_PRESSAO_SIST, NR_PRESSAO_DIAST) VALUES (SQ_HT_ID_GERAL.NEXTVAL, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			java.sql.Date data = new java.sql.Date(pressao.getData().getTimeInMillis());			
			stmt.setDate(1, data);
			stmt.setInt(2, pressao.getSistolica());
			stmt.setInt(3, pressao.getDiastolica());
			
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
	public void atualizar(Pressao pressao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_PRESSAO SET DT_PRESSAO = ?, NR_PRESSAO_SIST = ?, NR_PRESSAO_DIAST = ? WHERE CD_PRESSAO = ?";
			stmt = conexao.prepareStatement(sql);
			java.sql.Date data = new java.sql.Date(pressao.getData().getTimeInMillis());			
			stmt.setDate(1, data);
			stmt.setInt(2, pressao.getSistolica());
			stmt.setInt(3, pressao.getDiastolica());
			stmt.setInt(4, pressao.getCodigo());			

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
			String sql = "DELETE FROM T_HT_PRESSAO WHERE CD_PRESSAO = ?";
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
	public Pressao buscar(Integer id) {
		Pressao pressao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO WHERE CD_PRESSAO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_PRESSAO");
				java.sql.Date data = rs.getDate("DT_PRESSAO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				Integer sistolica = rs.getInt("NR_PRESSAO_SIST");
				Integer diastolica = rs.getInt("NR_PRESSAO_DIAST");

				pressao = new Pressao(codigo, dataInclusao, sistolica, diastolica);
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
		return pressao;
	}
	
	@Override
	public List<Pressao> listar() {
		List<Pressao> lista = new ArrayList<Pressao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PRESSAO");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_PRESSAO");
				java.sql.Date data = rs.getDate("DT_PRESSAO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				Integer sistolica = rs.getInt("NR_PRESSAO_SIST");
				Integer diastolica = rs.getInt("NR_PRESSAO_DIAST");

				Pressao pressao = new Pressao(codigo, dataInclusao, sistolica, diastolica);
				lista.add(pressao);
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
