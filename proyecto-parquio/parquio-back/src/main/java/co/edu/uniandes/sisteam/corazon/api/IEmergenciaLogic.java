/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;

import java.util.List;

/**
 *
 * @author santiago
 */
public interface IEmergenciaLogic {
    
    public EmergenciaEntity getEmergencia(Long id);
    public List<EmergenciaEntity> getEmergenciaMedicion(Long idMedicion);
   public EmergenciaEntity createEmergencia(EmergenciaEntity entity);
    
    
}
