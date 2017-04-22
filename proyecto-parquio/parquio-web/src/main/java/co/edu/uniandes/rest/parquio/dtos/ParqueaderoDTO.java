/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BarraganJeronimo
 */
@XmlRootElement
public class ParqueaderoDTO {

    private Long id;

    private String nombre;
    private int disponibilidad;
    private int cupos;
    private String direccion;
    private double latitud;
    private double longitud;

    public ParqueaderoDTO(Long id, String nombre, int disponibilidad, int cupos, String direccion, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.cupos = cupos;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    

    /**
     * Constructor por defecto
     */
    public ParqueaderoDTO() {
    }

    /**
     * Constructor apartir de entity
     *
     * @param entity
     */
    public ParqueaderoDTO(ParqueaderoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.disponibilidad = entity.getDisponibilidad();
            this.cupos = entity.getCupos();
            this.direccion =entity.getDireccion();
            this.latitud = entity.getLatitud();
            this.longitud=entity.getLongitud();
            
        }
    }

    /**
     * Convertir a ParqueaderoEntity
     *
     * @return MedicoEntity
     */
    public ParqueaderoEntity toEntity() {

        ParqueaderoEntity entity = new ParqueaderoEntity();

        entity.setId(this.getId());

        entity.setNombre(this.getNombre());
        entity.setDisponibilidad(this.getDisponibilidad());
        entity.setCupos(this.getCupos());
        entity.setDireccion(this.getDireccion());
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
