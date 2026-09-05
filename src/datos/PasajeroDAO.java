/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.Pasajero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class PasajeroDAO {
    public boolean insertar(Pasajero pasajero){
        String sql = "insert into pasajeros(cedula,nombre_completo,pasaporte,"
                + "fecha_vencimiento_pasaporte) "
                + "values(?,?,?,?)";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, pasajero.getCedula());
            ps.setString(2, pasajero.getNombreCompleto());
            ps.setString(3, pasajero.getPasaporte());
            ps.setDate(4, Date.valueOf(pasajero.getFechaVencimientoPasaporte()));
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; 
            
        } catch (SQLException e) {
            System.out.println("Error al insertar pasajero: "+e.getMessage());
            return false;
        }
    }
    
    public boolean modificar(Pasajero pasajero){
        String sql = "update pasajeros set cedula = ?, nombre_completo = ?, pasaporte = ?,"
                + "fecha_vencimiento_pasaporte = ? where id_pasajero = ? ";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, pasajero.getCedula());
            ps.setString(2, pasajero.getNombreCompleto());
            ps.setString(3, pasajero.getPasaporte());
            ps.setDate(4, Date.valueOf(pasajero.getFechaVencimientoPasaporte()));
            ps.setInt(5, pasajero.getIdPasajero());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; 
            
        } catch (SQLException e) {
            System.out.println("Error al modificar pasajero: "+e.getMessage());
            return false;
        }
    }
    
    public Pasajero buscarPorCedula(String cedula) {
        String sql = "SELECT id_pasajero, cedula, nombre_completo, pasaporte, "
                + "fecha_vencimiento_pasaporte FROM pasajeros WHERE cedula = ?";

        try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pasajero(
                            rs.getInt("id_pasajero"),
                            rs.getString("cedula"),
                            rs.getString("nombre_completo"),
                            rs.getString("pasaporte"),
                            rs.getDate("fecha_vencimiento_pasaporte").toLocalDate()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pasajero: " + e.getMessage());
        }

        return null;
    }
}

