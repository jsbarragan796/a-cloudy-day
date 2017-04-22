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

import co.edu.uniandes.rest.parquio.dtos.ReservaDTO;
import co.edu.uniandes.rest.parquio.dtos.ReservaDetailDTO;
import co.edu.uniandes.rest.parquio.dtos.ParqueaderoDetailDTO;
import co.edu.uniandes.sisteam.parquio.api.IReservaLogic;
import co.edu.uniandes.sisteam.parquio.api.IParqueaderoLogic;
import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
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

import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/parqueaderos/{parqueaderoId: \\d+}/reservas")
public class ReservaResource {

    @Inject
    private IReservaLogic reservaLogic;

    @Inject
    private IParqueaderoLogic parqueaderoLogic;

    @PathParam("parqueaderoId")
    private Long parqueaderoId;

    /**
     * Convierte una lista de ReservaEntity a una lista de
     * ReservaDetailDTO
     *
     * @param entityList Lista de ReservaEntity a convertir
     * @return Lista de ReservaDetailDTO convertida
     *
     */
    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }

    public void existsParqueadero(Long parqueaderoId) {
        ParqueaderoDetailDTO parqueadero = new ParqueaderoDetailDTO(parqueaderoLogic.getParqueaderoId(parqueaderoId));
        if (parqueadero == null) {
            throw new WebApplicationException(404);
        }
    }

    /**
     * Obtiene los datos de los Reservas de una compañía a partir del ID de
     * la Parqueadero
     *
     *
     * @return Lista de ReservaDetailDTO con los datos del Reserva
     * consultado
     *
     */
    @GET
    public List<ReservaDetailDTO> getReservasParqueadero() {
        existsParqueadero(parqueaderoId);
        
        List<ReservaEntity> reservas = reservaLogic.getReservasParqueadero(parqueaderoId);

        return listEntity2DTO(reservas);
    }
     /**
     * Obtiene los datos de una instancia de Reserva a partir de su ID
     * asociado a un Parqueadero
     *
     * @param reservaId Identificador de la instancia a consultar
     * @return Instancia de ReservaDetailDTO con los datos del Reserva
     * consultado
     *
     */
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("reservaId") Long reservaId) {
       
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
       
        return new ReservaDetailDTO(entity);
    }

    /**
     * Asocia un Reserva existente a un Parqueadero
     *
     * @param dto Objeto de ReservaDetailDTO con los datos nuevos
     * @return Objeto de ReservaDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO dto) throws BusinessLogicException {
        existsParqueadero(parqueaderoId);
        return new ReservaDetailDTO(reservaLogic.createReserva(parqueaderoId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Reserva.
     *
     * @param reservaId Identificador de la instancia de Reserva a
     * modificar
     * @param dto Instancia de ReservaDetailDTO con los nuevos datos.
     * @return Instancia de ReservaDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDetailDTO dto) {
        existsParqueadero(parqueaderoId);
        ReservaEntity entity = dto.toEntity();
        entity.setId(reservaId);
        ReservaEntity oldEntity = reservaLogic.getReserva(reservaId);
        return new ReservaDetailDTO(reservaLogic.updateReserva(parqueaderoId, entity));
    }

    /**
     * Elimina una instancia de Reserva de la base de datos.
     *
     * @param reservaId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) {
        existsParqueadero(parqueaderoId);
        reservaLogic.deleteReserva(reservaId);
    }
    
    
   

}
