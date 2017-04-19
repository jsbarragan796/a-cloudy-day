/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.ejbs;

import co.edu.uniandes.sisteam.corazon.api.IHistoriaClinicaLogic;
import co.edu.uniandes.sisteam.corazon.entities.DiagnosticoEntity;
import co.edu.uniandes.sisteam.corazon.entities.ExamenEntity;
import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;
import co.edu.uniandes.sisteam.corazon.persistence.DiagnosticoPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.ExamenPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.HistoriaClinicaPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.TratamientoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author santiago
 */
@Stateless
public class HistoriaClinicaLogic implements IHistoriaClinicaLogic {

    @Inject
    private HistoriaClinicaPersistence historiaClinicaPersistence;

    @Inject
    private ExamenPersistence examenPersistence;

    @Inject
    private DiagnosticoPersistence diagnosticoPersistence;

    @Inject
    private TratamientoPersistence tratamientoPersistence;

    @Override
    public HistoriaClinicaEntity getHistoriaClinicaPaciente(Long idPaciente) {
        return historiaClinicaPersistence.getHistoriaClinicaPaciente(idPaciente);
    }

    @Override
    public TratamientoEntity addTratamientoPaciente(Long idPaciente, TratamientoEntity tratamiento) {
        HistoriaClinicaEntity historia = historiaClinicaPersistence.getHistoriaClinicaPaciente(idPaciente);
//        historia.addTratamiento(tratamiento);
        tratamiento.setHistoriaClinica(historia);
        return tratamientoPersistence.create(tratamiento);
    }

    @Override
    public ExamenEntity addExamenPaciente(Long idPaciente, ExamenEntity examen) {
        HistoriaClinicaEntity historia = historiaClinicaPersistence.getHistoriaClinicaPaciente(idPaciente);
//        historia.addTratamiento(tratamiento);
        examen.setHistoriaClinica(historia);
        return examenPersistence.create(examen);
    }

    @Override
    public DiagnosticoEntity addDiagnosticoPaciente(Long idPaciente, DiagnosticoEntity diagnostico) {
        HistoriaClinicaEntity historia = historiaClinicaPersistence.getHistoriaClinicaPaciente(idPaciente);
//        historia.addTratamiento(tratamiento);
        diagnostico.setHistoriaClinica(historia);
        return diagnosticoPersistence.create(diagnostico);
    }

    @Override
    public HistoriaClinicaEntity getHistoriaClinica(Long id) {
        return historiaClinicaPersistence.find(id);
    }

    @Override
    public void deleteTratamientoPaciente(Long idTratamiento) {
        tratamientoPersistence.delete(idTratamiento);
    }

    @Override
    public void deleteExamenPaciente(Long idExamen) {
        examenPersistence.delete(idExamen);
    }

    @Override
    public void deleteDiagnosticoPaciente(Long idDiagnostico) {
        diagnosticoPersistence.delete(idDiagnostico);
    }

    @Override
    public TratamientoEntity getTratamientoPaciente(Long idTratamiento) {
        return tratamientoPersistence.find(idTratamiento);
    }

    @Override
    public ExamenEntity getExamenPaciente(Long idExamen) {
        return examenPersistence.find(idExamen);
    }

    @Override
    public DiagnosticoEntity getDiagnosticoPaciente(Long idDiagnostico) {
        return diagnosticoPersistence.find(idDiagnostico);
    }

}
