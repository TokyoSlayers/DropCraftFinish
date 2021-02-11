package net.DropCraft.Finish.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static Connection conn;

    public static void connect(String host, String database, int port, String user, String password) {
        if(!isConnected()){
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host+ ":" + port + "/"+database,user,password);
                System.out.println("Mysql en route");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Mysql mort");
            }
        }
    }

    public static void disconnect() {
        if(isConnected()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        try {
            if ((conn == null) || (conn.isClosed()) || (conn.isValid(5))) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Connection getConnection(){
        return conn;
    }
}
