package br.com.fiap.healthtrack.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager_SAMPLE {

	private static ConnectionManager_SAMPLE connectionManager;

	private ConnectionManager_SAMPLE() {
	}

	public static ConnectionManager_SAMPLE getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager_SAMPLE();
		}
		return connectionManager;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("JDBC", "USER","PASSWORD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}