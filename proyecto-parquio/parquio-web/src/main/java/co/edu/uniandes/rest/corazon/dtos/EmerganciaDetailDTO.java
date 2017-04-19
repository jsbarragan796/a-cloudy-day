/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;

/**
 *
 * @author BarraganJeronimo
 */
public class EmerganciaDetailDTO extends EmergenciaDTO{
   
    private MedicionDTO medicion;

    public EmerganciaDetailDTO() {
    }

    public EmerganciaDetailDTO(Long id, double latitud, double longitud, MedicionDTO medicionId) {
        super(id, latitud, longitud, medicionId);
    }

    public EmerganciaDetailDTO(EmergenciaEntity entity) {
        super(entity);
        if(entity!=null){
            if(entity.getMedicion()!=null){
                this.medicion= new MedicionDTO(entity.getMedicion());
            }
        }
    }
    
    
  
    
    
    
    

    public MedicionDTO getMedicion() {
        return medicion;
    }

    public void setMedicion(MedicionDTO medicion) {
        this.medicion = medicion;
    }
    
    
}
