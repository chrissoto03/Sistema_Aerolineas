/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author chris
 */
public class Aerolinea {
    private int idAerolinea;
    private String nombre;

    public Aerolinea(int idAerolinea, String nombre) {
        this.idAerolinea = idAerolinea;
        this.nombre = nombre;
    }
    
    public Aerolinea() {
        
    }
    
    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Aerolinea otra = (Aerolinea) obj;
        return this.idAerolinea == otra.idAerolinea;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idAerolinea);
    }
}
