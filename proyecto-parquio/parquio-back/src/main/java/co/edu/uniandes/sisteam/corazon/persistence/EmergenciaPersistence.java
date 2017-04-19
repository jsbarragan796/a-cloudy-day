
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class EmergenciaPersistence {

    private static final Logger LOGGER = Logger.getLogger(EmergenciaPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public EmergenciaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando emergencia con id={0}", id);
        return em.find(EmergenciaEntity.class, id);
    }
    
    

    public List<EmergenciaEntity> findAll() {
        LOGGER.info("Consultando todas las emergencias");
        Query q = em.createQuery("select u from EmergenciaEntity u");
        return q.getResultList();
    }

    public List<EmergenciaEntity> findAllInMedicion(Long medicionId) {
        LOGGER.log(Level.INFO, "Consultando todas las emergencias de la medicion id={0}", medicionId);
        TypedQuery q = em.createQuery("select d from EmergenciaEntity d  where d.medicion.id = :medicionId", EmergenciaEntity.class);
        q = q.setParameter("medicionId", medicionId);
        return q.getResultList();
    }

    public EmergenciaEntity create(EmergenciaEntity entity) {
        LOGGER.info("Creando una emergencia nueva");
        em.persist(entity);
        LOGGER.info("Emergencia creada");
        return entity;
    }

    public EmergenciaEntity update(EmergenciaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando emergencia con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en emergencias
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando emergencia con id={0}", id);
        EmergenciaEntity entity = em.find(EmergenciaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
