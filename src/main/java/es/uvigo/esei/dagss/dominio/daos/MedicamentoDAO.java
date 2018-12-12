/*
 Proyecto Java EE, DAGSS-2014
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class MedicamentoDAO extends GenericoDAO<Medicamento> {

    public Medicamento getMedicamento(String id){
        TypedQuery<Medicamento> q = em.createQuery("SELECT p FROM Medicamento AS p WHERE ID = ':id';", Medicamento.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
}
