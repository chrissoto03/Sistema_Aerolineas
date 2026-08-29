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
public class Vuelo {
    private int idVuelo;
    private Avion avion;
    private Piloto piloto;
    private String paisDestino;
    private String ciudadDestino;
    private LocalDate fechaHoraSalida;
    private String estado;

    public Vuelo(int idVuelo, Avion avion, Piloto piloto, String paisDestino, String ciudadDestino, LocalDate fechaHoraSalida, String estado) {
        this.idVuelo = idVuelo;
        this.avion = avion;
        this.piloto = piloto;
        this.paisDestino = paisDestino;
        this.ciudadDestino = ciudadDestino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.estado = estado;
    }
    
    public Vuelo(){
        
    }
    
    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public LocalDate getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDate fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
