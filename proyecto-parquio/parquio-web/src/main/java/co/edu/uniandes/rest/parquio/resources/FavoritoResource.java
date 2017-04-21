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

import co.edu.uniandes.rest.parquio.dtos.FavoritoDTO;
import co.edu.uniandes.rest.parquio.dtos.FavoritoDetailDTO;
import co.edu.uniandes.rest.parquio.dtos.ConductorDetailDTO;
import co.edu.uniandes.sisteam.parquio.api.IFavoritoLogic;
import co.edu.uniandes.sisteam.parquio.api.IConductorLogic;
import co.edu.uniandes.sisteam.parquio.entities.FavoritoEntity;
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
@Path("/conductores/{conductorId: \\d+}/favoritos")
public class FavoritoResource {

    @Inject
    private IFavoritoLogic favoritoLogic;

    @Inject
    private IConductorLogic conductorLogic;

    @PathParam("conductorId")
    private Long conductorId;

    /**
     * Convierte una lista de FavoritoEntity a una lista de
     * FavoritoDetailDTO
     *
     * @param entityList Lista de FavoritoEntity a convertir
     * @return Lista de FavoritoDetailDTO convertida
     *
     */
    private List<FavoritoDetailDTO> listEntity2DTO(List<FavoritoEntity> entityList) {
        List<FavoritoDetailDTO> list = new ArrayList<>();
        for (FavoritoEntity entity : entityList) {
            list.add(new FavoritoDetailDTO(entity));
        }
        return list;
    }

    public void existsConductor(Long conductorId) {
        ConductorDetailDTO conductor = new ConductorDetailDTO(conductorLogic.getConductorId(conductorId));
        if (conductor == null) {
            throw new WebApplicationException(404);
        }
    }

    /**
     * Obtiene los datos de los Favoritos de una compañía a partir del ID de
     * la Conductor
     *
     *
     * @return Lista de FavoritoDetailDTO con los datos del Favorito
     * consultado
     *
     */
    @GET
    public List<FavoritoDetailDTO> getFavoritosConductor() {
        existsConductor(conductorId);
        
        List<FavoritoEntity> favoritos = favoritoLogic.getFavoritosConductor(conductorId);

        return listEntity2DTO(favoritos);
    }
     /**
     * Obtiene los datos de una instancia de Favorito a partir de su ID
     * asociado a un Conductor
     *
     * @param favoritoId Identificador de la instancia a consultar
     * @return Instancia de FavoritoDetailDTO con los datos del Favorito
     * consultado
     *
     */
    @GET
    @Path("{favoritoId: \\d+}")
    public FavoritoDetailDTO getFavorito(@PathParam("favoritoId") Long favoritoId) {
       
        FavoritoEntity entity = favoritoLogic.getFavorito(favoritoId);
       
        return new FavoritoDetailDTO(entity);
    }

    /**
     * Asocia un Favorito existente a un Conductor
     *
     * @param dto Objeto de FavoritoDetailDTO con los datos nuevos
     * @return Objeto de FavoritoDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public FavoritoDetailDTO createFavorito(FavoritoDetailDTO dto) throws BusinessLogicException {
        existsConductor(conductorId);
        return new FavoritoDetailDTO(favoritoLogic.createFavorito(conductorId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Favorito.
     *
     * @param favoritoId Identificador de la instancia de Favorito a
     * modificar
     * @param dto Instancia de FavoritoDetailDTO con los nuevos datos.
     * @return Instancia de FavoritoDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{favoritoId: \\d+}")
    public FavoritoDetailDTO updateFavorito(@PathParam("favoritoId") Long favoritoId, FavoritoDetailDTO dto) {
        existsConductor(conductorId);
        FavoritoEntity entity = dto.toEntity();
        entity.setId(favoritoId);
        FavoritoEntity oldEntity = favoritoLogic.getFavorito(favoritoId);
        return new FavoritoDetailDTO(favoritoLogic.updateFavorito(conductorId, entity));
    }

    /**
     * Elimina una instancia de Favorito de la base de datos.
     *
     * @param favoritoId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{favoritoId: \\d+}")
    public void deleteFavorito(@PathParam("favoritoId") Long favoritoId) {
        existsConductor(conductorId);
        favoritoLogic.deleteFavorito(favoritoId);
    }
    
    
   

}
