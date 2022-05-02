package dev.thom.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection createConnection(Properties databaseProperties) throws SQLException {

        Properties dbProperties = new Properties();
        dbProperties.setProperty("url", "jdbc:postgresql://revaturedb.cilcmbbm7xyz.us-east-1.rds.amazonaws.com/revaturedb");
        dbProperties.setProperty("user", "postgres");
        dbProperties.setProperty("password", "");

        String url = dbProperties.getProperty("url","");
        Connection connection = DriverManager.getConnection(url, dbProperties);


        return connection;
    }
}
