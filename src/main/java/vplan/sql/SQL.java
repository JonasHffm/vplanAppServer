package vplan.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {

    private String HOST;
    private String DATABASE;
    private String USER;
    private String PASSWORD;

    private Connection con;


    public SQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
    }

    public void connect() {
        try {
            System.out.println(" ");
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("Die Verbindung zur MySQL-Datenbank wurde hergestellt!");
        } catch (SQLException e) {
            System.out.println(" ");
            System.out.println("Die Verbindung zur MySQL-Datenbank ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
        System.out.println(" ");
        System.out.println(" ");

    }


    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println(" ");
                System.out.println("Die Verbindung zur MySQL-Datenbank wurde Erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println(" ");
            System.out.println("Fehler beim beenden der Verbindung zur MySQL-Datenbank! Fehler: " + e.getMessage());
        }
    }


    public void update(String qry) {

        try {
            con.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ResultSet getResults(String qry) {

        try {
            return con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  boolean isConnected() {
        if(con != null) {
            return true;
        }else {
            return false;
        }

    }

}
