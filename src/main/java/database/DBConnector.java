package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    protected final String dbName = "ServletService";
    protected final String dbUser = "postgres";
    protected final String dbPassword = "1234";
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbPassword);
        if (dbConnection != null){
            System.out.println("Connection established!");
        }else {
            System.out.println("Connection failed");
        }
        return dbConnection;
    }
}
