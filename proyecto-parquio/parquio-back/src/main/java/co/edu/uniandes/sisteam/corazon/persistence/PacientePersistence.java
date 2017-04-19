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
package co.edu.uniandes.sisteam.corazon.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class PacientePersistence {

    private static final Logger LOGGER = Logger.getLogger(PacientePersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public PacienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando paciente con id={0}", id);
        return em.find(PacienteEntity.class, id);
    }

    public PacienteEntity findByCedula(int cedula) {
        LOGGER.log(Level.INFO, "Consultando paciente con cedula = {0}", cedula);
        TypedQuery<PacienteEntity> q
                = em.createQuery("select u from PacienteEntity u where u.cedula = :cedula", PacienteEntity.class);
        q = q.setParameter("cedula", cedula);

        List<PacienteEntity> pacientesSimilarCedula = q.getResultList();
        if (pacientesSimilarCedula.isEmpty()) {
            return null;
        } else {
            return pacientesSimilarCedula.get(0);
        }
    }

    public List<PacienteEntity> findAll() {
    //    LOGGER.info("Consultando todos los pacientes");
        Query q = em.createQuery("select u from PacienteEntity u");
        return q.getResultList();
    }

    public PacienteEntity create(PacienteEntity entity) {
        LOGGER.info("Creando un paciente nuevo " + entity.getNombres());
        em.persist(entity);

        return entity;
    }

    public PacienteEntity update(PacienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando paciente con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando paciente con id={0}", id);
        PacienteEntity entity = em.find(PacienteEntity.class, id);
        em.remove(entity);
    }
}
