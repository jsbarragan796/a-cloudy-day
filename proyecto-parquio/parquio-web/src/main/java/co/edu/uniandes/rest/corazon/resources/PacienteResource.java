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
package co.edu.uniandes.rest.corazon.resources;


import co.edu.uniandes.rest.corazon.dtos.PacienteDTO;
import co.edu.uniandes.rest.corazon.dtos.PacienteDetailDTO;
import co.edu.uniandes.rest.corazon.exceptions.PacienteLogicException;
import co.edu.uniandes.sisteam.corazon.api.IPacienteLogic;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;

@Path("/resources/pacientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    private IPacienteLogic pacienteLogic;

    /**
     * Convierte una lista de PacienteEntity a una lista de PacienteDTO.
     *
     * @param entityList Lista de PacienteEntity a convertir.
     * @return Lista de PacienteDTO convertida.
     *
     */
    private List<PacienteDTO> listEntity2DTO(List<PacienteEntity> entityList) {
        List<PacienteDTO> list = new ArrayList<>();
        for (PacienteEntity entity : entityList) {
            list.add(new PacienteDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Paciente
     *
     * @return Colección de objetos de PacienteDTO
     *
     */
    @GET
    public List<PacienteDTO> getPacientes() { 
        
        return listEntity2DTO(pacienteLogic.getPacientes());
    }

    /**
     * Obtiene los datos de una instancia de Paciente a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PacienteDTO con los datos del Paciente
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public PacienteDetailDTO getPaciente(@PathParam("id") Long id) 
    {
        return new PacienteDetailDTO(pacienteLogic.getPaciente(id));
    }

//    /**
//     * Obtiene los datos de una instancia de Paciente a partir de su ID
//     *
//     * @param id Identificador de la instancia a consultar
//     * @return Instancia de PacienteDTO con los datos del Paciente
//     * consultado
//     *
//     */
//    @GET
//    @Path("{cedula: \\d+}")
//    public PacienteDTO getPacienteByCedula(@QueryParam("cedula") int cedula) {
//        return new PacienteDTO(pacienteLogic.getPacienteByCedula(cedula));
//    }

    /**
     * Se encarga de crear un Paciente en la base de datos
     *
     * @param dto Objeto de PacienteDTO con los datos nuevos
     * @return Objeto de PacienteDTOcon los datos nuevos y su ID
     * @throws co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException
     *
     */
    @POST
    public PacienteDTO createPaciente(PacienteDTO dto) throws PacienteLogicException  {
        System.out.println("dto es "+ dto.toEntity());
        PacienteDTO respuesta= dto;
        try {
            respuesta = new PacienteDTO(pacienteLogic.createPaciente(dto.toEntity()));
            return respuesta;
        } catch (BusinessLogicException ex) {
            throw new PacienteLogicException(ex.getMessage());
        }
       
    }

    /**
     * Actualiza la información de una instancia de Paciente
     *
     * @param id Identificador de la instancia de Paciente a modificar
     * @param dto Instancia de PacienteDTO con los nuevos datos
     * @return Instancia de PacienteDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public PacienteDTO updatePaciente(@PathParam("id") Long id, PacienteDTO dto) {
        PacienteEntity entity = dto.toEntity();
        entity.setId(id);
        return new PacienteDTO(pacienteLogic.updatePaciente(entity));
    }

    /**
     * Elimina una instancia de Paciente de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePaciente(@PathParam("id") Long id) {
        pacienteLogic.deletePaciente(id);
    }

//    @GET
//    @Path("{id: \\d+}/numberofmesas")
//    public Integer getNumberOfMesasPaciente(@PathParam("id") Long id) {
//        return pacienteLogic.getNumberOfMesasPaciente(id);
//    }
//    /**
//     * 
//     * @param pacienteId
//     * @return
//     * @throws PacienteLogicException
//     */
//    @Path("{pacienteId: \\d+}-{fecha}/reservas")
//    public Class<PacienteReservasResourse> getReservasResource(@PathParam("pacienteId") Long pacienteId) throws PacienteLogicException {
//
//        PacienteDTO paciente = getPaciente(pacienteId);
//        if (paciente == null) {
//            throw new PacienteLogicException("El paciente no existe");
//        }
//        return PacienteReservasResourse.class;
//
//    }


}
