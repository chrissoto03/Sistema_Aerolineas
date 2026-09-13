/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.AerolineaDAO;
import modelo.*;
import java.util.List;

/**
 *
 * @author chris
 */
public class AerolineaLogica {
    private final AerolineaDAO aerolineaDAO = new AerolineaDAO();
    
    public void registrarAerolinea(Aerolinea aerolinea) throws Exception{
        if (aerolinea.getNombre() == null || aerolinea.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la aerolinea es obligatorio. ");
        }
        boolean guardado = aerolineaDAO.insertar(aerolinea);
        if (!guardado) {
            throw new Exception("No se pudo registrar la aerolinea. ");
        }
        
    }
    
    public List<Aerolinea> listarAerolineas(){
        return aerolineaDAO.listar();
    }
    
}
