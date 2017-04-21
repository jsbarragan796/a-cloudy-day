/*
 * CarroDTO
 * Objeto de transferencia de datos de Carros.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class CarroDTO {
    
    private Long id;
    
    private String placa;
   private String tipo;
    
    
    /**
     * Constructor por defecto
     */
    public CarroDTO() {
	}

    public CarroDTO(Long id, String placa, String tipo) {
        this.id = id;
        this.placa = placa;
        this.tipo = tipo;
    }

    
    
    public CarroDTO(CarroEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.placa = entity.getPlaca();
            this.tipo = entity.getTipo();
            
        }
    }
    
     /**
     * Convierte un objeto CarroDTO a CarroEntity.
     *
     * @return Nueva objeto CarroEntity.
     * 
     */
    public CarroEntity toEntity() {
        CarroEntity entity = new CarroEntity();
        entity.setId(this.getId());
        entity.setPlaca(this.getPlaca());
        entity.setTipo(this.getTipo());
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
