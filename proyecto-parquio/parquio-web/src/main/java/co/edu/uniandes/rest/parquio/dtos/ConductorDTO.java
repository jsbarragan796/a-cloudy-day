/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BarraganJeronimo
 */
@XmlRootElement
public class ConductorDTO {

    private Long id;

    private String usuario;
    private int cedula;
    private String nombres;
    private String apellidos;

    /**
     * Constructor
     *
     */
    public ConductorDTO(String usuario, int cedula, String nombres, String apellidos) {
        super();
        this.usuario = usuario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    /**
     * Constructor por defecto
     */
    public ConductorDTO() {
    }

    /**
     * Constructor apartir de entity
     *
     * @param entity
     */
    public ConductorDTO(ConductorEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.usuario = entity.getUsuario();
            this.cedula = entity.getCedula();
            this.nombres = entity.getNombres();
            this.apellidos = entity.getApellidos();
        }
    }

    /**
     * Convertir a ConductorEntity
     *
     * @return MedicoEntity
     */
    public ConductorEntity toEntity() {

        ConductorEntity entity = new ConductorEntity();

        entity.setId(id);

        entity.setUsuario(usuario);
        entity.setCedula(cedula);
        entity.setApellidos(apellidos);
        entity.setNombres(nombres);

        return entity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
    
    
    
}
