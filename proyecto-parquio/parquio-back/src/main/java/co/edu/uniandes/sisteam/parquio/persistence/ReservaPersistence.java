/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sisteam.parquio.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ReservaPersistence {

    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamParquioPU")
    protected EntityManager em;

    public ReservaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }
    

    public List<ReservaEntity> findAll() {
        LOGGER.info("Consultando todas las reservas");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }

    public List<ReservaEntity> findAllForConductor(Long conductorId) {
        LOGGER.log(Level.INFO, "Consultando todas las reservas de conductor id={0}", conductorId);
        TypedQuery q = em.createQuery("select d from ReservaEntity d  where d.conductor.id = :conductorId", ReservaEntity.class);
        q = q.setParameter("conductorId", conductorId);
        return q.getResultList();
    }

    public ReservaEntity create(ReservaEntity entity) {
        LOGGER.info("Creando una reserva nueva");
        em.persist(entity);
        LOGGER.info("Reserva creada");
        return entity;
    }

    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando reserva con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en reservas
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
