/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.rest.corazon.dtos.DiagnosticoDTO;
import co.edu.uniandes.rest.corazon.dtos.ExamenDTO;
import co.edu.uniandes.rest.corazon.dtos.HistoriaClinicaDTO;
import co.edu.uniandes.rest.corazon.dtos.TratamientoDTO;
import co.edu.uniandes.rest.corazon.exceptions.PacienteLogicException;
import co.edu.uniandes.sisteam.corazon.api.IHistoriaClinicaLogic;
import co.edu.uniandes.sisteam.corazon.entities.DiagnosticoEntity;
import co.edu.uniandes.sisteam.corazon.entities.ExamenEntity;
import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.TratamientoEntity;
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
@Path("pacientes/{idPaciente: \\d+}/historia")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HistoriaClinicaResource {
    
    @Inject
    private IHistoriaClinicaLogic historiaClinicaLogic;
    
    @GET
    public HistoriaClinicaDTO getHistoriaClinicaPaciente(@PathParam("idPaciente") Long idPaciente){
        HistoriaClinicaEntity entity = historiaClinicaLogic.getHistoriaClinicaPaciente(idPaciente);
        if(entity == null){
            throw new WebApplicationException(404);
        }
        return new HistoriaClinicaDTO(entity);
    }
    
    @POST
    @Path("tratamiento")
    public TratamientoDTO addTratamientoPaciente(@PathParam("idPaciente") Long idPaciente, TratamientoDTO dto){
        TratamientoEntity entity = dto.toEntity();
        TratamientoEntity respuesta = historiaClinicaLogic.addTratamientoPaciente(idPaciente, entity);
        return new TratamientoDTO(respuesta);
    }
    
    @POST
    @Path("examen")
    public ExamenDTO addExamenPaciente(@PathParam("idPaciente") Long idPaciente, ExamenDTO dto){
        ExamenEntity entity = dto.toEntity();
        ExamenEntity respuesta = historiaClinicaLogic.addExamenPaciente(idPaciente, entity);
        return new ExamenDTO(respuesta);
    }
    
    @POST
    @Path("diagnostico")
    public DiagnosticoDTO addDiagnosticoPaciente(@PathParam("idPaciente") Long idPaciente, DiagnosticoDTO dto){
        DiagnosticoEntity entity = dto.toEntity();
        DiagnosticoEntity respuesta = historiaClinicaLogic.addDiagnosticoPaciente(idPaciente, entity);
        return new DiagnosticoDTO(respuesta);
    }
    
    @DELETE
    @Path("diagnostico/{idDiagnostico:\\d+}")
    public void deleteDiagnosticoPaciente(@PathParam("idDiagnostico") Long idDiagnostico) {
        if(historiaClinicaLogic.getDiagnosticoPaciente(idDiagnostico)==null){
            throw new WebApplicationException("El diagnostico no existe",404);
        }
        else{
            historiaClinicaLogic.deleteDiagnosticoPaciente(idDiagnostico);
        }
    }
    
    @DELETE
    @Path("examen/{idExamen: \\d+}")
    public void deleteExamenPaciente(@PathParam("idExamen") Long idExamen) {
        if(historiaClinicaLogic.getExamenPaciente(idExamen)==null){
            throw new WebApplicationException("El examen no existe",404);
        }
        else{
            historiaClinicaLogic.deleteExamenPaciente(idExamen);
        }
    }
    
    @DELETE
    @Path("tratamiento/{idTratamiento: \\d+}")
    public void deleteTratamientoPaciente(@PathParam("idTratamiento") Long idTratamiento) {
        if(historiaClinicaLogic.getDiagnosticoPaciente(idTratamiento)==null){
            throw new WebApplicationException("El tratamiento no existe",404);
        }
        else{
            historiaClinicaLogic.deleteTratamientoPaciente(idTratamiento);
        }
    }
}
