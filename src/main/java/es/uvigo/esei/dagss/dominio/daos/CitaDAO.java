/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class CitaDAO  extends GenericoDAO<Cita>{    

    // Completar aqui
    public Cita buscarPorID(String id) {
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c "
                                            + "  WHERE c.id = :id", Cita.class);
        q.setParameter("id", id);

        return filtrarResultadoUnico(q);
    }
    
    public List<Cita> buscarPorMedicoID(Long medicoId) {
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c JOIN FETCH c.medico  WHERE c.medico.id = :id"
               , Cita.class);
        q.setParameter("id", medicoId);
        
        return q.getResultList();
    }
    
    public Cita actualizarCita(Long citaId, EstadoCita estado){
        Cita cita = buscarPorId(citaId);
        cita.setEstado(estado);
        return actualizar(cita);
    }
    
}
