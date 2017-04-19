/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.resources;

import co.edu.uniandes.rest.parquio.dtos.MedicoDetailDTO;
import co.edu.uniandes.rest.parquio.dtos.MedicoDTO;
import co.edu.uniandes.rest.parquio.dtos.PacienteDTO;
import co.edu.uniandes.rest.parquio.exceptions.MedicoLogicException;
import co.edu.uniandes.sisteam.corazon.api.IMedicoLogic;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author BarraganJeronimo
 */
@Path("/medicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicoResource {

    @Inject
    private IMedicoLogic medicoLogic;

    /**
     * Convierte una lista de MedicoEntity a una lista de MedicoDTO.
     *
     * @param entityList Lista de MedicoEntity a convertir.
     * @return Lista de MedicoDTO convertida.
     *
     */
    private List<MedicoDTO> listEntity2DTO(List<MedicoEntity> entityList) {
        List<MedicoDTO> list = new ArrayList<>();
        for (MedicoEntity entity : entityList) {
            list.add(new MedicoDTO(entity));
        }
        return list;
    }

    @GET
    public List<MedicoDTO> getMedico() {

        return listEntity2DTO(medicoLogic.getAllMedicos());
    }

    @GET
    @Path("{id: \\d+}")
    public MedicoDetailDTO getMedico(@PathParam("id") Long id) {
        return new MedicoDetailDTO(medicoLogic.getMedicoId(id));
    }
    
    
    @POST
    public MedicoDTO createMedico(MedicoDTO dto) throws MedicoLogicException {
       
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (dto.getFechaNacimientoS() != null) {
                Date nFecha;
                try {
                    nFecha = sdf.parse(dto.getFechaNacimientoS());
                    dto.setFechaNacimiento(nFecha);
                } catch (ParseException ex) {
                    throw new MedicoLogicException("" + ex.getMessage());
                }
            }

          return new MedicoDetailDTO(medicoLogic.createMedico(dto.toEntity()));
        } catch (BusinessLogicException ex) {
            throw new MedicoLogicException(ex.getMessage());
        }

       
    }
    
    @POST
    @Path("{id: \\d+}/agregarPacienteTratante/{idPaciente: \\d+}")
    public MedicoDetailDTO addPaciente(@PathParam("id") Long id,@PathParam("idPaciente") Long idpaciente) throws MedicoLogicException {
       
    
     medicoLogic.agregarPacienteMedico(id, idpaciente);
        
    return getMedico(id);
        
    }
    
    @POST
    @Path("{id: \\d+}/agregarPacienteHistorial/{idPaciente: \\d+}")
    public MedicoDetailDTO addPacienteHistorial(@PathParam("id") Long id,@PathParam("idPaciente") Long idpaciente) throws MedicoLogicException {
       
    
     medicoLogic.agregarPacienteHistorial(id, idpaciente);
        
    return getMedico(id);
        
    }
    
    

    @PUT
    @Path("{id: \\d+}")
    public MedicoDetailDTO updateMedico(@PathParam("id") Long id, MedicoDetailDTO dto) throws MedicoLogicException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (dto.getFechaNacimientoS() != null) {
                Date nFecha;
                try {
                    nFecha = sdf.parse(dto.getFechaNacimientoS());
                    dto.setFechaNacimiento(nFecha);
                } catch (ParseException ex) {
                    throw new MedicoLogicException("" + ex.getMessage());
                }
            }
     
          MedicoDetailDTO resp= new MedicoDetailDTO(medicoLogic.updateMedico(dto.toEntity()));
          resp.setId(id);
          return resp;
        } catch (BusinessLogicException ex) {
            throw new MedicoLogicException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMedico(@PathParam("id") Long id) {
        medicoLogic.deleteMedico(id);
    }

}
