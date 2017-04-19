package co.edu.uniandes.rest.corazon.resources;

/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */


import co.edu.uniandes.rest.corazon.dtos.EmergenciaDTO;
import co.edu.uniandes.rest.corazon.dtos.MedicionDTO;
import co.edu.uniandes.sisteam.corazon.api.IEmergenciaLogic;
import co.edu.uniandes.sisteam.corazon.api.IMedicionLogic;
import co.edu.uniandes.sisteam.corazon.api.IPacienteLogic;
import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("pacientes/{pacienteId: \\d+}/mediciones/{medicionId: \\d+}/emergencia")
public class EmergenciaResource {

    @Inject
    private IEmergenciaLogic emergenciaLogic;

    
    @Inject
    private IMedicionLogic medicionLogic;

    @Inject
    private IPacienteLogic pacienteLogic;
    
    

    /**
     * Convierte una lista de EmergenciaEntity a una lista de
     * EmergenciaDTO
     *
     * @param entityList Lista de EmergenciaEntity a convertir
     * @return Lista de EmergenciaDTO convertida
     *
     */
    private List<EmergenciaDTO> listEntity2DTO(List<EmergenciaEntity> entityList) {
        List<EmergenciaDTO> list = new ArrayList<>();
        for (EmergenciaEntity entity : entityList) {
            list.add(new EmergenciaDTO(entity));
        }
        return list;
    }

    
    public void existsMedicion(Long medicionId) 
    {
        MedicionDTO medicion = new MedicionDTO(medicionLogic.getMedicion(medicionId));
        if (medicion == null) {
            throw new WebApplicationException(404);
        }
        
        
    }

    /**
     * Obtiene los datos de una instancia de Emergencia a partir de su ID
     *
     * @param medicionId
     * @param emergenciaId Identificador de la instancia a consultar
     * @return Instancia de EmergenciaDTO con los datos del Emergencia
     * consultado
     *
     */
   @Path("{emergenciaId}")
   @GET
    public List<EmergenciaDTO> getEmergencia(@PathParam("medicionId") Long medicionId, @PathParam("emergenciaId") Long emergenciaId) {
    
        System.out.println("Esta es el id Sebas" + medicionId + "emergenciaId "+ emergenciaId);
        
       
        List<EmergenciaEntity> entity = emergenciaLogic.getEmergenciaMedicion(medicionId);
       
        return listEntity2DTO(entity);
    }
    
   
   
   @POST
    public EmergenciaDTO createEmergenciaMedicion( @PathParam("medicionId") Long medicionId,EmergenciaDTO dto) throws BusinessLogicException {        
        
                
        return new EmergenciaDTO(emergenciaLogic.createEmergencia( dto.toEntity()));
    }
}
