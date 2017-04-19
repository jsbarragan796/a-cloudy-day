package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * MedicionDTO
 * Objeto de transferencia de datos de Mediciones.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
@XmlRootElement
public class EmergenciaDTO {
    
    private Long id;
    private double latitud;
    private double longitud;  
    
    /**
     * Constructor por defecto
     */
    public EmergenciaDTO() {
	}

    public EmergenciaDTO(Long id, double latitud, double longitud, MedicionDTO medicionId){
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    public EmergenciaDTO(EmergenciaEntity entity) {
        
        if (entity != null) {
            this.id = entity.getId();
            this.latitud = entity.getLatitud();
            this.longitud = entity.getLongitud();
    
        }
    }
    
     /**
     * Convierte un objeto MedicionDTO a MedicionEntity.
     *
     * @return Nueva objeto MedicionEntity.
     * 
     */
    public EmergenciaEntity toEntity() {
        EmergenciaEntity entity = new EmergenciaEntity();
        entity.setId(this.getId());
        entity.setLatitud(this.getLatitud());
        entity.setLongitud(this.getLongitud());

        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


   
    

}
