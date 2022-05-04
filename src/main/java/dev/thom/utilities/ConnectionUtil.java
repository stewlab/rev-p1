package dev.thom.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection createConnection(Properties databaseProperties) throws SQLException {

        Properties dbProperties = new Properties();

        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");
        String dbUrl = System.getenv("DB_URL");

        dbProperties.setProperty("user", dbUser);
        dbProperties.setProperty("password", dbPass);
        dbProperties.setProperty("url", dbUrl);

        String url = dbProperties.getProperty("url","");
        Connection connection = DriverManager.getConnection(url, dbProperties);


        return connection;
    }
}
