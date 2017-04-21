/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
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
public class ConductorPersistence {

    private static final Logger LOGGER = Logger.getLogger(ConductorPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamParquioPU")
    protected EntityManager em;

    public ConductorEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando conductor con id={0}", id);
        return em.find(ConductorEntity.class, id);
    }

    public ConductorEntity findByCedula(Long cedula) {
        LOGGER.log(Level.INFO, "Consultando conductor con cedula = {0}", cedula);
        TypedQuery<ConductorEntity> q
         = em.createQuery("select u from ConductorEntity u where u.cedula = :cedula", ConductorEntity.class);
        q = q.setParameter("cedula", cedula);

        List<ConductorEntity> conductores = q.getResultList();
        if (conductores.isEmpty()) {
            return null;
        } else {
            return conductores.get(0);
        }
    }

    public List<ConductorEntity> findAll() {
        LOGGER.info("Consultando todos los conductores");
        Query q = em.createQuery("select u from ConductorEntity u");
        return q.getResultList();
    }

    public ConductorEntity create(ConductorEntity entity) {
        LOGGER.log(Level.INFO, "Creando un conductor nuevo {0}", entity.getNombres());
        em.persist(entity);

        return entity;
    }

    public ConductorEntity update(ConductorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando conductor con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando conductor con id={0}", id);
        ConductorEntity entity = em.find(ConductorEntity.class, id);
        em.remove(entity);
    }

}
