/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.ejbs;

import co.edu.uniandes.sisteam.corazon.api.IMarcapasosLogic;
import co.edu.uniandes.sisteam.corazon.entities.MarcapasosEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.corazon.persistence.MarcapasosPersistence;
import co.edu.uniandes.sisteam.corazon.persistence.PacientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class MarcapasosLogic implements IMarcapasosLogic {
    
    @Inject
    private MarcapasosPersistence persistence;
    
    @Inject
    private PacientePersistence paciente;
    
    @Override
    public MarcapasosEntity getMarcapasosId(long id) {
        return persistence.find(id);
    }
    
    @Override
    public List<MarcapasosEntity> getAllMarcapasos() {
        return persistence.findAll();
    }
    
    @Override
    public MarcapasosEntity getMarcapasosNumeroSerie(String numeroSerie) {
        return persistence.findByNumeroSerie(numeroSerie);
    }
    
    @Override
    public MarcapasosEntity createMarcapasos(MarcapasosEntity entity, Long id) throws BusinessLogicException {
        
        MarcapasosEntity alreadyExist = getMarcapasosNumeroSerie(entity.getNumeroSerie());
        PacienteEntity existe = paciente.find(id);
        if (alreadyExist != null) {
            throw new BusinessLogicException("Ya existe un marcapasos con ese numero de serie");
        }        
        if (existe == null) {
            throw new BusinessLogicException("No exite cliente con esa identificacion: " + id);
        } else {
            existe.setMarcapasos(entity);
            entity.setPaciente(existe);          
            persistence.create(entity);
            paciente.update(existe);
        }
        return entity;
        
    }
    
    @Override
    public MarcapasosEntity updateMarcapasos(MarcapasosEntity entity) throws BusinessLogicException {
        
        MarcapasosEntity alreadyExist = getMarcapasosNumeroSerie(entity.getNumeroSerie());
        if (alreadyExist == null) {
            throw new BusinessLogicException("No existe un marcapasos con ese numero de serie para actualizar");
        } else {
            persistence.update(entity);
        }
        return entity;
    }
    
    @Override
    public void deleteMarcapasos(long id) {
        persistence.delete(id);
        
    }
    
}
