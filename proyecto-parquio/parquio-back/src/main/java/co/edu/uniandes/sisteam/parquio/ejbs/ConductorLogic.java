/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.ejbs;

import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;

import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
import co.edu.uniandes.sisteam.parquio.api.IConductorLogic;
import co.edu.uniandes.sisteam.parquio.persistence.ConductorPersistence;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class ConductorLogic implements IConductorLogic {

    @Inject
    private ConductorPersistence persistence;

    @Override
    public ConductorEntity getConductorId(Long conductorId) {
        return persistence.find(conductorId);
    }

    @Override
    public ConductorEntity getConductorCedula(int cedula) {
        return persistence.findByCedula(cedula);
    }

    @Override
    public List<ConductorEntity> getConductores() {
        return persistence.findAll();
    }

    @Override
    public ConductorEntity createConductor(ConductorEntity entity) throws BusinessLogicException {
           
        ConductorEntity alreadyExistCC = getConductorCedula(entity.getCedula());
        
        if (alreadyExistCC != null) {
            throw new BusinessLogicException("Ya existe un conductor con misma cedula");
        }else {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public ConductorEntity updateConductor(ConductorEntity entity) throws BusinessLogicException {
      
        ConductorEntity alreadyExistCC = getConductorCedula(entity.getCedula());
        
        if (alreadyExistCC == null) {
            throw new BusinessLogicException("No existe un conductor con la cedula dada");
        }else {
           
            persistence.update(alreadyExistCC);
        }
        return entity;
    }

    @Override
    public void deleteConductor(Long conductorid) {
        persistence.delete(conductorid);
    }

    
}
