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
package co.edu.uniandes.rest.parquio.resources;


import co.edu.uniandes.rest.parquio.dtos.ConductorDTO;
import co.edu.uniandes.rest.parquio.dtos.ConductorDetailDTO;
import co.edu.uniandes.sisteam.parquio.api.IConductorLogic;
import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
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
import javax.inject.Inject;
import javax.ws.rs.QueryParam;

@Path("/conductores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConductorResource {

    @Inject
    private IConductorLogic conductorLogic;

    /**
     * Convierte una lista de ConductorEntity a una lista de ConductorDTO.
     *
     * @param entityList Lista de ConductorEntity a convertir.
     * @return Lista de ConductorDetailDTO convertida.
     *
     */
    private List<ConductorDTO> listEntity2DTO(List<ConductorEntity> entityList) {
        List<ConductorDTO> list = new ArrayList<>();
        for (ConductorEntity entity : entityList) {
            list.add(new ConductorDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Conductor
     *
     * @return Colección de objetos de ConductorDetailDTO
     *
     */
    @GET
    public List<ConductorDTO> getConductores() {

        return listEntity2DTO(conductorLogic.getConductores());
    }

    /**
     * Obtiene los datos de una instancia de Conductor a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ConductorDetailDTO con los datos del Conductor
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public ConductorDetailDTO getConductor(@PathParam("id") Long id) {
        return new ConductorDetailDTO(conductorLogic.getConductorId(id));
    }

    /**
     * Se encarga de crear un Conductor en la base de datos
     *
     * @param dto Objeto de ConductorDetailDTO con los datos nuevos
     * @return Objeto de ConductorDetailDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public ConductorDetailDTO createConductor(ConductorDetailDTO dto) throws BusinessLogicException {
        return new ConductorDetailDTO(conductorLogic.createConductor(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Conductor
     *
     * @param id Identificador de la instancia de Conductor a modificar
     * @param dto Instancia de ConductorDetailDTO con los nuevos datos
     * @return Instancia de ConductorDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public ConductorDetailDTO updateConductor(@PathParam("id") Long id, ConductorDetailDTO dto) throws BusinessLogicException {
        ConductorEntity entity = dto.toEntity();
        entity.setId(id);
        return new ConductorDetailDTO(conductorLogic.updateConductor(entity));
    }

    /**
     * Elimina una instancia de Conductor de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteConductor(@PathParam("id") Long id) {
        conductorLogic.deleteConductor(id);
    }
}
