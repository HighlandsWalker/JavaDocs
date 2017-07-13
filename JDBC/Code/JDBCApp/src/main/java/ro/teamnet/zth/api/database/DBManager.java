package ro.teamnet.zth.api.database;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class DBManager {

    static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() {
        try {
//            Driver myDriver = new oracle.jdbc.driver.OracleDriver();
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!0");
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        registerDriver();
        try {
            Properties info = new Properties();
            info.put("user", DBProperties.USER);
            info.put("password", DBProperties.PASS);
            String URL = CONNECTION_STRING;
            Connection conn = DriverManager.getConnection(URL, info);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkConnection(Connection connection) {
        Statement stmt = null;
        String query = "select 1 FROM DUAL";
        String toReturn = "";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Object elem;
                elem = rs.getInt("1");
                System.out.println(elem);
                toReturn += elem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return toReturn;
        }
    }
}
