package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {

    private static final Logger logger =
            LogManager.getLogger(DBConnection.class);

    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {

        logger.info("Establishing database connection...");

        Connection con = DriverManager.getConnection(
                URL, USERNAME, PASSWORD);

        logger.info("Database connected successfully!");

        return con;
    }
}

