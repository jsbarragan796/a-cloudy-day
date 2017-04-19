/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author BarraganJeronimo
 */
@Entity
public class DiagnosticoEntity extends BaseEntity implements Serializable {
    
    private Date fecha;
     
    private String descripcion;
    
    @ManyToOne
    private HistoriaClinicaEntity historiaClinica;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HistoriaClinicaEntity getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinicaEntity historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    
}
