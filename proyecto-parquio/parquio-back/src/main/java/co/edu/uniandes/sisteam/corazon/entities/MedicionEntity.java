
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


@Entity
public class MedicionEntity extends BaseEntity implements Serializable {

    private Long pacienteId;
    private String dictamen;
    private Date fecha;
    private Double frecuenciaCardiaca;
    private Double presionSanguinea;
    private int nivelEstres;
    
     //Relaciones con Emergencia
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional=true)
    private EmergenciaEntity emergencia;

   
    
    /**
     * Obtiene el atributo pacienteId.
     *
     * @return atributo pacienteId.
     *
     */
    public Long getPacienteId() 
    {
        return pacienteId;
    }

    /**
     * Establece el valor del atributo pacienteId.
     *
     * @param pacienteId nuevo valor del atributo
     *
     */
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
    
    

    public String getDictamen() {
        return dictamen;
    }

    public void setDictamen(String dictamen) {
        this.dictamen = dictamen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Double frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Double getPresionSanguinea() {
        return presionSanguinea;
    }

    public void setPresionSanguinea(Double presionSanguinea) {
        this.presionSanguinea = presionSanguinea;
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        this.nivelEstres = nivelEstres;
    }
    
     public EmergenciaEntity getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(EmergenciaEntity emergencia) {
        this.emergencia = emergencia;
    }
}
