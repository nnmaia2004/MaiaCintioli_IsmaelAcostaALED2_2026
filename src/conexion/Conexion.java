package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/tienda_videojuegos";

    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL, USUARIO, PASSWORD);
    }
    
    
public static void main(String[] args) {
    try (Connection con = conectar()) {
        System.out.println("Conexion exitosa con MySQL");
    } catch (SQLException e) {
        System.out.println("Error de conexion: " + e.getMessage());
    }
}

}
