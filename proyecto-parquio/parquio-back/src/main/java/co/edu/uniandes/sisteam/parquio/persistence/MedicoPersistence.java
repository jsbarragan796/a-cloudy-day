/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.parquio.entities.MedicoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class MedicoPersistence {

    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public MedicoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medico con id={0}", id);
        return em.find(MedicoEntity.class, id);
    }

    public MedicoEntity findByCedula(int cedula) {
        LOGGER.log(Level.INFO, "Consultando medico con cedula = {0}", cedula);
        TypedQuery<MedicoEntity> q
         = em.createQuery("select u from MedicoEntity u where u.cedula = :cedula", MedicoEntity.class);
        q = q.setParameter("cedula", cedula);

        List<MedicoEntity> medicos = q.getResultList();
        if (medicos.isEmpty()) {
            return null;
        } else {
            return medicos.get(0);
        }
    }

    public MedicoEntity findByTarjetaProfesional(String nTarjetaProfesional) {
        LOGGER.log(Level.INFO, "Consultando medico con número de tarjeta profesional = {0}", nTarjetaProfesional);
        TypedQuery<MedicoEntity> q
         = em.createQuery("select u from MedicoEntity u where u.tarjetaProfesional = :nTarjetaProfesional", MedicoEntity.class);
        q = q.setParameter("nTarjetaProfesional", nTarjetaProfesional);

        List<MedicoEntity> medicos = q.getResultList();
        if (medicos.isEmpty()) {
            return null;
        } else {
            return medicos.get(0);
        }
    }

    public List<MedicoEntity> findAll() {
        LOGGER.info("Consultando todos los medicos");
        Query q = em.createQuery("select u from MedicoEntity u");
        return q.getResultList();
    }

    public MedicoEntity create(MedicoEntity entity) {
        LOGGER.log(Level.INFO, "Creando un medico nuevo {0}", entity.getNombres());
        em.persist(entity);

        return entity;
    }

    public MedicoEntity update(MedicoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medico con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medico con id={0}", id);
        MedicoEntity entity = em.find(MedicoEntity.class, id);
        em.remove(entity);
    }

}
