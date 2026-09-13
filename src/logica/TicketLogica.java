/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.*;
import modelo.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author chris
 */
public class TicketLogica {
    private final TicketDAO ticketDAO = new TicketDAO();
    private final PasajeroDAO pasajeroDAO = new PasajeroDAO();
    
    public void comprarTicket(String cedulaPasajero, String nombreCompleto, String pasaporte,
            LocalDate fechaVencimientoPasaporte, Vuelo vuelo, String numeroAsiento) throws Exception{
        validarDatosBasicos(cedulaPasajero, nombreCompleto, pasaporte, 
                fechaVencimientoPasaporte, vuelo, numeroAsiento);
        if (fechaVencimientoPasaporte.isBefore(LocalDate.now())) {
            throw new Exception("El pasaporte del pasajero esta vencido. ");
        }
        if (!"Programado".equals(vuelo.getEstado())) {
            throw new Exception("No se pueden vender tiquetes para un vuelo que no este programado. ");
        }
        
        Pasajero pasajero = pasajeroDAO.buscarPorCedula(cedulaPasajero);
        if (pasajero == null) {
            pasajero = new Pasajero();
            pasajero.setCedula(cedulaPasajero);
            pasajero.setNombreCompleto(nombreCompleto);
            pasajero.setPasaporte(pasaporte);
            pasajero.setFechaVencimientoPasaporte(fechaVencimientoPasaporte);
            
            boolean pasajeroGuardado = pasajeroDAO.insertar(pasajero);
            if (!pasajeroGuardado) {
                throw new Exception("No se pudo registrar pasajero");
            }
            
            pasajero = pasajeroDAO.buscarPorCedula(cedulaPasajero);
        }
        
        Ticket ticket = new Ticket();
        ticket.setVuelo(vuelo);
        ticket.setPasajero(pasajero);
        ticket.setFechaHoraCompra(LocalDateTime.now());
        ticket.setNumeroAsiento(numeroAsiento);
        boolean vendido = TicketDAO.insertar(ticket, vuelo.getAvion().getCapacidad());
        if (!vendido) {
            throw new Exception("No hay asientos disponibles en este vuelo, o el asiento ya fue vendido. ");
        }
    }  
    
    public List<Ticket> listarTickets(){
        return ticketDAO.listar();
    }
    
    private void validarDatosBasicos(String cedula,String nombreCompleto, String pasaporte,
            LocalDate fechaVencimientoPasaporte, Vuelo vuelo, String numeroAsiento) throws Exception{
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new Exception("Debe ingresar la cedula del pasajero. ");
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre completo del pasajero. ");
        }
        if (pasaporte == null || pasaporte.trim().isEmpty()) {
            throw new Exception("Debe ingresar el numero de pasaporte. ");
        }
        if (fechaVencimientoPasaporte == null) {
            throw new Exception("Debe ingresar la fecha de vencimiento del pasaporte. ");
        }
        if (vuelo == null) {
            throw new Exception("Debe seleccionar un vuelo.");
        }
        if (numeroAsiento == null || numeroAsiento.trim().isEmpty()) {
            throw new Exception("Debe ingresar el numero de asiento. ");
        }
    }
}
