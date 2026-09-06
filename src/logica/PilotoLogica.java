/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.PilotoDAO;
import java.time.LocalDate;
import modelo.Piloto;
import java.util.List;
import java.time.Period;

/**
 *
 * @author chris
 */
public class PilotoLogica {
    private final PilotoDAO pilotoDAO = new PilotoDAO();
    
    public void registrarPiloto(Piloto piloto) throws Exception{
        validarDatos(piloto);
        boolean guardado = pilotoDAO.insertar(piloto);
        if (!guardado) {
            throw new Exception("No se puedo insertar el piloto. Intente de nuevo. ");
        }
    }
    
    public void modificarPiloto(Piloto piloto) throws Exception{
        validarDatos(piloto);
        boolean actualizar = pilotoDAO.modificar(piloto);
        if (!actualizar) {
            throw new Exception("No se pudo modificar piloto. ");
        }
    }
    
    public List<Piloto> listarPilotos(){
        return pilotoDAO.listar();
    }
    
    public List<Piloto> listarPilotosConLicenciaVigente(){
        return pilotoDAO.listarConLicenciaVigente();
    }
    
    private void validarDatos(Piloto piloto) throws Exception{
        if (piloto.getCedula() == null || piloto.getCedula().trim().isEmpty()) {
            throw new Exception("Debe ingresar la cedula. ");
        }
        if (piloto.getNombre() == null || piloto.getNombre().trim().isEmpty()) {
            throw new Exception("Debe ingresar un nombre. ");
        }
        if (piloto.getApellidos() == null || piloto.getApellidos().trim().isEmpty()) {
            throw new Exception("Debe ingresar apellidos.");
        }
        if (piloto.getFechaNacimiento() == null || Period.between(piloto.getFechaNacimiento(), LocalDate.now()).getYears() < 18) {
            throw new Exception("El piloto debe ser mayor de edad. ");
        }
        if (piloto.getEstadoCivil() == null || piloto.getEstadoCivil().trim().isEmpty()) {
            throw new Exception("Debe ingresar el estado civil. ");
        }
        if (piloto.getNumeroLicencia() == null || piloto.getNumeroLicencia().trim().isEmpty()) {
            throw new Exception("Debe ingresar el numero de licencia. ");
        }
        if (piloto.getFechaVencimientoLicencia() == null) {
            throw new Exception("Debe ingresar la fecha de vencimiento de la licencia. ");
        }
    }
    
}
