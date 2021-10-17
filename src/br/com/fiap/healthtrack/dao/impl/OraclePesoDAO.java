package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.healthtrack.bean.Peso;
import br.com.fiap.healthtrack.dao.PesoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OraclePesoDAO implements PesoDAO {
private Connection conexao;
	
	@Override
	public void cadastrar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_PESO (CD_PESO, T_HT_USUARIO_ID_USUARIO, NR_PESO, DT_PESO) VALUES (SQ_HT_ID_GERAL.NEXTVAL, 10,  ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, peso.getPeso());
			java.sql.Date data = new java.sql.Date(peso.getData().getTimeInMillis());			
			stmt.setDate(2, data);
			
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
	public void atualizar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_PESO SET NR_PESO = ?, DT_PESO = ? WHERE CD_PESO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, peso.getPeso());
			java.sql.Date data = new java.sql.Date(peso.getData().getTimeInMillis());			
			stmt.setDate(2, data);
			stmt.setInt(3, peso.getCodigo());

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
			String sql = "DELETE FROM T_HT_PESO WHERE CD_PESO = ?";
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
	public Peso buscar(Integer id) {
		Peso peso = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PESO WHERE CD_PESO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_PESO");
				Double qtPeso = rs.getDouble("NR_PESO");
				java.sql.Date data = rs.getDate("DT_PESO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());

				peso = new Peso(codigo, qtPeso, dataInclusao);
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
		return peso;
	}
	
	@Override
	public List<Peso> listar() {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_PESO ORDER BY DT_PESO DESC, CD_PESO DESC");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_PESO");
				Double qtPeso = rs.getDouble("NR_PESO");
				java.sql.Date data = rs.getDate("DT_PESO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());

				Peso peso = new Peso(codigo, qtPeso, dataInclusao);
				lista.add(peso);
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
	
	public List<Peso> resumir() {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_PESO ORDER BY DT_PESO DESC, CD_PESO DESC) WHERE ROWNUM <= 3");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_PESO");
				Double nrPeso = rs.getDouble("NR_PESO");
				java.sql.Date data = rs.getDate("DT_PESO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());

				Peso peso = new Peso(codigo, nrPeso, dataInclusao);
				lista.add(peso);
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
