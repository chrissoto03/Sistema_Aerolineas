/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author chris
 */
public class Ticket {
    private int idTicket;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private LocalDateTime fechaHoraCompra;
    private String numeroAsiento;

    public Ticket(int idTicket, Vuelo vuelo, Pasajero pasajero, LocalDateTime fechaHoraCompra, String numeroAsiento) {
        this.idTicket = idTicket;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.fechaHoraCompra = fechaHoraCompra;
        this.numeroAsiento = numeroAsiento;
    }
    
    public Ticket(){
        
    }
    
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public LocalDateTime getFechaHoraCompra() {
        return fechaHoraCompra;
    }

    public void setFechaHoraCompra(LocalDateTime fechaHoraCompra) {
        this.fechaHoraCompra = fechaHoraCompra;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }
    
    
}
