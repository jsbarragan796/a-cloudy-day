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
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.FavoritoEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class FavoritoDetailDTO extends FavoritoDTO {

    @PodamExclude
    private ConductorDTO conductor;

    /**
     *
     */
    public FavoritoDetailDTO() {
        super();
    }

    /**
     * Crea un objeto FavoritoDetailDTO a partir de un objeto FavoritoEntity incluyendo
     * los atributos de FavoritoDTO.
     *
     * @param entity Entidad FavoritoEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public FavoritoDetailDTO(FavoritoEntity entity) {
        super(entity);
        if (entity.getConductor() != null) {
            this.conductor = new ConductorDTO(entity.getConductor());
        }
    }

    /**
     * Convierte un objeto FavoritoDetailDTO a FavoritoEntity incluyendo los atributos
     * de FavoritoDTO.
     *
     * @return objeto FavoritoEntity.
     *
     */
    @Override
    public FavoritoEntity toEntity() {
        FavoritoEntity entity = super.toEntity();
        if (this.getConductor() != null) {
            entity.setConductor(this.getConductor().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo conductor.
     *
     * @return atributo conductor.
     *
     */
    public ConductorDTO getConductor() {
        return conductor;
    }

    /**
     * Establece el valor del atributo conductor.
     *
     * @param conductor nuevo valor del atributo
     *
     */
    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

}
