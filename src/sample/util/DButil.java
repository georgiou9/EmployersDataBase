package sample.util;


import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/**
 * Created by George G.
 */
public class DButil {
    // Declare JDBC driver
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Connection
    private static Connection conn = null;
    // Connection String
    private static final String connStr = "jdbc:mysql://localhost/classicmodels?user=dbuser&password=333333";

    // Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        // Establish the MySQL connection using Connection string
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check out console!" + e);
            e.printStackTrace();
            throw e;
        }
        if (conn != null) {
            System.out.println("Connection Succeded!");
        }
    }

    // Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null & !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DB execute Query Operation
    public static ResultSet dbExcecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        // Declare statement, resultSet and CacheResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {
            // Connect to DB
            dbConnect();
            System.out.println("Select statement: " + queryStmt + " \n");

            // Create statement
            stmt = conn.createStatement();

            // Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    // DB Execute Update (for Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        // Declare stmt as null
        Statement stmt = null;
        try {
            // Connect to DB (Establish Oracle Connection
            dbConnect();
            // Create statement
            stmt = conn.createStatement();
            // Run executable operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occured at execute update Operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                // Close statement
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
