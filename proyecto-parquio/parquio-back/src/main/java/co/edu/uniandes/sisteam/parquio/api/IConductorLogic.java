/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.api;

import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IConductorLogic {

    public ConductorEntity getConductorId(Long conductorId);

    public ConductorEntity getConductorCedula(Long cedula);
    
    public List<ConductorEntity> getConductores();

    public ConductorEntity createConductor(ConductorEntity entity) throws BusinessLogicException;

    public ConductorEntity updateConductor(ConductorEntity entity) throws BusinessLogicException;
        
    public void deleteConductor(Long conductorId);

}
