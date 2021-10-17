package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.healthtrack.bean.Alimento;
import br.com.fiap.healthtrack.bean.Refeicao;
import br.com.fiap.healthtrack.dao.RefeicaoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OracleRefeicaoDAO implements RefeicaoDAO {
private Connection conexao;
	
	@Override
	public void cadastrar(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HT_DIETA (CD_DIETA, T_HT_USUARIO_ID_USUARIO, T_HT_ALIMENTO_CD_ALIMENTO, DT_ALIMENTO) VALUES (SQ_HT_ID_GERAL.NEXTVAL, 10, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, refeicao.getAlimento().getCodigo());
			java.sql.Date data = new java.sql.Date(refeicao.getData().getTimeInMillis());			
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
	public void atualizar(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HT_DIETA SET DT_DIETA = ? WHERE CD_DIETA = ?";
			stmt = conexao.prepareStatement(sql);
			java.sql.Date data = new java.sql.Date(refeicao.getData().getTimeInMillis());			
			stmt.setDate(1, data);
			stmt.setInt(2, refeicao.getCodigo());			

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
			String sql = "DELETE FROM T_HT_DIETA WHERE CD_DIETA = ?";
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
	public Refeicao buscar(Integer id) {
		Refeicao refeicao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_DIETA WHERE CD_DIETA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer codigo = rs.getInt("CD_DIETA");
				java.sql.Date data = rs.getDate("DT_ALIMENTO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());

				refeicao = new Refeicao(codigo, dataInclusao);
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
		return refeicao;
	}
	
	@Override
	public List<Refeicao> listar() {
		List<Refeicao> lista = new ArrayList<Refeicao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_DIETA R, T_HT_ALIMENTO A WHERE R.T_HT_ALIMENTO_CD_ALIMENTO = A.CD_ALIMENTO ORDER BY  R.DT_ALIMENTO DESC, A.CD_ALIMENTO DESC");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer codigo = rs.getInt("CD_DIETA");
				Integer idUsuario = rs.getInt("T_HT_USUARIO_ID_USUARIO");
				Integer codAlimento = rs.getInt("T_HT_ALIMENTO_CD_ALIMENTO");
				Double qtdCaloria = rs.getDouble("QT_CALORIA");
				String nomeAlimento = rs.getString("NM_ALIMENTO");
				java.sql.Date data = rs.getDate("DT_ALIMENTO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				
				Alimento alimento = new Alimento(codAlimento, qtdCaloria, nomeAlimento);

				Refeicao refeicao = new Refeicao(codigo, dataInclusao);
				refeicao.setUsuario(10);
				refeicao.setAlimento(alimento);
				lista.add(refeicao);
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
	
	
	public List<Refeicao> resumir() {
		List<Refeicao> lista = new ArrayList<Refeicao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM (SELECT * FROM T_HT_DIETA R, T_HT_ALIMENTO A WHERE R.T_HT_ALIMENTO_CD_ALIMENTO = A.CD_ALIMENTO ORDER BY  R.DT_ALIMENTO DESC, A.CD_ALIMENTO DESC) WHERE ROWNUM <= 3");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_DIETA");
				Integer idUsuario = rs.getInt("T_HT_USUARIO_ID_USUARIO");
				Integer codAlimento = rs.getInt("T_HT_ALIMENTO_CD_ALIMENTO");
				Double qtdCaloria = rs.getDouble("QT_CALORIA");
				String nomeAlimento = rs.getString("NM_ALIMENTO");
				java.sql.Date data = rs.getDate("DT_ALIMENTO");
				Calendar dataInclusao = Calendar.getInstance();
				dataInclusao.setTimeInMillis(data.getTime());
				
				Alimento alimento = new Alimento(codAlimento, qtdCaloria, nomeAlimento);

				Refeicao refeicao = new Refeicao(codigo, dataInclusao);
				refeicao.setUsuario(10);
				refeicao.setAlimento(alimento);
				
				lista.add(refeicao);
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
