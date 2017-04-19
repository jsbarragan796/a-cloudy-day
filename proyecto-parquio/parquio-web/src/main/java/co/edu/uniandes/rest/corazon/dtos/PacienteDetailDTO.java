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
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.ConsejoEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PacienteDetailDTO extends PacienteDTO {

    private MedicoDTO medicoTratante;
    
    private List<MedicoDTO> medicos=new ArrayList<>();

    private MarcapasosDTO marcapasos;

    private List<ConsejoDTO> consejosRecibidos = new ArrayList<>();

    public PacienteDetailDTO() {
        super();
    }

    /**
     * Crea un objeto PacienteDetailDTO a partir de un objeto PacienteEntity
     * incluyendo los atributos de PacienteDTO.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public PacienteDetailDTO(PacienteEntity entity) {
        super(entity);
        if (entity != null) {
            List<ConsejoEntity> consejosEntityRecibidos = entity.getConsejosRecibidos();
            for (ConsejoEntity consejoRecibido : consejosEntityRecibidos) {
                this.consejosRecibidos.add(new ConsejoDTO(consejoRecibido));
            }
            
            List<MedicoEntity> medicosa = entity.getMedicos();
            for (MedicoEntity medico : medicosa) {
                this.medicos.add(new MedicoDTO(medico));
            }
           if(entity.getMedicoTratante()!=null){
           medicoTratante= new MedicoDTO(entity.getMedicoTratante());
           }
           if(entity.getMarcapasos()!=null){
           this.marcapasos=new MarcapasosDTO(entity.getMarcapasos());
           }
        }

    }

    /**
     * Convierte un objeto PacienteDetailDTO a PacienteEntity incluyendo los
     * atributos de PacienteDTO.
     *
     * @return objeto PacienteEntity.
     *
     */
    @Override
    public PacienteEntity toEntity() {

        PacienteEntity entity = super.toEntity();
        
        List<ConsejoDTO> consejosRecibidosDTO = this.consejosRecibidos;

        for (ConsejoDTO consejoRealizado : consejosRecibidosDTO) {
            entity.getConsejosRecibidos().add(consejoRealizado.toEntity());
        }

        return entity;
    }

    public MedicoDTO getMedicoTratante() {
        return medicoTratante;
    }

    public void setMedicoTratante(MedicoDTO medicoTratante) {
        this.medicoTratante = medicoTratante;
    }

    public MarcapasosDTO getMarcapasos() {
        return marcapasos;
    }

    public void setMarcapasos(MarcapasosDTO marcapasos) {
        this.marcapasos = marcapasos;
    }

    public List<ConsejoDTO> getConsejosRecibidos() {
        return consejosRecibidos;
    }

    public void setConsejosRecibidos(List<ConsejoDTO> consejosRecibidos) {
        this.consejosRecibidos = consejosRecibidos;
    }
    public List<MedicoDTO> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<MedicoDTO> medicos) {
        this.medicos = medicos;
    }

}
