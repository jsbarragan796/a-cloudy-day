/*
 * FavoritoDTO
 * Objeto de transferencia de datos de Favoritos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.FavoritoEntity;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class FavoritoDTO {
    
    private Long id;
    
    private String nombre;
    private double latitud;
    private double longitud;
    
    
    /**
     * Constructor por defecto
     */
    public FavoritoDTO() {
	}

    public FavoritoDTO(Long id, String nombre, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    public FavoritoDTO(FavoritoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.latitud = entity.getLatitud();
            this.longitud = entity.getLongitud();
            
        }
    }
    
     /**
     * Convierte un objeto FavoritoDTO a FavoritoEntity.
     *
     * @return Nueva objeto FavoritoEntity.
     * 
     */
    public FavoritoEntity toEntity() {
        FavoritoEntity entity = new FavoritoEntity();
        entity.setNombre(this.getNombre());
        entity.setId(this.getId());
        entity.setLatitud(this.getLatitud());
        entity.setLongitud(this.getLongitud());
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
