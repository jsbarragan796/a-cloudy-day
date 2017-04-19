/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author santiago
 */
public interface IConsejoLogic {
    
    public List<ConsejoEntity> getConsejosPaciente(Long idPaciente);
    
    public List<ConsejoEntity> getConsejosMedico(Long idMedico);
    
    public ConsejoEntity createConsejo(Long idPaciente, Long idMedico, ConsejoEntity consejo) throws BusinessLogicException;
    
    public void deleteConsejo(Long id);
    
    public ConsejoEntity getConsejo(Long id);
}
