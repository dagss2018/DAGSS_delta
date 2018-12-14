package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.TipoUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;




/**
 *
 * @author Juan
 */

@Named(value = "gestionCitasControlador")
@SessionScoped
public class GestionCitasControlador implements Serializable{
    
    private Medico medicoActual;
    private String dni;
    private String numeroColegiado;
    private Cita citaActual;
    private List<Paciente> listaPacientes;
    private List<Cita> listaCitas;
    private Map<Cita,String> agenda;
    
    @EJB
    private MedicoDAO medicoDAO;
    
    @EJB
    private PacienteDAO pacienteDAO;
    
    @EJB
    private CitaDAO citaDAO;
    
    public GestionCitasControlador(){}
    
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }
    
    public Medico getMedicoActual() {
        return medicoActual;
    }

    public void setMedicoActual(Medico medicoActual) {
        this.medicoActual = medicoActual;
    }
    
    public List<Cita> getCitas(){
        return listaCitas;
    }
    
    public Cita getCitaActual(){
        return citaActual;
    }
    
    public void setCitaActual(Cita cita){
        citaActual = cita;
    }

    
    private Medico recuperarDatosMedico() {
        Medico medico = null;
        if (dni != null) {
            medico = medicoDAO.buscarPorDNI(dni);
        }
        if ((medico == null) && (numeroColegiado != null)) {
            medico = medicoDAO.buscarPorNumeroColegiado(numeroColegiado);
        }
        return medico;
    }
    
    
}
