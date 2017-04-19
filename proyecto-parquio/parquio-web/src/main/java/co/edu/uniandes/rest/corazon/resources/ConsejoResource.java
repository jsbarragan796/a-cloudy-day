/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.rest.corazon.dtos.ConsejoDTO;
import co.edu.uniandes.rest.corazon.dtos.ConsejoDetailDTO;
import co.edu.uniandes.rest.corazon.exceptions.MedicoLogicException;
import co.edu.uniandes.sisteam.corazon.api.IConsejoLogic;
import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author santiago
 */
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConsejoResource {

    @Inject
    private IConsejoLogic consejoLogic;

    private List<ConsejoDetailDTO> listEntity2DetailDTO(List<ConsejoEntity> entityList) {
        List<ConsejoDetailDTO> list = new ArrayList<>();
        for (ConsejoEntity entity : entityList) {
            list.add(new ConsejoDetailDTO(entity));
        }
        return list;
    }

    @GET
    @Path("pacientes/{idPaciente: \\d+}/consejos")
    public List<ConsejoDetailDTO> getConsejosPaciente(@PathParam("idPaciente") Long idPaciente) {
        List<ConsejoEntity> consejos = consejoLogic.getConsejosPaciente(idPaciente);
        return listEntity2DetailDTO(consejos);
    }

    @GET
    @Path("medicos/{idMedico: \\d+}/consejos")
    public List<ConsejoDetailDTO> getConsejosMedico(@PathParam("idMedico") Long idMedico) {
        List<ConsejoEntity> consejos = consejoLogic.getConsejosPaciente(idMedico);
        return listEntity2DetailDTO(consejos);
    }

    @POST
    @Path("medicos/{idMedico: \\d+}/consejos/{idPaciente: \\d+}")
    public ConsejoDetailDTO createConsejo(@PathParam("idPaciente") Long idPaciente,
            @PathParam("idMedico") Long idMedico, ConsejoDTO dto) throws BusinessLogicException {
        ConsejoEntity consejo = dto.toEntity();
        ConsejoEntity respuesta = consejoLogic.createConsejo(idPaciente, idMedico, consejo);
        return new ConsejoDetailDTO(respuesta);
    }
    
    @DELETE
    @Path("consejos/{id: \\d+}")
    public void deleteConsejo(@PathParam("id") Long id) {
        if(consejoLogic.getConsejo(id)==null){
            throw new WebApplicationException("El consejo no existe",404);
        }
        else{
            consejoLogic.deleteConsejo(id);
        }
    }
}
