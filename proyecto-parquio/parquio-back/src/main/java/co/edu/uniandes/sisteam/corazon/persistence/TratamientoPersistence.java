/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author santiago
 */
@Stateless
public class TratamientoPersistence {
    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;
    
    public TratamientoEntity find(Long id) {
        return em.find(TratamientoEntity.class, id);
    }

    public TratamientoEntity create(TratamientoEntity entity) {
        em.persist(entity);
        return entity;
    }

    public TratamientoEntity update(TratamientoEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        TratamientoEntity entity = em.find(TratamientoEntity.class, id);
        em.remove(entity);
    }
}
