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
package co.edu.uniandes.sisteam.corazon.ejbs;

import co.edu.uniandes.sisteam.corazon.persistence.MedicionPersistence;
import co.edu.uniandes.sisteam.corazon.api.IMedicionLogic;
import co.edu.uniandes.sisteam.corazon.api.IPacienteLogic;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class MedicionLogic implements IMedicionLogic {

    @Inject
    private MedicionPersistence persistence;

    @Inject
    private IPacienteLogic pacienteLogic;

    /**
     * Obtiene la lista de los registros de Medicion que pertenecen a una
     * Paciente.
     *
     * @param pacienteid id de la Paciente la cual es padre de las Mediciones.
     * @param fechaInicio fecha de inicio del rango de busqueda.
     * @param fechaFin fecha de fin del rango de busqueda.
     *
     * @return Colección de objetos de MedicionEntity.
     *
     */
    @Override
    public List<MedicionEntity> getMedicionesDePaciente(Long pacienteid, String fechaInicio, String fechaFin) {
        if (fechaInicio.equals("null") && fechaFin.equals("null")) {
            return persistence.findAllForPaciente(pacienteid);
        }
        else {
           return persistence.findForPacienteByDates(pacienteid, Date.valueOf(fechaInicio), Date.valueOf(fechaFin));
        }
    }

    /**
     * Obtiene la lista de los registros de Medicion que pertenecen a una
     * Paciente.
     *
     * @param pacienteid id de la Paciente la cual es padre de las Mediciones.
     * @return Colección de objetos de MedicionEntity.
     *
     */
    @Override
    public List<MedicionEntity> getMedicionesTodas() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Medicion a partir de su ID.
     *
     * @param medicionId Identificador de la Medicion a consultar
     * @return Instancia de MedicionEntity con los datos de la Medicion
     * consultada.
     *
     */
    @Override
    public MedicionEntity getMedicion(Long medicionId) {
        try {
            return persistence.find(medicionId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La Medicion no existe");
        }
    }

    /**
     * Se encarga de crear una Medicion en la base de datos.
     *
     * @param entity Objeto de MedicionEntity con los datos nuevos
     * @param pacienteid id de la Paciente la cual sera padre de la nueva
     * Medicion.
     * @return Objeto de MedicionEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public MedicionEntity createMedicion(MedicionEntity entity) {

        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Medicion.
     *
     * @param entity Instancia de MedicionEntity con los nuevos datos.
     * @param pacienteid id de la Paciente la cual sera padre de la Medicion
     * actualizada.
     * @return Instancia de MedicionEntity con los datos actualizados.
     *
     */
    @Override
    public MedicionEntity updateMedicion(MedicionEntity entity) {

        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Medicion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param pacienteid id de la Paciente la cual es padre de la Medicion.
     *
     */
    @Override
    public void deleteMedicion(Long id) {
        MedicionEntity old = getMedicion(id);
        persistence.delete(old.getId());
    }
}
