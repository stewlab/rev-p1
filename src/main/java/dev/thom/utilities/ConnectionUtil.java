package dev.thom.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection createConnection(Properties databaseProperties) throws SQLException {

        Properties dbProperties = new Properties();
        databaseProperties.setProperty("url", "jdbc:postgresql://revaturedb.cilcmbbm7xyz.us-east-1.rds.amazonaws.com/revaturedb");
        databaseProperties.setProperty("user", "postgres");
        databaseProperties.setProperty("password", "args[0]");

        String url = databaseProperties.getProperty("url","");
        Connection connection = DriverManager.getConnection(url, dbProperties);


        return connection;
    }
}
