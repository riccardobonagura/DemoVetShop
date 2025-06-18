package database;
import java.sql.*;


public class DBConnectionManager {

    //inserire nome database, username e password
    public static String url = "jdbc:mysql://localhost:3306/";
    public static String dbName = "ProgettoAmbulatorio";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String userName = "root";
    public static String password = "admin";

    private static Connection conn;
    private static PreparedStatement statement;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        //1. configurare il drivermanger
        Connection conn = null;
        Class.forName(driver); //com.mysql.cj.jdbc.Driver

        //2. crea la connessione tramite il driver manager
        conn = DriverManager.getConnection(url+dbName,userName,password);

        //3. restituisce la connessione
        return conn;
    }

    public static void closeConnection() throws ClassNotFoundException, SQLException {
        conn.close();
    }

    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        //2. creo lo statement
        Statement statment = conn.createStatement();

        //eseguo la query che ho fornito come input
        ResultSet ret = statment.executeQuery(query); //"SELECT * from STUDENTI where .... "


        //ci ritorna il result set
        return ret;
    }

    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        int ret = statement.executeUpdate(query);
        conn.close();
        return ret;
    }

    public static Integer updateQueryReturnGeneratedKey(String query) throws ClassNotFoundException, SQLException {
        Integer ret = null;

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            ret = rs.getInt(1);
        }

        conn.close();

        return ret;
    }
}