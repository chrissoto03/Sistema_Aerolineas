/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.Piloto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class PilotoDAO {
    public boolean insertar(Piloto piloto){
        String sql = "insert into pilotos(cedula,nombre,apellidos,fecha_nacimiento,"
                + "estado_civil,numero_licencia, fecha_vencimiento_licencia)"
                + "values (?,?,?,?,?,?,?)";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, piloto.getCedula());
            ps.setString(2, piloto.getNombre());
            ps.setString(3, piloto.getApellidos());
            ps.setDate(4, Date.valueOf(piloto.getFechaNacimiento()));
            ps.setString(5, piloto.getEstadoCivil());
            ps.setString(6, piloto.getNumeroLicencia());
            ps.setDate(7, Date.valueOf(piloto.getFechaVencimientoLicencia()));
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas >0;
        } catch (SQLException e) {
            System.out.println("Error al insertar piloto: "+e.getMessage());
            return false;
        }
    }
    
    public boolean modificar(Piloto piloto){
        String sql = "update pilotos set cedula = ?, nombre= ?,apellidos = ?,"
                + "fecha_nacimiento =?, estado_civil =?,numero_licencia = ?,"
                + "fecha_vencimiento_licencia = ? where id_piloto = ?";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, piloto.getCedula());
            ps.setString(2, piloto.getNombre());
            ps.setString(3, piloto.getApellidos());
            ps.setDate(4, Date.valueOf(piloto.getFechaNacimiento()));
            ps.setString(5, piloto.getEstadoCivil());
            ps.setString(6, piloto.getNumeroLicencia());
            ps.setDate(7, Date.valueOf(piloto.getFechaVencimientoLicencia()));
            ps.setInt(8, piloto.getIdPiloto());
            
            int filaAfectadas = ps.executeUpdate();
            return filaAfectadas >0;
            
        } catch (SQLException e) {
            System.out.println("Error al modificar piloto: "+e.getMessage());
            return false;
        }
    }
    
    public List<Piloto> listar(){
        List<Piloto> lista = new ArrayList<>();
        String sql = "select id_piloto ,cedula,nombre,apellidos,fecha_nacimiento,"
                + "estado_civil,numero_licencia, fecha_vencimiento_licencia "
                + "from pilotos order by apellidos, nombre";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {                
                Piloto p = new Piloto(
                        rs.getInt("id_piloto"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("estado_civil"),
                        rs.getString("numero_licencia"),
                        rs.getDate("fecha_vencimiento_licencia").toLocalDate()
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar piloto: "+e.getMessage());
        }
        return lista;
    }
    
    public List<Piloto> listarConLicenciaVigente(){
        List<Piloto> lista= new ArrayList<>();
        String sql ="select id_piloto,cedula,nombre,apellidos,fecha_nacimiento,"
                + "estado_civil,numero_licencia,fecha_vencimiento_licencia "
                + "from pilotos where fecha_vencimiento_licencia >= curdate() "
                + "order by apellidos, nombre";
        
        try (Connection con = ConexionDB.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {                
                Piloto p = new Piloto(
                        rs.getInt("id_piloto"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("estado_civil"),
                        rs.getString("numero_licencia"),
                        rs.getDate("fecha_vencimiento_licencia").toLocalDate()
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pilotos con licencia vigente: "+e.getMessage());
        }
        return lista;
    }
}
