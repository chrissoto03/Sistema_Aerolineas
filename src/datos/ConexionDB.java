package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (IOException e) {
            System.out.println("No se pudo leer config.properties: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver de MySQL: " + e.getMessage());
        }
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}