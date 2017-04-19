/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.api;

import co.edu.uniandes.sisteam.parquio.entities.MedicoEntity;
import co.edu.uniandes.sisteam.parquio.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IMedicoLogic {

    public MedicoEntity getMedicoId(Long medicoId);

    public MedicoEntity getMedicoCedula(int cedula);

    public MedicoEntity getMedicoTarjetaProfesional(String nTarjetaProfesional);

    public List<MedicoEntity> getAllMedicos();

    public MedicoEntity createMedico(MedicoEntity entity) throws BusinessLogicException;

    public MedicoEntity updateMedico(MedicoEntity entity) throws BusinessLogicException;
    
    public void agregarPacienteMedico(Long medicoid,Long idPaciente);
    public void agregarPacienteHistorial(Long medicoid,Long idPaciente);
    
    public void deleteMedico(Long medicoid);

}
