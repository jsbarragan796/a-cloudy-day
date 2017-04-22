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

import co.edu.uniandes.rest.parquio.dtos.CarroDTO;
import co.edu.uniandes.rest.parquio.dtos.CarroDetailDTO;
import co.edu.uniandes.rest.parquio.dtos.ConductorDetailDTO;
import co.edu.uniandes.sisteam.parquio.api.ICarroLogic;
import co.edu.uniandes.sisteam.parquio.api.IConductorLogic;
import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;
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
@Path("/conductores/{conductorId: \\d+}/carros")
public class CarroResource {

    @Inject
    private ICarroLogic carroLogic;

    @Inject
    private IConductorLogic conductorLogic;

    @PathParam("conductorId")
    private Long conductorId;

    /**
     * Convierte una lista de CarroEntity a una lista de
     * CarroDetailDTO
     *
     * @param entityList Lista de CarroEntity a convertir
     * @return Lista de CarroDetailDTO convertida
     *
     */
    private List<CarroDetailDTO> listEntity2DTO(List<CarroEntity> entityList) {
        List<CarroDetailDTO> list = new ArrayList<>();
        for (CarroEntity entity : entityList) {
            list.add(new CarroDetailDTO(entity));
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
     * Obtiene los datos de los Carros de una compañía a partir del ID de
     * la Conductor
     *
     *
     * @return Lista de CarroDetailDTO con los datos del Carro
     * consultado
     *
     */
    @GET
    public List<CarroDetailDTO> getCarrosConductor() {
        existsConductor(conductorId);
        
        List<CarroEntity> carros = carroLogic.getCarrosConductor(conductorId);

        return listEntity2DTO(carros);
    }
     /**
     * Obtiene los datos de una instancia de Carro a partir de su ID
     * asociado a un Conductor
     *
     * @param carroId Identificador de la instancia a consultar
     * @return Instancia de CarroDetailDTO con los datos del Carro
     * consultado
     *
     */
    @GET
    @Path("{carroId: \\d+}")
    public CarroDetailDTO getCarro(@PathParam("carroId") Long carroId) {
       
        CarroEntity entity = carroLogic.getCarro(carroId);
       
        return new CarroDetailDTO(entity);
    }

    /**
     * Asocia un Carro existente a un Conductor
     *
     * @param dto Objeto de CarroDetailDTO con los datos nuevos
     * @return Objeto de CarroDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public CarroDetailDTO createCarro(CarroDetailDTO dto) throws BusinessLogicException {
        existsConductor(conductorId);
        return new CarroDetailDTO(carroLogic.createCarro(conductorId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Carro.
     *
     * @param carroId Identificador de la instancia de Carro a
     * modificar
     * @param dto Instancia de CarroDetailDTO con los nuevos datos.
     * @return Instancia de CarroDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{carroId: \\d+}")
    public CarroDetailDTO updateCarro(@PathParam("carroId") Long carroId, CarroDetailDTO dto) {
        existsConductor(conductorId);
        CarroEntity entity = dto.toEntity();
        entity.setId(carroId);
        CarroEntity oldEntity = carroLogic.getCarro(carroId);
        return new CarroDetailDTO(carroLogic.updateCarro(conductorId, entity));
    }

    /**
     * Elimina una instancia de Carro de la base de datos.
     *
     * @param carroId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{carroId: \\d+}")
    public void deleteCarro(@PathParam("carroId") Long carroId) {
        existsConductor(conductorId);
        carroLogic.deleteCarro(carroId);
    }
    
    
   

}
