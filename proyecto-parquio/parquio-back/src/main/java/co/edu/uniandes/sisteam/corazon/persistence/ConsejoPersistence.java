/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author santiago
 */
@Stateless
public class ConsejoPersistence {

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public ConsejoEntity find(Long id) {
        return em.find(ConsejoEntity.class, id);
    }

    public ConsejoEntity create(ConsejoEntity entity) {
        em.persist(entity);
        return entity;
    }

    public ConsejoEntity update(ConsejoEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ConsejoEntity entity = em.find(ConsejoEntity.class, id);
        em.remove(entity);
    }

    public List<ConsejoEntity> getConsejosPaciente(Long idPaciente) {
        TypedQuery q = em.createQuery("select u from ConsejoEntity u where u.paciente.id = :idPaciente", ConsejoEntity.class);
        q = q.setParameter("idPaciente", idPaciente);
        return q.getResultList();
    }
    
    public List<ConsejoEntity> getConsejosMedico(Long idMedico) {
        TypedQuery q = em.createQuery("select u from ConsejoEntity u where u.medico.id = :idMedico", ConsejoEntity.class);
        q = q.setParameter("idMedico", idMedico);
        return q.getResultList();
    }
}
