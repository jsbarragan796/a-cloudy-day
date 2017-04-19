/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.MarcapasosEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BarraganJeronimo
 */
@XmlRootElement
public class MarcapasosDTO {

    private long id;

    private Date fechaImplante;

    private Date finVidaUtil;

    private String fechaImplanteS;

    private String finVidaUtilS;

    private String marca;

    private String modelo;

    private String numeroSerie;

    private Double voltaje;

    private Double frecuenciaElectrica;

    public MarcapasosDTO(Date fechaImplante, Date finVidaUtil, String marca, String modelo, String numeroSerie, Double voltaje, Double frecuenciaElectrica) {
        this.fechaImplante = fechaImplante;
        this.finVidaUtil = finVidaUtil;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.voltaje = voltaje;
        this.frecuenciaElectrica = frecuenciaElectrica;
    }

    public MarcapasosDTO() {
    }

    public MarcapasosDTO(MarcapasosEntity entity) {

        if (entity != null) {
            this.id=entity.getId();
            this.fechaImplante = entity.getFechaImplante();
            this.finVidaUtil = entity.getFinVidaUtil();
            this.marca = entity.getMarca();
            this.modelo = entity.getModelo();
            this.numeroSerie = entity.getNumeroSerie();
            this.voltaje = entity.getVoltaje();
            this.frecuenciaElectrica = entity.getFrecuenciaElectrica();
           
        }

    }

    public MarcapasosEntity toEntity() {
        MarcapasosEntity entity = new MarcapasosEntity();
        entity.setFechaImplante(fechaImplante);
        entity.setFinVidaUtil(finVidaUtil);
        entity.setFrecuenciaElectrica(frecuenciaElectrica);
        entity.setMarca(marca);
        entity.setModelo(modelo);
        entity.setNumeroSerie(numeroSerie);
        entity.setVoltaje(voltaje);
        entity.setId(id);
        return entity;
    }

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

    public String getFechaImplanteS() {
        return fechaImplanteS;
    }

    public void setFechaImplanteS(String fechaImplanteS) {
        this.fechaImplanteS = fechaImplanteS;
    }

    public String getFinVidaUtilS() {
        return finVidaUtilS;
    }

    public void setFinVidaUtilS(String finVidaUtilS) {
        this.finVidaUtilS = finVidaUtilS;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
