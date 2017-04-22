/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.ejbs;

import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;

import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
import co.edu.uniandes.sisteam.parquio.api.IParqueaderoLogic;
import co.edu.uniandes.sisteam.parquio.persistence.ParqueaderoPersistence;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class ParqueaderoLogic implements IParqueaderoLogic {

    @Inject
    private ParqueaderoPersistence persistence;

    @Override
    public ParqueaderoEntity getParqueaderoId(Long parqueaderoId) {
        return persistence.find(parqueaderoId);
    }

    @Override
    public List<ParqueaderoEntity> getParqueaderos() {
        return persistence.findAll();
    }

    @Override
    public ParqueaderoEntity createParqueadero(ParqueaderoEntity entity) throws BusinessLogicException 
    {
        return persistence.create(entity);
    }

    @Override
    public ParqueaderoEntity updateParqueadero(ParqueaderoEntity entity) throws BusinessLogicException 
    {
           return persistence.update(entity);
        
    }

    @Override
    public void deleteParqueadero(Long parqueaderoid) 
    {
        persistence.delete(parqueaderoid);
    }

}
