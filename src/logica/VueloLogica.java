/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.VueloDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import modelo.Vuelo;
import java.util.List;

/**
 *
 * @author chris
 */
public class VueloLogica {
    private final VueloDAO vueloDAO = new VueloDAO();
    
    public void registrarVuelo(Vuelo vuelo) throws Exception{
        vuelo.setEstado("Programado");
        validarDatos(vuelo);
        boolean ingresar = vueloDAO.insertar(vuelo);
        if (!ingresar) {
            throw new Exception("No se pudo registrar el vuelo. Intente de nuevo. ");
        }
    }
    
    public void cancelarVuelo(int idVuelo) throws Exception{
        boolean actualizadar = vueloDAO.cambiarEstado(idVuelo, "Cancelado");
        if (!actualizadar) {
            throw new Exception("No se puedo cancelar el vuelo. Intente de nuevo. ");
        }
    }
    public void finalizarVuelo(int idVuelo) throws Exception{
        boolean actualizado = vueloDAO.cambiarEstado(idVuelo, "Finalizado");
        if (!actualizado) {
            throw new Exception("No se pudo finalizar el vuelo. Intente de nuevo. ");
        }
    }
    
    public List<Vuelo> listarVuelos(){
        return vueloDAO.listar();
    }
    
    public List<Vuelo> listarVuelosProgramados(){
        return vueloDAO.listarProgramados();
    }
    
    private void validarDatos(Vuelo vuelo) throws Exception{
        if (vuelo.getAvion() == null) {
            throw new Exception("Debe seleccionar un avion. ");
        }
        if (!"Activo".equals(vuelo.getAvion().getEstado())) {
            throw new Exception("Solo se pueden seleccionar los aviones con estado 'Activo'. ");
        }
        if (vuelo.getPiloto() == null) {
            throw new Exception("Debe seleccionar un piloto. ");
        }
        if (vuelo.getPiloto().getFechaVencimientoLicencia().isBefore(java.time.LocalDate.now())) {
            throw new Exception("No se puede agregar un piloto con la cedula vencida. ");
        }
        if (vuelo.getPaisDestino() == null || vuelo.getPaisDestino().trim().isEmpty()) {
            throw new Exception("El pais de destino es obligatorio. ");
        }
        if (vuelo.getCiudadDestino() == null || vuelo.getCiudadDestino().trim().isEmpty()) {
            throw new Exception("La ciudad de destino es obligatorio. ");
        }
        if (vuelo.getFechaHoraSalida() == null || !vuelo.getFechaHoraSalida().isAfter(LocalDateTime.now())) {
            throw new Exception("La fecha y hora de salida debe ser posterior a la fecha actual. ");
        }
    }
}
