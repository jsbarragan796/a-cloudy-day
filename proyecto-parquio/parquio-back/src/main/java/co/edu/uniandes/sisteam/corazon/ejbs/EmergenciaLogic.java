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




import co.edu.uniandes.sisteam.corazon.api.IEmergenciaLogic;
import co.edu.uniandes.sisteam.corazon.api.IMedicionLogic;
import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import co.edu.uniandes.sisteam.corazon.persistence.EmergenciaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class EmergenciaLogic implements IEmergenciaLogic {

    @Inject
    private EmergenciaPersistence persistence;

    @Inject
    private IMedicionLogic medicionLogic;

    /**
     * Obtiene la lista de los registros de Emergencia que pertenecen a una
     * Medicion.
     *
     * @param idMedicion
     * @return Colección de objetos de EmergenciaEntity.
     *
     */
    @Override
    public List<EmergenciaEntity> getEmergenciaMedicion(Long idMedicion) {
        return persistence.findAllInMedicion(idMedicion);
    }

    /**
     * Obtiene los datos de una instancia de Emergencia a partir de su ID.
     *
     * @param emergenciaId Identificador de la Emergencia a consultar
     * @return Instancia de EmergenciaEntity con los datos de la Emergencia
     * consultada.
     *
     */
    @Override
    public EmergenciaEntity getEmergencia(Long emergenciaId) {
        try {
            return persistence.find(emergenciaId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La Emergencia no existe");
        }
    }

   @Override
    public EmergenciaEntity createEmergencia(EmergenciaEntity entity) {
        entity = persistence.create(entity);
        return entity;
    }
    
}

