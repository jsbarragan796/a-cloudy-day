/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.api;

import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IParqueaderoLogic {

    public ParqueaderoEntity getParqueaderoId(Long parqueaderoId);
    
    public List<ParqueaderoEntity> getParqueaderos();

    public ParqueaderoEntity createParqueadero(ParqueaderoEntity entity) throws BusinessLogicException;

    public ParqueaderoEntity updateParqueadero(ParqueaderoEntity entity) throws BusinessLogicException;
        
    public void deleteParqueadero(Long parqueaderoId);

}
