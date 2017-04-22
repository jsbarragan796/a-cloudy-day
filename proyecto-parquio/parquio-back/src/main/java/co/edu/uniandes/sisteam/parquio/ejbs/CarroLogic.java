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
package co.edu.uniandes.sisteam.parquio.ejbs;


import co.edu.uniandes.sisteam.parquio.api.ICarroLogic;
import co.edu.uniandes.sisteam.parquio.api.IConductorLogic;
import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;
import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
import co.edu.uniandes.sisteam.parquio.persistence.CarroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class CarroLogic implements ICarroLogic {

    @Inject
    private CarroPersistence persistence;

    @Inject
    private IConductorLogic conductorLogic;

    /**
     * Obtiene la lista de los registros de Carro que pertenecen a una
     * Conductor.
     *
     * @param conductorid id de la Conductor la cual es padre de las Carros.
     * @return Colección de objetos de CarroEntity.
     *
     */
    @Override
    public List<CarroEntity> getCarrosConductor(Long conductorid) {
        ConductorEntity conductor = conductorLogic.getConductorId(conductorid);
        return conductor.getCarros();
    }

    /**
     * Obtiene los datos de una instancia de Carro a partir de su ID.
     *
     * @param carroId Identificador de la Carro a consultar
     * @return Instancia de CarroEntity con los datos de la Carro
     * consultada.
     *
     */
    @Override
    public CarroEntity getCarro(Long carroId) {
        try {
            return persistence.find(carroId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La Carro no existe");
        }
    }

    /**
     * Se encarga de crear una Carro en la base de datos.
     *
     * @param entity Objeto de CarroEntity con los datos nuevos
     * @param conductorid id de la Conductor la cual sera padre de la nueva Carro.
     * @return Objeto de CarroEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public CarroEntity createCarro(Long conductorid, CarroEntity entity) {
        ConductorEntity conductor = conductorLogic.getConductorId(conductorid);
        conductor.add(entity);
        entity.setConductor(conductor);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Carro.
     *
     * @param entity Instancia de CarroEntity con los nuevos datos.
     * @param conductorid id de la Conductor la  cual sera padre de la Carro
     * actualizada.
     * @return Instancia de CarroEntity con los datos actualizados.
     *
     */
    @Override
    public CarroEntity updateCarro(Long conductorid, CarroEntity entity) {
        ConductorEntity conductor = conductorLogic.getConductorId(conductorid);
        entity.setConductor(conductor);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Carro de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param conductorid id de la Conductor la cual es padre de la Carro.
     *
     */
    @Override
    public void deleteCarro(Long id) {
        CarroEntity old = getCarro(id);
        persistence.delete(old.getId());
    }
}
