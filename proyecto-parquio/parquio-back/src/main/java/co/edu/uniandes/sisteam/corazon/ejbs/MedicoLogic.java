/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.ejbs;

import co.edu.uniandes.sisteam.corazon.api.IMedicoLogic;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.corazon.persistence.MedicoPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.PacientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class MedicoLogic implements IMedicoLogic {

    @Inject
    private MedicoPersistence persistence;
    
    @Inject
    private PacientePersistence persistencePa;

    @Override
    public MedicoEntity getMedicoId(Long medicoId) {
        return persistence.find(medicoId);
    }

    @Override
    public MedicoEntity getMedicoCedula(int cedula) {
        return persistence.findByCedula(cedula);
    }

    @Override
    public MedicoEntity getMedicoTarjetaProfesional(String nTarjetaProfesional) {
        return persistence.findByTarjetaProfesional(nTarjetaProfesional);
    }

    @Override
    public List<MedicoEntity> getAllMedicos() {
        return persistence.findAll();
    }

    @Override
    public MedicoEntity createMedico(MedicoEntity entity) throws BusinessLogicException {
           
        MedicoEntity alreadyExistTP = getMedicoTarjetaProfesional(entity.getTarjetaProfesional());
           MedicoEntity alreadyExistCC = getMedicoCedula(entity.getCedula());
        if (alreadyExistTP != null) {
            throw new BusinessLogicException("Ya existe un medico con misma Tarjeta Profesional");
        }
        if (alreadyExistCC != null) {
            throw new BusinessLogicException("Ya existe un medico con misma cedula");
        }else {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public MedicoEntity updateMedico(MedicoEntity entity) throws BusinessLogicException {
      
           MedicoEntity alreadyExistTP = getMedicoTarjetaProfesional(entity.getTarjetaProfesional());
           MedicoEntity alreadyExistCC = getMedicoCedula(entity.getCedula());
        if (alreadyExistTP == null ) {
            throw new BusinessLogicException("No existe un medico con la Tarjeta Profesional dada");
        }
        if (alreadyExistCC == null) {
            throw new BusinessLogicException("No existe un medico con la cedula dada");
        }else {
           
            persistence.update(alreadyExistCC);
        }
        return entity;
    }

    @Override
    public void deleteMedico(Long medicoid) {
        persistence.delete(medicoid);
    }

    @Override
    public void agregarPacienteMedico(Long medicoid, Long idPaciente) {
      PacienteEntity paciente = persistencePa.find(idPaciente);
      MedicoEntity medico = persistence.find(medicoid);
      medico.addPacienteTrantando(paciente);
      paciente.setMedicoTratante(medico);
      persistence.update(medico);
    
    }

    @Override
    public void agregarPacienteHistorial(Long medicoid, Long idPaciente) {
      PacienteEntity paciente = persistencePa.find(idPaciente);
      MedicoEntity medico = persistence.find(medicoid);
      medico.getPacientes().add(paciente);
      paciente.getMedicos().add(medico);
      persistence.update(medico);
    }

}
