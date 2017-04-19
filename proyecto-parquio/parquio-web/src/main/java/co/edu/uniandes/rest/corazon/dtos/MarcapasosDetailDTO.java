/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.MarcapasosEntity;

/**
 *
 * @author BarraganJeronimo
 */
public class MarcapasosDetailDTO extends MarcapasosDTO {

    private PacienteDTO paciente;

    public MarcapasosDetailDTO() {
        super();
    }

    public MarcapasosDetailDTO(MarcapasosEntity entity){
        super(entity);
        if(entity!=null){
            if(entity.getPaciente()!=null){
                this.paciente= new PacienteDTO(entity.getPaciente());
            }
        }
        
    }
    
    @Override
    public MarcapasosEntity toEntity(){
        MarcapasosEntity entity= super.toEntity();
        
        if(paciente!=null){
            entity.setPaciente(paciente.toEntity());
        }
        
        
        return entity;
    }
    
    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

}
