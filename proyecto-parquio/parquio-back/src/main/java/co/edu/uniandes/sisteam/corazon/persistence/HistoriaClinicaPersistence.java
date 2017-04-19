/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;
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
public class HistoriaClinicaPersistence {

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public HistoriaClinicaEntity getHistoriaClinicaPaciente(Long idPaciente) {
        TypedQuery q = em.createQuery("select u from HistoriaClinicaEntity u where u.paciente.id = :idPaciente", HistoriaClinicaEntity.class);
        q = q.setParameter("idPaciente", idPaciente);
        List<HistoriaClinicaEntity> similarName = q.getResultList();
        if (similarName.isEmpty()) {
            return null;
        } else {
            return similarName.get(0);
        }
    }

    public HistoriaClinicaEntity find(Long id) {
        return em.find(HistoriaClinicaEntity.class, id);
    }

    public HistoriaClinicaEntity create(HistoriaClinicaEntity entity) {
        em.persist(entity);
        return entity;
    }

    public HistoriaClinicaEntity update(HistoriaClinicaEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        HistoriaClinicaEntity entity = em.find(HistoriaClinicaEntity.class, id);
        em.remove(entity);
    }
}
