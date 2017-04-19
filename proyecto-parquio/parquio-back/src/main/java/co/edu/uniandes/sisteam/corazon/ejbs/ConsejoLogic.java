/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.ejbs;

import co.edu.uniandes.sisteam.corazon.api.IConsejoLogic;
import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.corazon.persistence.ConsejoPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.MedicoPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.PacientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author santiago
 */
@Stateless
public class ConsejoLogic implements IConsejoLogic {

    @Inject
    private ConsejoPersistence persistence;

    @Inject
    private MedicoPersistence medicoPersistence;

    @Inject
    private PacientePersistence pacientePersistence;

    @Override
    public List<ConsejoEntity> getConsejosPaciente(Long idPaciente) {
        return persistence.getConsejosPaciente(idPaciente);
    }

    @Override
    public List<ConsejoEntity> getConsejosMedico(Long idMedico) {
        return persistence.getConsejosMedico(idMedico);
    }

    @Override
    public ConsejoEntity createConsejo(Long idPaciente, Long idMedico, ConsejoEntity consejo) throws BusinessLogicException {
        PacienteEntity paciente = pacientePersistence.find(idPaciente);
        MedicoEntity medico = medicoPersistence.find(idMedico);

        if (medico == null) {
            throw new BusinessLogicException("No existe el medico que entra por parametro");
        } else if (paciente == null) {
            throw new BusinessLogicException("No existe el paciente que entra por parametro");
        } else {
            consejo.setMedico(medico);
            consejo.setPaciente(paciente);
            persistence.create(consejo);
        }
        return consejo;
    }

    @Override
    public void deleteConsejo(Long id) {
        persistence.delete(id);
    }

    @Override
    public ConsejoEntity getConsejo(Long id) {
        return persistence.find(id);
    }

}
