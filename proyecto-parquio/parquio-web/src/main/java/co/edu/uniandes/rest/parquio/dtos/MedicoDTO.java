/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BarraganJeronimo
 */
@XmlRootElement
public class MedicoDTO {

    private long id;

    private int cedula;
    private String tarjetaProfesional;
    private String nombres;
    private boolean especialista;
    private String apellidos;
    private char sexo;
    private Date fechaNacimiento;
    private String fechaNacimientoS;

    /**
     * Constructor
     *
     * @param cedula
     * @param tarjetaProfesional
     * @param nombres
     * @param especialista
     * @param apellidos
     * @param sexo
     */
    public MedicoDTO(int cedula, String tarjetaProfesional, String nombres, boolean especialista, String apellidos, char sexo) {
        super();
        this.cedula = cedula;
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombres = nombres;
        this.especialista = especialista;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }

    /**
     * Constructor por defecto
     */
    public MedicoDTO() {
    }

    /**
     * Constructor apartir de entity
     *
     * @param entity
     */
    public MedicoDTO(MedicoEntity entity) {
        if (entity != null) {
            this.id= entity.getId();
            this.cedula = entity.getCedula();
            this.tarjetaProfesional = entity.getTarjetaProfesional();
            this.nombres = entity.getNombres();
            this.especialista = entity.getEspecialista();
            this.apellidos = entity.getApellidos();
            this.sexo = entity.getSexo();
            this.fechaNacimiento= entity.getFechaNacimiento();
        }
    }

    /**
     * Convertir a MedicoEntity
     *
     * @return MedicoEntity
     */
    public MedicoEntity toEntity() {

        MedicoEntity entity = new MedicoEntity();
        entity.setApellidos(apellidos);
        entity.setCedula(cedula);
        entity.setEspecialista(especialista);
        entity.setFechaNacimiento(fechaNacimiento);
        entity.setNombres(nombres);
        entity.setSexo(sexo);
        entity.setTarjetaProfesional(tarjetaProfesional);
        entity.setId(id);

        return entity;

    }

    /**
     *
     * @return
     */
    public int getCedula() {
        return cedula;
    }

    /**
     *
     * @param cedula
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     *
     * @return
     */
    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    /**
     *
     * @param tarjetaProfesional
     */
    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    /**
     *
     * @return
     */
    public String getNombres() {
        return nombres;
    }

    /**
     *
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     *
     * @return
     */
    public boolean isEspecialista() {
        return especialista;
    }

    /**
     *
     * @param especialista
     */
    public void setEspecialista(boolean especialista) {
        this.especialista = especialista;
    }

    /**
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return
     */
    public char getSexo() {
        return sexo;
    }

    /**
     *
     * @param sexo
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     *
     * @return
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return
     */
    public String getFechaNacimientoS() {
        return fechaNacimientoS;
    }

    /**
     *
     * @param fechaNacimientoS
     */
    public void setFechaNacimientoS(String fechaNacimientoS) {
        this.fechaNacimientoS = fechaNacimientoS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
