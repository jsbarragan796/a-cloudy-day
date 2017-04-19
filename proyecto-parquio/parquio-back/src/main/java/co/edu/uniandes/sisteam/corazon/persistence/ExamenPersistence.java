/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.ExamenEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author santiago
 */
@Stateless
public class ExamenPersistence {
    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;
    
    public ExamenEntity find(Long id) {
        return em.find(ExamenEntity.class, id);
    }

    public ExamenEntity create(ExamenEntity entity) {
        em.persist(entity);
        return entity;
    }

    public ExamenEntity update(ExamenEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ExamenEntity entity = em.find(ExamenEntity.class, id);
        em.remove(entity);
    }
}
