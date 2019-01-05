/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicamentoDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Juan
 */

@Named(value = "gestionPrescripcionesControlador")
@SessionScoped
public class GestionPrescripcionesControlador implements Serializable{

    private Medico medicoActual;
    private Cita citaActual;
    private Prescripcion prescripcionActual;
    private List<Prescripcion> prescripciones;
    //private List<Medicamento> medicamentos;
    
    @Inject
    private PrescripcionDAO prescripcionDAO;
    
    @Inject
    private MedicamentoDAO medicamentoDAO;
    
    @Inject
    private AutenticacionControlador autenticacionControlador;
    
    public GestionPrescripcionesControlador(){}
    
    public String inicializar(Cita cita){   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        this.medicoActual = (Medico) this.autenticacionControlador.getUsuarioActual();
        this.citaActual = cita;
        this.prescripciones = prescripcionDAO.buscarPorPaciente(cita.getPaciente().getId(), dateFormat.format(date));
        //this.medicamentos = medicamentoDAO.buscarTodos();
        
        return "prescripciones";
    } 
    
    public List<Prescripcion> getPrescripciones(){
        return prescripciones;
    }
    
    public Prescripcion getPrescripcionActual(){
        return prescripcionActual;
    }
    
    public Cita getCitaActual(){
        return citaActual;
    }
    public Medico getMedicoActual(){
        return medicoActual;
    }
    
    public void setPrescripcionActual(Prescripcion prescripcion){
        prescripcionActual = prescripcion;
    }
    
    public List<Medicamento> getListadoMedicamentos(){
        return medicamentoDAO.buscarTodos();
    
    }
    
    public void doNuevo(){
        prescripcionActual = new Prescripcion(); 
        prescripcionActual.setPaciente(citaActual.getPaciente());
        prescripcionActual.setMedico(medicoActual);
    }
    
    public void doGuardarNuevo() {
        // Crea un nuevo centro de salud
        prescripcionActual = prescripcionDAO.crear(prescripcionActual);
        // Actualiza lista de centros de salud a mostrar
        prescripciones = prescripcionDAO.buscarTodos();
    }
    
    public void doEliminar(Prescripcion prescripcion) {
        System.out.println(">>> llama a elimina con "+prescripcion);
        prescripcionDAO.eliminar(prescripcion);
        prescripciones = prescripcionDAO.buscarTodos(); // Actualizar lista de centros
    }
}
