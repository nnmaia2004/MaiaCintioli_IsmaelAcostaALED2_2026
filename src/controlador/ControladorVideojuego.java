package controlador;

import dao.VideojuegoDAO;
import modelo.Videojuego;
import java.sql.SQLException;
import java.util.List;

public class ControladorVideojuego {

    private final VideojuegoDAO dao = new VideojuegoDAO();

    public void agregar(String nombre, String plataforma,
                        String genero, String precio, String stock)
                        throws SQLException {

        Videojuego v = validarDatos(
                0, nombre, plataforma, genero, precio, stock);

        dao.agregar(v);
    }

    public void modificar(int id, String nombre, String plataforma,
                          String genero, String precio, String stock)
                          throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un videojuego.");
        }

        Videojuego v = validarDatos(
                id, nombre, plataforma, genero, precio, stock);

        dao.modificar(v);
    }

    public void eliminar(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un videojuego.");
        }

        dao.eliminar(id);
    }

    public List<Videojuego> listar(String nombre, boolean stockBajo)
            throws SQLException {

        return dao.listar(nombre.trim(), stockBajo);
    }

    private Videojuego validarDatos(int id, String nombre,
                                   String plataforma, String genero,
                                   String precioTexto, String stockTexto) {

        nombre = nombre.trim();
        plataforma = plataforma.trim();
        genero = genero.trim();
        precioTexto = precioTexto.trim();
        stockTexto = stockTexto.trim();

        if (nombre.isEmpty() || plataforma.isEmpty()
                || genero.isEmpty() || precioTexto.isEmpty()
                || stockTexto.isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe completar todos los campos.");
        }

        if (nombre.length() > 100 || plataforma.length() > 50
                || genero.length() > 50) {

            throw new IllegalArgumentException(
                    "Uno de los textos supera el largo permitido.");
        }

        double precio;
        int stock;

        try {
            precio = Double.parseDouble(
                    precioTexto.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El precio debe ser numerico.");
        }

        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser un numero no negativo.");
        }

        try {
            stock = Integer.parseInt(stockTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El stock debe ser un numero entero.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo.");
        }

        return new Videojuego(
                id, nombre, plataforma, genero, precio, stock);
    }
}
