package br.com.fiap.healthtrack.factory;

import br.com.fiap.healthtrack.dao.AlimentoDAO;
import br.com.fiap.healthtrack.dao.AtividadeDAO;
import br.com.fiap.healthtrack.dao.PesoDAO;
import br.com.fiap.healthtrack.dao.PressaoDAO;
import br.com.fiap.healthtrack.dao.RefeicaoDAO;
import br.com.fiap.healthtrack.dao.TreinoDAO;
import br.com.fiap.healthtrack.dao.UsuarioDAO;
import br.com.fiap.healthtrack.dao.impl.OracleAlimentoDAO;
import br.com.fiap.healthtrack.dao.impl.OracleAtividadeDAO;
import br.com.fiap.healthtrack.dao.impl.OraclePesoDAO;
import br.com.fiap.healthtrack.dao.impl.OraclePressaoDAO;
import br.com.fiap.healthtrack.dao.impl.OracleRefeicaoDAO;
import br.com.fiap.healthtrack.dao.impl.OracleTreinoDAO;
import br.com.fiap.healthtrack.dao.impl.OracleUsuarioDAO;

public class DAOFactory {
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}
	
	public static AtividadeDAO getAtividadeDAO() {
		return new OracleAtividadeDAO();
	}
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static PressaoDAO getPressoDAO() {
		return new OraclePressaoDAO();
	}
	
	public static RefeicaoDAO getRefeicaoDAO() {
		return new OracleRefeicaoDAO();
	}
	
	public static TreinoDAO getTreinoDAO() {
		return new OracleTreinoDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
}
