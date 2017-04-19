/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public class MedicoDetailDTO extends MedicoDTO {

    private List<PacienteDTO> pacientesTratando = new ArrayList<>();

    private List<PacienteDTO> pacientes = new ArrayList<>();

    private List<ConsejoDTO> consejosRealizados = new ArrayList<>();

    public MedicoDetailDTO() {
        super();
    }

    public MedicoDetailDTO(MedicoEntity entity) {
        super(entity);

        if (entity != null) {
            List<ConsejoEntity> consejosEntityRealizados = entity.getConsejosRealizados();

            for (ConsejoEntity consejoRealizado : consejosEntityRealizados) {
                this.consejosRealizados.add(new ConsejoDTO(consejoRealizado));
            }
            List<PacienteEntity> pacientesEntity = entity.getPacientes();
            for (PacienteEntity paciente : pacientesEntity) {
                this.pacientes.add(new PacienteDTO(paciente));
            }

            List<PacienteEntity> pacientesTratandoEntity = entity.getPacientesTratando();

            for (PacienteEntity pacienteEntity : pacientesTratandoEntity) {
                this.pacientesTratando.add(new PacienteDTO(pacienteEntity));
            }
        }

    }

    @Override
    public MedicoEntity toEntity() {

        MedicoEntity entity = super.toEntity();

        List<ConsejoDTO> consejosRealizados2 = this.consejosRealizados;

        for (ConsejoDTO consejoRealizado : consejosRealizados2) {
            entity.getConsejosRealizados().add(consejoRealizado.toEntity());
        }
        List<PacienteDTO> pacientes2 = this.pacientes;
        for (PacienteDTO paciente : pacientes2) {
           entity.getPacientes().add(paciente.toEntity());
        }

        List<PacienteDTO> pacientesTratando2 = this.pacientesTratando;

        for (PacienteDTO pacienteEntity : pacientesTratando2) {
           entity.getPacientesTratando().add(pacienteEntity.toEntity());
        }
        
        return entity;

    }

    public List<PacienteDTO> getPacientesTratando() {
        return pacientesTratando;
    }

    public void setPacientesTratando(List<PacienteDTO> pacientesTratando) {
        this.pacientesTratando = pacientesTratando;
    }

    public List<PacienteDTO> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteDTO> pacientes) {
        this.pacientes = pacientes;
    }

    public List<ConsejoDTO> getConsejosRealizados() {
        return consejosRealizados;
    }

    public void setConsejosRealizados(List<ConsejoDTO> consejosRealizados) {
        this.consejosRealizados = consejosRealizados;
    }

}
