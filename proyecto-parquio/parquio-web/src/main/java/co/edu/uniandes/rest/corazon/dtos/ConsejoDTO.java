/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.rojas19
 */
@XmlRootElement
public class ConsejoDTO {

    private Long id;
    private Date fecha;
    private String tipo;
    private String descripcion;

    public ConsejoDTO() {
    }

    public ConsejoDTO(Long id, Date fecha, String tipo, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public ConsejoDTO(ConsejoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.tipo = entity.getTipo();
            this.descripcion = entity.getDescripcion();
        }
    }

    public ConsejoEntity toEntity() {
        ConsejoEntity entity = new ConsejoEntity();
        if (id != null) {
            entity.setId(this.getId());
        }
        entity.setFecha(this.getFecha());
        entity.setTipo(this.getTipo());
        entity.setDescripcion(this.getDescripcion());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
