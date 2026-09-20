/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.AvionDAO;
import modelo.Avion;
import java.util.List;

/**
 *
 * @author chris
 */
public class AvionLogica {
    private final AvionDAO avionDAO = new AvionDAO();
    
    public void registrarAvion(Avion avion)throws Exception{
        validarDatos(avion);
        boolean guardado = avionDAO.insertar(avion);
        if (!guardado) {
            throw new Exception("No se pudo registrar el avion. Intente de nuevo. ");
        }
    }
    
    public void modificarAvion(Avion avion) throws Exception{
        validarDatos(avion);
        boolean modificado = avionDAO.modificarAvion(avion);
        if (!modificado) {
            throw new Exception("No se puedo modiicar el avion. Intente de nuevo. ");
        }
    }
    
    public List<Avion> listarAviones(){
        return avionDAO.listar();
    }
    
    public List<Avion> listarAvionesActivos(){
        return avionDAO.listarActivos();
    }
    
    private void validarDatos(Avion avion)throws Exception{
        if (avion.getAerolinea() == null) {
            throw new Exception("Debe seleccionar una aerolinea.");
        }
        if (avion.getModelo() == null || avion.getModelo().trim().isEmpty()) {
            throw new Exception("Es obligatorio seleccionar el modelo del avion. ");
        }
        if (avion.getCapacidad() < 0) {
            throw new Exception("La capacidad debe ser mayor a 0. ");
        }
        if (avion.getEstado() == null || avion.getEstado().trim().isEmpty()) {
            throw new Exception("Debe seleccionar un estado (Activo/Inactivo). ");
        }
        if (!"Activo".equals(avion.getEstado()) && !"Inactivo".equals(avion.getEstado())) {
            throw new Exception("Debe seleccionar un estado válido (Activo/Inactivo).");
        }
    }
}
