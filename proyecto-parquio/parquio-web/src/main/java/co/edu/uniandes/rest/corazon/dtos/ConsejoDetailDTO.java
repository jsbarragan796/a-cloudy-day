/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;

/**
 *
 * @author santiago
 */
public class ConsejoDetailDTO extends ConsejoDTO {

    private MedicoDTO medico;

    private PacienteDTO paciente;

    public ConsejoDetailDTO() {
        super();
    }

    public ConsejoDetailDTO(ConsejoEntity entity) {
        super(entity);
        if (entity.getMedico() != null) {
            this.medico = new MedicoDTO(entity.getMedico());
        }
        if (entity.getPaciente() != null) {
            this.paciente = new PacienteDTO(entity.getPaciente());
        }
    }

    @Override
    public ConsejoEntity toEntity() {
        ConsejoEntity entity = super.toEntity();
        if (this.getMedico() != null) {
            entity.setMedico(this.getMedico().toEntity());
        }
        if (this.getPaciente() != null) {
            entity.setPaciente(this.getPaciente().toEntity());
        }
        return entity;
    }

    public MedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }
    
    

}
