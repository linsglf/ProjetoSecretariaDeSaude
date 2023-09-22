package DAO;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	
	public Connection conectaBD() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/[NOME BANCO DE DADOS]?user=root&password=";
			conn = DriverManager.getConnection(url);
			
		} catch (SQLException e) {
			Alerts.showAlert("Error", null,"ConexaoDAO" + e.getMessage(), AlertType.ERROR);
		}
		return conn;
	}

}


