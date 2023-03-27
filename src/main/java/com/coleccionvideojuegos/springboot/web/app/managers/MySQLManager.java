package com.coleccionvideojuegos.springboot.web.app.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySQLManager {
	
	public static String driver = "com.mysql.cj.jdbc.Driver"; //Common.getProperty("BD.driver");
	public static String url = "jdbc:mysql://localhost:3306/videojuegos?useSSL=false"; //Common.getProperty("BD.url");
	public static String username = "root"; //Common.getProperty("BD.username");
	public static String password = "Pr0gr4m4r!"; //Common.getProperty("BD.password");
	
	/**
	 * CONECCTAR CON LA BADE DE DATOS
	 * @return
	 */
	public Connection conectar() {
		Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
	
	
	public final static Logger log = LogManager.getLogger(MySQLManager.class);
}
