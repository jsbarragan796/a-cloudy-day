/*
 * ReservaDTO
 * Objeto de transferencia de datos de Reservas.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import java.util.Date;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class ReservaDTO {
    
    private Long id;
    
    private Date fecha;
   private double duracion;
    
    
    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
	}

    public ReservaDTO(Long id, Date fecha, double duracion) {
        this.id = id;
        this.fecha = fecha;
        this.duracion = duracion;
    }

   

    
    
    public ReservaDTO(ReservaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.duracion = entity.getDuracion();
            
        }
    }
    
     /**
     * Convierte un objeto ReservaDTO a ReservaEntity.
     *
     * @return Nueva objeto ReservaEntity.
     * 
     */
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setDuracion(this.getDuracion());
        return entity;
    }

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    
}
