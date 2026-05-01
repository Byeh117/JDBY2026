package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static DatabaseConfig INSTANCE;
    private Connection conn;

    private DatabaseConfig() throws SQLException {
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pizza_hub",
                "root",
                "MySQLpw1!"
        );
    }

    public static DatabaseConfig getInstance() throws SQLException {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseConfig();
        }
        return INSTANCE;
    }

    public Connection getConn() {
        return conn;
    }
}
