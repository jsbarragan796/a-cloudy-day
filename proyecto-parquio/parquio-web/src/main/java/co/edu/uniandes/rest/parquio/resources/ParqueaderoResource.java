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

import co.edu.uniandes.rest.parquio.dtos.ParqueaderoDTO;
import co.edu.uniandes.rest.parquio.dtos.ReservaDTO;
import co.edu.uniandes.rest.parquio.dtos.ReservaDetailDTO;
import co.edu.uniandes.sisteam.parquio.api.IParqueaderoLogic;
import co.edu.uniandes.sisteam.parquio.api.IReservaLogic;
import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;
import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import static java.lang.Math.toIntExact;
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
import javax.ws.rs.WebApplicationException;

@Path("/parqueaderos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParqueaderoResource {

    @Inject
    private IParqueaderoLogic parqueaderoLogic;

    @Inject
    private IReservaLogic reservaLogic;

    /**
     * Convierte una lista de ParqueaderoEntity a una lista de ParqueaderoDTO.
     *
     * @param entityList Lista de ParqueaderoEntity a convertir.
     * @return Lista de ParqueaderoDTO convertida.
     *
     */
    private List<ParqueaderoDTO> listEntity2DTO(List<ParqueaderoEntity> entityList) {
        List<ParqueaderoDTO> list = new ArrayList<>();
        for (ParqueaderoEntity entity : entityList) {
            list.add(new ParqueaderoDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Parqueadero
     *
     * @return Colección de objetos de ParqueaderoDTO
     *
     */
    @GET
    public List<ParqueaderoDTO> getParqueaderos() {

        return listEntity2DTO(parqueaderoLogic.getParqueaderos());
    }

    /**
     * Obtiene los datos de una instancia de Parqueadero a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ParqueaderoDTO con los datos del Parqueadero
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public ParqueaderoDTO getParqueadero(@PathParam("id") Long id) {
        return new ParqueaderoDTO(parqueaderoLogic.getParqueaderoId(id));
    }

   

    /**
     * Se encarga de crear un Parqueadero en la base de datos
     *
     * @param dto Objeto de ParqueaderoDTO con los datos nuevos
     * @return Objeto de ParqueaderoDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public ParqueaderoDTO createParqueadero(ParqueaderoDTO dto) throws BusinessLogicException {
        return new ParqueaderoDTO(parqueaderoLogic.createParqueadero(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Parqueadero
     *
     * @param id Identificador de la instancia de Parqueadero a modificar
     * @param dto Instancia de ParqueaderoDTO con los nuevos datos
     * @return Instancia de ParqueaderoDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public ParqueaderoDTO updateParqueadero(@PathParam("id") Long id, ParqueaderoDTO dto) throws BusinessLogicException {
        ParqueaderoEntity entity = dto.toEntity();
        entity.setId(id);
        return new ParqueaderoDTO(parqueaderoLogic.updateParqueadero(entity));
    }

    /**
     * Elimina una instancia de Parqueadero de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteParqueadero(@PathParam("id") Long id) {
        parqueaderoLogic.deleteParqueadero(id);
    }
}
