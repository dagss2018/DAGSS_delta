/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.farmacia;

import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author adri
 */
@Named(value = "gestionPrescripcionesFarmaciasControlador")
@SessionScoped
public class PrescripcionesFarmaciaControlador implements Serializable {

    private String numTarjeta;
    
    private List<Prescripcion> prescrips;
    
    private Paciente paciente;
    
    private Prescripcion prescripcion;
    
    private List<Receta> recetas;

    @EJB
    PrescripcionDAO prescripcionDAO;
    
    @EJB
    PacienteDAO pacienteDAO;
    
    @EJB 
    RecetaDAO recetaDAO;
    
    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public List<Prescripcion> getPrescrips() {
        return prescrips;
    }

    public void setPrescrips(List<Prescripcion> prescrips) {
        this.prescrips = prescrips;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
    
    public boolean parametrosInvalidos(){
        return numTarjeta==null;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }
        
    public String inicializar() {
        String destino = null;
        if (parametrosInvalidos()) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha indicado un numero de tarjeta sanitaria", ""));
        }else{
             this.paciente = pacienteDAO.buscarPorTarjetaSanitaria(this.numTarjeta);
             this.prescrips = prescripcionDAO.getPrescrip(paciente.getId());
             this.recetas = null;
             destino = "listadoPrescripciones";
        }
        return destino;
    }
    public String verRecetas(Prescripcion p){
        String destino = null;
        this.prescripcion = p; 
        this.recetas = p.getRecetas();
        //this.recetas = this.recetaDAO.getRecetas(p.getId());
        this.recetas.forEach((rec) -> {
            System.out.println("Receta: " + rec.toString());
        });
       
        if(this.recetas !=null){
            destino = "listadoRecetas";
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existen recetas para esta prescripción", ""));
        }
        return destino;        
    }
}
