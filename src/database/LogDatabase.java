package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogDatabase {

    private final String connectionString = "jdbc:sqlite:/home/stefan/IdeaProjects/BankApp/Database";
    public Connection conn = null;


    public LogDatabase() {
        testConnection();
    }

    public void testConnection() {
        try {
            this.conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}
