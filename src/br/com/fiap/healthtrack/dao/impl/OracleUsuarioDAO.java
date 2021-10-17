package br.com.fiap.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.fiap.healthtrack.bean.Alimento;
import br.com.fiap.healthtrack.bean.Usuario;
import br.com.fiap.healthtrack.dao.UsuarioDAO;
import br.com.fiap.healthtrack.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {
private Connection conexao;
	
	@Override
	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
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
		
		return false;
	}
	
	public Usuario guardar() {
		Usuario usuario = new Usuario();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HT_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Integer idUsuario = rs.getInt("ID_USUARIO");
				String username = rs.getString("DS_USERNAME");
				String senhaUsuario = rs.getString("DS_SENHA");
				String emailUsuario = rs.getString("DS_EMAIL");
				String nomeUsuario = rs.getString("NM_USUARIO");
				String sobrenome = rs.getString("NM_SOBRENOME");
				Double altura = rs.getDouble("DS_ALTURA");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());

				usuario = new Usuario(idUsuario, username, senhaUsuario, emailUsuario, nomeUsuario, sobrenome, altura, dataNascimento);
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

		return usuario;
	}
}
