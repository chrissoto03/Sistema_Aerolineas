/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class Avion {
    private int idAvion;
    private Aerolinea aerolinea;
    private String modelo;
    private int capacidad;
    private String estado;

    public Avion(int idAvion, Aerolinea aerolinea, String modelo, int capacidad, String estado) {
        this.idAvion = idAvion;
        this.aerolinea = aerolinea;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.estado = estado;
    }
    
    public Avion() {
    }
    
    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return modelo + " "+aerolinea.getNombre()+" ";
    }
}
