/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.DiagnosticoEntity;
import co.edu.uniandes.sisteam.corazon.entities.ExamenEntity;
import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
public class HistoriaClinicaDTO {

    private Long id;

    private PacienteDTO paciente;

    private List<ExamenDTO> examenes = new ArrayList<>();

    private List<TratamientoDTO> tratamientos = new ArrayList<>();

    private List<DiagnosticoDTO> diagnosticos = new ArrayList<>();

    public HistoriaClinicaDTO() {
    }

    public HistoriaClinicaDTO(HistoriaClinicaEntity entity) {
        if (entity != null) {
            id = entity.getId();
            paciente = new PacienteDTO(entity.getPaciente());

            for (ExamenEntity examen : entity.getExamenes()) {
                examenes.add(new ExamenDTO(examen));
            }
            for (TratamientoEntity tratamiento : entity.getTratamientos()) {
                tratamientos.add(new TratamientoDTO(tratamiento));
            }
            for(DiagnosticoEntity diagnostico: entity.getDiagnosticos()){
                diagnosticos.add(new DiagnosticoDTO(diagnostico));
            }          

        }
    }
    
    public HistoriaClinicaEntity toEntity(){
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public List<ExamenDTO> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenDTO> examenes) {
        this.examenes = examenes;
    }

    public List<TratamientoDTO> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientoDTO> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public List<DiagnosticoDTO> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<DiagnosticoDTO> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
    
    

}
