/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.DiagnosticoEntity;
import co.edu.uniandes.sisteam.corazon.entities.ExamenEntity;
import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;

/**
 *
 * @author santiago
 */
public interface IHistoriaClinicaLogic {
    
    public HistoriaClinicaEntity getHistoriaClinica(Long id);
    public HistoriaClinicaEntity getHistoriaClinicaPaciente(Long idPaciente);
    
    public TratamientoEntity addTratamientoPaciente(Long idPaciente, TratamientoEntity tratamiento);
    public ExamenEntity addExamenPaciente(Long idPaciente, ExamenEntity examen);
    public DiagnosticoEntity addDiagnosticoPaciente(Long idPaciente, DiagnosticoEntity diagnostico);
    
    public void deleteTratamientoPaciente(Long idTratamiento);
    public void deleteExamenPaciente(Long idExamen);
    public void deleteDiagnosticoPaciente(Long idDiagnostico);
    
    public TratamientoEntity getTratamientoPaciente(Long idTratamiento);
    public ExamenEntity getExamenPaciente(Long idExamen);
    public DiagnosticoEntity getDiagnosticoPaciente(Long idDiagnostico);
    
}
