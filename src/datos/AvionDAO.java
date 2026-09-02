/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mysql.cj.jdbc.ConnectionImpl;
import modelo.Avion;
import modelo.Aerolinea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RepaintManager;

/**
 *
 * @author chris
 */
public class AvionDAO {
    public boolean insertar(Avion avion){
        String sql = "insert into aviones (id_aerolinea,modelo,capacidad,estado) values (?,?,?,?)";
        
        try (Connection con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1,avion.getAerolinea().getIdAerolinea());
            ps.setString(2, avion.getModelo());
            ps.setInt(3,avion.getCapacidad());
            ps.setString(4, avion.getEstado());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al insertar avion: "+e.getMessage());
            return false;
        }
    }
    
    public boolean modificarAvion(Avion avion){
        String sql = "update aviones set id_aerolinea = ?, modelo = ?,capacidad = ?,estado = ? where id_avion = ?";
        
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)){
                
             ps.setInt(1, avion.getAerolinea().getIdAerolinea());
             ps.setString(2, avion.getModelo());
             ps.setInt(3,avion.getCapacidad());
             ps.setString(4, avion.getEstado());
             
             int filasAfectadas = ps.executeUpdate();
             return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al modicar avion: "+e.getMessage());
            return false;
        }
    }
    
    public List<Avion> listar(){
        List<Avion> lista = new ArrayList<>();
        
        String sql = "select a.id_Avion, a.modelo,a.capacidad,a.estado, "
                + "ae.id_aerolinea,ae.nombre "
                + "from aviones a "
                + "inner join aerolineas ae on a.id_aerolinea = ae.id_aerolinea "
                + "order by a.id_avion";
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Aerolinea aerolinea = new Aerolinea(
                        rs.getInt("id_aerolinea"),
                        rs.getString("nombre")
                );
                
                Avion avion = new Avion(
                rs.getInt("id_avion"),
                aerolinea,
                rs.getString("modelo"),
                rs.getInt("capacidad"),
                rs.getString("estado")
                );
                
                lista.add(avion);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar aviones: "+e.getMessage());
        }
        return lista;
    }
    
    public List<Avion> listarAviones(){
        List<Avion> lista = new ArrayList<>();
        
        String sql = "select a.id_avion, a.modelo, a.capacidad, a.estado,"
                + "ae.id_aerolinea, ae.nombre "
                + "from aviones a "
                + "inner join aerolineas ae on a.id_aerolinea = ae.id_aerolinea "
                + "where a.estado = 'Activo' "
                + "order by a.id_avion";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {                
                Aerolinea aerolinea = new Aerolinea(
                        rs.getInt("id_aerolinea"),
                        rs.getString("nombre")
                );
                
                Avion avion = new Avion(
                        rs.getInt("id_aerolinea"),
                        aerolinea,
                        rs.getString("modelo"),
                        rs.getInt("capacidad"),
                        rs.getString("estado")
                );
                lista.add(avion);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar aviones activos: "+e.getMessage());
        }
        return lista;
    }
}
