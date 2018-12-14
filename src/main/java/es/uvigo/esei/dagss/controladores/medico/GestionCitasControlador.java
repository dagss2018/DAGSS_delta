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
    private Cita cita;
    private Paciente paciente;
    private List<Cita> listaCitas;
    private Map<Cita,String> agenda;
    
    @EJB
    private MedicoDAO medicoDAO;
    
    @EJB
    private PacienteDAO pacienteDAO;
    
    @EJB
    private CitaDAO citaDAO;
    
    public GestionCitasControlador(){}
    
    
    
}
