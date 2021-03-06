package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.TipoUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;




/**
 *
 * @author Juan
 */

@Named(value = "gestionCitasMedicoControlador")
@SessionScoped
public class GestionCitasControlador implements Serializable{
    
    private Medico medicoActual;
    private Cita citaActual;
    private List<Cita> citas;
    
    @Inject
    private CitaDAO citaDAO;
    
    @Inject
    private AutenticacionControlador autenticacionControlador;
    
    @Inject
    private GestionPrescripcionesControlador prescripcionesControlador;
     
     
    public GestionCitasControlador(){}
    
    
    public Medico getMedicoActual() {
        return medicoActual;
    }

    public void setMedicoActual(Medico medicoActual) {
        this.medicoActual = medicoActual;
    }
    
    public List<Cita> getCitas(){
        return citas;
    }
    
    public Cita getCitaActual(){
        return citaActual;
    }
    
    public void setCitaActual(Cita cita){
        citaActual = cita;
    }

    public boolean parametrosInvalidos(){
        return medicoActual==null;
    }
    
    public void doVer(Cita cita) {
        citaActual = cita;   // Otra alternativa: volver a refrescarlos desde el DAO
    }
    
    public String inicializar(){        
        this.medicoActual = (Medico) this.autenticacionControlador.getUsuarioActual();
        System.out.println(medicoActual.getId());
        this.citas = this.citaDAO.buscarPorMedicoID(medicoActual.getId());
        
        return "agenda";
    } 
    
    public boolean esPendiente(Cita cita){
        if(cita.getEstado().equals(EstadoCita.PLANIFICADA)) return true;
        else return false;
    }
    
    public void doCompletar(){
        citaActual = this.citaDAO.actualizarCita(citaActual.getId(), EstadoCita.COMPLETADA);
        this.citas = this.citaDAO.buscarPorMedicoID(medicoActual.getId());
    }
    
    public void doAusente(){
        citaActual = this.citaDAO.actualizarCita(citaActual.getId(), EstadoCita.AUSENTE);
        System.out.println("ID: "+citaActual.getId());
        System.out.println("Estado: "+EstadoCita.AUSENTE);
        this.citas = this.citaDAO.buscarPorMedicoID(medicoActual.getId());
    }
    
    public String inicializarPrescripcion(){
        return prescripcionesControlador.inicializar(citaActual);
    }
    
    public String doVolver() {
        return "../index?faces-redirect=true";
    }
    
}
