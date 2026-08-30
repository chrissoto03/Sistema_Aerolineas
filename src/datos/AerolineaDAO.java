/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.Aerolinea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class AerolineaDAO {
    
    public boolean insertar(Aerolinea aerolinea) {
        String sql = "insert into aerolineas (nombre) values (?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, aerolinea.getNombre());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar aerolínea: " + e.getMessage());
            return false;
        }
    }

    public List<Aerolinea> listar() {
        List<Aerolinea> lista = new ArrayList<>();
        String sql = "select id_aerolinea, nombre from aerolineas order by nombre";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aerolinea a = new Aerolinea(
                    rs.getInt("id_aerolinea"),
                    rs.getString("nombre")
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar aerolineas: " + e.getMessage());
        }

        return lista;
    }
    
}
