package br.com.fiap.healthtrack.dao;

import br.com.fiap.healthtrack.bean.Usuario;

public interface UsuarioDAO {
	boolean validarUsuario(Usuario usuario);
	Usuario guardar();
}
