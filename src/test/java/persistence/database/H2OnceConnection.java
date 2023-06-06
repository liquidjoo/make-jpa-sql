package persistence.database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Logger;

public class H2OnceConnection implements Database {

    private static final Logger LOGGER = Logger.getLogger(H2.class.getName());

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/jpa_test_db";

    private static final String USER = "sa";
    private static final String PASS = "";

    private DataSource dataSource;

    public H2OnceConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public H2OnceConnection() {
    }

    @Override
    public void execute(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if (Objects.nonNull(dataSource)) {
                conn = dataSource.getConnection();
            }

            //STEP 3: Execute a io.github.liquidjoo.simplehiberante.query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }

    @Override
    public ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if (Objects.nonNull(dataSource)) {
                conn = dataSource.getConnection();
            }

            //STEP 3: Execute a io.github.liquidjoo.simplehiberante.query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            return stmt.executeQuery(sql);
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw new IllegalArgumentException();
        }//Handle errors for Class.forName
        finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }


}
