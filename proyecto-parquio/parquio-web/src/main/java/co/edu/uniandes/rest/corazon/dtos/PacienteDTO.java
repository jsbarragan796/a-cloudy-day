/*
 * PacienteDTO
 * Objeto de transferencia de datos de Paciente.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de Paciente.
 *
 * @author Asistente
 */
@XmlRootElement
public class PacienteDTO {
    
   
    private Long id;   
    private int cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private char sexo;
    

    
    private String direccionResidencia;
    private String entidadPrestadoraSalud;
    private Date fechaNacimiento;
    private String tipoSanguineo;

    /**
     * Constructor por defecto
     */
    public PacienteDTO() {
        
    }

    public PacienteDTO(Long id, int cedula, String nombres, String apellidos,
            int edad, char sexo, String direccionResidencia, 
            String entidadPrestadoraSalud, Date fechaNacimiento, 
            String tipoSanguineo ) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        
        this.direccionResidencia = direccionResidencia;
        this.entidadPrestadoraSalud = entidadPrestadoraSalud;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSanguineo = tipoSanguineo;
   
    }
  
    /**
     * Crea un objeto PacienteDTO a partir de un objeto PacienteEntity.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public PacienteDTO(PacienteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.cedula = entity.getCedula();
            this.nombres = entity.getNombres();
            this.apellidos = entity.getApellidos();
            this.edad = entity.getEdad();
            this.sexo = entity.getSexo();          
            this.direccionResidencia = entity.getDireccionResidencia();
            this.entidadPrestadoraSalud = entity.getEntidadPrestadoraSalud();
            this.fechaNacimiento = entity.getFechaNacimiento();
            this.tipoSanguineo = entity.getTipoSanguineo();
        }
    }

    /**
     * Convierte un objeto PacienteDTO a PacienteEntity.
     *
     * @return Nueva objeto PacienteEntity.
     *
     */
    public PacienteEntity toEntity() {
        PacienteEntity entity = new PacienteEntity();
        
        entity.setId(this.getId());
        entity.setCedula(this.getCedula());
        entity.setNombres(this.getNombres());
        entity.setApellidos(this.getApellidos());
        entity.setEdad(this.getEdad());
        entity.setSexo(this.getSexo());        
        entity.setDireccionResidencia(this.direccionResidencia);
        entity.setEntidadPrestadoraSalud(this.entidadPrestadoraSalud);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setTipoSanguineo(this.tipoSanguineo);
        
        return entity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEntidadPrestadoraSalud() {
        return entidadPrestadoraSalud;
    }

    public void setEntidadPrestadoraSalud(String entidadPrestadoraSalud) {
        this.entidadPrestadoraSalud = entidadPrestadoraSalud;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    
    

}
