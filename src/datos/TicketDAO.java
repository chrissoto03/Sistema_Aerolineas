/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.*;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 * @author chris
 */
public class TicketDAO {
    public int contarAsientosOcupados(int idVuelo){
        String sql = "select count(*) as total from tiquetes where id_vuelo = ?";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idVuelo);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al contar asientos ocupados: "+e.getMessage());
        }
        return 0;
    }
    
    public static synchronized boolean insertar(Ticket ticket, int capacidadAvion){
        TicketDAO dao = new TicketDAO();
        int ocupados = dao.contarAsientosOcupados(ticket.getVuelo().getIdVuelo());
        
        if (ocupados >= capacidadAvion) {
            System.out.println("No hay asientos disponibles en este vuelo. ");
            return false;
        }
        
        String sql = "insert into tiquetes (id_vuelo,id_pasajero,fecha_hora_compra,"
                + "numero_asiento) values (?,?,?,?)";
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, ticket.getVuelo().getIdVuelo());
            ps.setInt(2, ticket.getPasajero().getIdPasajero());
            ps.setTimestamp(3, Timestamp.valueOf(ticket.getFechaHoraCompra()));
            ps.setString(4, ticket.getNumeroAsiento());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al insertar ticket: "+e.getMessage());
            return false;
        }
        
    }
    
    public List<Ticket> listar(){
        List<Ticket> lista = new ArrayList<>();
        
         String sql = "select t.id_ticket, t.fecha_hora_compra, t.numero_asiento, "
                + "pa.id_pasajero, pa.cedula, pa.nombre_completo, pa.pasaporte, "
                + "pa.fecha_vencimiento_pasaporte, "
                + "v.id_vuelo, v.pais_destino, v.ciudad_destino, "
                + "v.fecha_hora_salida, v.estado as estado_vuelo, "
                + "a.id_avion, a.modelo, a.capacidad, a.estado as estado_avion, "
                + "ae.id_aerolinea, ae.nombre as nombre_aerolinea, "
                + "p.id_piloto, p.cedula AS cedula_piloto, p.nombre as nombre_piloto, "
                + "p.apellidos, p.fecha_nacimiento, p.estado_civil, "
                + "p.numero_licencia, p.fecha_vencimiento_licencia "
                + "from tiquetes t "
                + "inner join pasajeros pa on t.id_pasajero = pa.id_pasajero "
                + "inner join vuelos v on t.id_vuelo = v.id_vuelo "
                + "inner join aviones a on v.id_avion = a.id_avion "
                + "inner join aerolineas ae on a.id_aerolinea = ae.id_aerolinea "
                + "inner join pilotos p on v.id_piloto = p.id_piloto "
                + "order by t.fecha_hora_compra desc";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {                
                Pasajero pasajero = new Pasajero(
                        rs.getInt("id_pasajero"),
                        rs.getString("cedula"),
                        rs.getString("nombre_completo"),
                        rs.getString("pasaporte"),
                        rs.getDate("fecha_vencimiento_pasaporte").toLocalDate()
                );
                
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
                        rs.getString("cedula_piloto"),
                        rs.getString("nombre_piloto"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("estado_civil"),
                        rs.getString("numero_licencia"),
                        rs.getDate("fecha_vencimiento_licencia").toLocalDate()
                );
                
                Vuelo vuelo = new Vuelo(
                        rs.getInt("id_vuelo"),
                        avion,
                        piloto,
                        rs.getString("pais_destino"),
                        rs.getString("ciudad_destino"),
                        rs.getTimestamp("fecha_hora_salida").toLocalDateTime(),
                        rs.getString("estado_vuelo")
                );
                
                Ticket ticket = new Ticket(
                        rs.getInt("id_ticket"),
                        vuelo,
                        pasajero,
                        rs.getTimestamp("fecha_hora_compra").toLocalDateTime(),
                        rs.getString("numero_asiento")
                );
                lista.add(ticket);
                    
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tickets: "+e.getMessage());
        }
        return lista;
    }
    
}
