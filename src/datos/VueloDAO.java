/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 * @author chris
 */
public class VueloDAO {
    public boolean insertar(Vuelo vuelo){
        String sql = "insert into vuelos(id_avion,id_piloto,pais_destino,ciudad_destino,"
                +"fecha_hora_salida,estado) "
                + "values (? , ?, ? , ?, ?, ?)";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, vuelo.getAvion().getIdAvion());
            ps.setInt(2, vuelo.getPiloto().getIdPiloto());
            ps.setString(3, vuelo.getPaisDestino());
            ps.setString(4, vuelo.getCiudadDestino());
            ps.setTimestamp(5, Timestamp.valueOf(vuelo.getFechaHoraSalida()));
            ps.setString(6, vuelo.getEstado());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al insertar vuelo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean cambiarEstado(int idVuelo,String nuevoEstado){
        String sql = "update vuelos set estado = ? where id_vuelo = ?";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idVuelo);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de vuelo: "+e.getMessage());
            return false;
        }
    }
    
    public List<Vuelo> listar(){
        List<Vuelo> lista = new ArrayList<>();
        String sql = armarSelectBase() + "order by v.fecha_hora_salida";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {   
                lista.add(mapearVuelo(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar vuelos: "+e.getMessage());
        }
        return lista;
    }
    
        public List<Vuelo> listarProgramados(){
        List<Vuelo> lista = new ArrayList<>();
        String sql = armarSelectBase() + " where v.estado = 'Programado' order by v.fecha_hora_salida";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {   
                lista.add(mapearVuelo(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar vuelos programados: "+e.getMessage());
        }
        return lista;
    }
    
    public String armarSelectBase() {
        return "select v.id_vuelo, v.pais_destino, v.ciudad_destino, "
                + "v.fecha_hora_salida, v.estado, "
                + "a.id_avion, a.modelo, a.capacidad, a.estado as estado_avion, "
                + "ae.id_aerolinea, ae.nombre as nombre_aerolinea, "
                + "p.id_piloto, p.cedula, p.nombre as nombre_piloto, p.apellidos, "
                + "p.fecha_nacimiento, p.estado_civil, p.numero_licencia, "
                + "p.fecha_vencimiento_licencia "
                + "from vuelos v "
                + "inner join aviones a on v.id_avion = a.id_avion "
                + "inner join aerolineas ae on a.id_aerolinea = ae.id_aerolinea "
                + "inner join pilotos p on v.id_piloto = p.id_piloto ";
    }
    
    private Vuelo mapearVuelo(ResultSet rs)throws SQLException{
        Aerolinea aerolinea = new Aerolinea(
                rs.getInt("id_aerolinea"),
                rs.getString("nombre_aerolinea")
        );
        
        Avion avion = new Avion(
                rs.getInt("id_avion"),
                aerolinea,
                rs.getString("modelo"),
                rs.getInt("capacidad"),
                rs.getString("estado_avion")
        );
        
        Piloto piloto = new Piloto(
                rs.getInt("id_piloto"),
                rs.getString("cedula"),
                rs.getString("nombre_piloto"),
                rs.getString("apellidos"),
                rs.getDate("fecha_nacimiento").toLocalDate(),
                rs.getString("estado_civil"),
                rs.getString("numero_licencia"),
                rs.getDate("fecha_vencimiento_licencia").toLocalDate()      
        );
        
        return new Vuelo(
                rs.getInt("id_vuelo"),
                avion,
                piloto,
                rs.getString("pais_destino"),
                rs.getString("ciudad_destino"),
                rs.getTimestamp("fecha_hora_salida").toLocalDateTime(),
                rs.getString("estado")
        );
    }
}
