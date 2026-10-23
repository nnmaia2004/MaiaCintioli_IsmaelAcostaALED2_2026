package dao;

import conexion.Conexion;
import modelo.Videojuego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAO {

    public void agregar(Videojuego v) throws SQLException {

        String sql = "INSERT INTO videojuegos "
                + "(nombre, plataforma, genero, precio, stock) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getPlataforma());
            ps.setString(3, v.getGenero());
            ps.setDouble(4, v.getPrecio());
            ps.setInt(5, v.getStock());

            ps.executeUpdate();
        }
    }

    
public List<Videojuego> listar(String busqueda, boolean stockBajo)
        throws SQLException {

    List<Videojuego> lista = new ArrayList<>();

    String sql = "SELECT * FROM videojuegos WHERE "
            + "(CAST(id AS CHAR) LIKE ? "
            + "OR nombre LIKE ? "
            + "OR plataforma LIKE ? "
            + "OR genero LIKE ?)";

    if (stockBajo) {
        sql += " AND stock <= 5";
    }

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        String filtro = "%" + busqueda + "%";

        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ps.setString(3, filtro);
        ps.setString(4, filtro);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Videojuego v = new Videojuego(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );

                lista.add(v);
            }
        }
    }

    return lista;
}

    public void modificar(Videojuego v) throws SQLException {

        String sql = "UPDATE videojuegos SET "
                + "nombre=?, plataforma=?, genero=?, "
                + "precio=?, stock=? WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getPlataforma());
            ps.setString(3, v.getGenero());
            ps.setDouble(4, v.getPrecio());
            ps.setInt(5, v.getStock());
            ps.setInt(6, v.getId());

            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM videojuegos WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
