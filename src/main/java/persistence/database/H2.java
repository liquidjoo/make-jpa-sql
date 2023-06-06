package persistence.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Logger;

public class H2 implements Database {

    private static final Logger LOGGER = Logger.getLogger(H2.class.getName());

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";

    private static final String USER = "sa";
    private static final String PASS = "";

    private DataSource dataSource;

    public H2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();

            if (Objects.nonNull(dataSource)) {
                conn = dataSource.getConnection();
            }

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();

            if (Objects.nonNull(dataSource)) {
                conn = dataSource.getConnection();
            }

            stmt = conn.createStatement();

            return stmt.executeQuery(sql);
        } catch (Exception se) {
            se.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
