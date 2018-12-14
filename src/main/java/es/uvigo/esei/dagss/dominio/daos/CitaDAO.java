/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
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
    
    public List<Cita> buscarPorMedicoID(String MedicoId) {
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c "
                                            + "  WHERE c.medico_id = :id", Cita.class);
        q.setParameter("id", MedicoId);
        
        return q.getResultList();
    }
    
}
