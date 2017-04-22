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
import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CarroPersistence {

    private static final Logger LOGGER = Logger.getLogger(CarroPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamParquioPU")
    protected EntityManager em;

    public CarroEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando carro con id={0}", id);
        return em.find(CarroEntity.class, id);
    }
    

    public List<CarroEntity> findAll() {
        LOGGER.info("Consultando todas las carros");
        Query q = em.createQuery("select u from CarroEntity u");
        return q.getResultList();
    }

    public List<CarroEntity> findAllForConductor(Long conductorId) {
        LOGGER.log(Level.INFO, "Consultando todas las carros de conductor id={0}", conductorId);
        TypedQuery q = em.createQuery("select d from CarroEntity d  where d.conductor.id = :conductorId", CarroEntity.class);
        q = q.setParameter("conductorId", conductorId);
        return q.getResultList();
    }

    public CarroEntity create(CarroEntity entity) {
        LOGGER.info("Creando una carro nueva");
        em.persist(entity);
        LOGGER.info("Carro creada");
        return entity;
    }

    public CarroEntity update(CarroEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando carro con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en carros
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando carro con id={0}", id);
        CarroEntity entity = em.find(CarroEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
