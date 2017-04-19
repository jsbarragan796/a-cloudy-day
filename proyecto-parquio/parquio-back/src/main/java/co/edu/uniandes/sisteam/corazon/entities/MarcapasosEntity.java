/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
@Entity
public class MarcapasosEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaImplante;

    @PodamExclude
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVidaUtil;
    
    private String marca;
    
    private String modelo;
    
    private String numeroSerie;
    
    private Double voltaje;
    
    private Double frecuenciaElectrica;
    
    @OneToOne(fetch = FetchType.LAZY)
    private PacienteEntity paciente;

    

    
    public Date getFechaImplante() {
        return fechaImplante;
    }

    public void setFechaImplante(Date fechaImplante) {
        this.fechaImplante = fechaImplante;
    }

    public Date getFinVidaUtil() {
        return finVidaUtil;
    }

    public void setFinVidaUtil(Date finVidaUtil) {
        this.finVidaUtil = finVidaUtil;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(Double voltaje) {
        this.voltaje = voltaje;
    }

    public Double getFrecuenciaElectrica() {
        return frecuenciaElectrica;
    }

    public void setFrecuenciaElectrica(Double frecuenciaElectrica) {
        this.frecuenciaElectrica = frecuenciaElectrica;
    }
    
    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

}
