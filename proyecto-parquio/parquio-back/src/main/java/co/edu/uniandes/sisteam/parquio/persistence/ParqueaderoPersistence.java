/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;
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
public class ParqueaderoPersistence {

    private static final Logger LOGGER = Logger.getLogger(ParqueaderoPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamParquioPU")
    protected EntityManager em;

    public ParqueaderoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando parqueadero con id={0}", id);
        return em.find(ParqueaderoEntity.class, id);
    }

    public List<ParqueaderoEntity> findAll() {
        LOGGER.info("Consultando todos los parqueaderos");
        Query q = em.createQuery("select u from ParqueaderoEntity u");
        return q.getResultList();
    }

    public ParqueaderoEntity create(ParqueaderoEntity entity) {
        LOGGER.log(Level.INFO, "Creando un parqueadero nuevo {0}", entity.getNombre());
        em.persist(entity);

        return entity;
    }

    public ParqueaderoEntity update(ParqueaderoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando parqueadero con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando parqueadero con id={0}", id);
        ParqueaderoEntity entity = em.find(ParqueaderoEntity.class, id);
        em.remove(entity);
    }

}
