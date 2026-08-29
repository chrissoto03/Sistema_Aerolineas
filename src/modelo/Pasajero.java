/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class Pasajero {
    private String idPasajero;
    private String cedula;
    private String nombreCompleto;
    private String pasaporte;
    private LocalDate fechaVencimientoPasaporte;

    public Pasajero(String idPasajero, String cedula, String nombreCompleto, String pasaporte, LocalDate fechaVencimientoPasaporte) {
        this.idPasajero = idPasajero;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.pasaporte = pasaporte;
        this.fechaVencimientoPasaporte = fechaVencimientoPasaporte;
    }
    
    public Pasajero(){
        
    }
    
    public String getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(String idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public LocalDate getFechaVencimientoPasaporte() {
        return fechaVencimientoPasaporte;
    }

    public void setFechaVencimientoPasaporte(LocalDate fechaVencimientoPasaporte) {
        this.fechaVencimientoPasaporte = fechaVencimientoPasaporte;
    }
    
    
}
