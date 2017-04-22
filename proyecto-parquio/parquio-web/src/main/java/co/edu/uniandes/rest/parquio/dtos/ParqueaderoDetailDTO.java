/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;
import co.edu.uniandes.sisteam.parquio.entities.ParqueaderoEntity;
import co.edu.uniandes.sisteam.parquio.entities.FavoritoEntity;
import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public class ParqueaderoDetailDTO extends ParqueaderoDTO {
    
    private List<ReservaDTO> reservas = new ArrayList<>();

    public ParqueaderoDetailDTO() {
        super();
    }


    public ParqueaderoDetailDTO(ParqueaderoEntity entity) {
        super(entity);

        if (entity != null) 
        {

             List<ReservaEntity> reservasEntity = entity.getReservas();
            for (ReservaEntity reserva : reservasEntity) {
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
    }

    @Override
    public ParqueaderoEntity toEntity() {

        ParqueaderoEntity entity = super.toEntity();
        
        List<ReservaDTO> reservas2 = this.reservas;
        for (ReservaDTO reserva : reservas2) {
            entity.getReservas().add(reserva.toEntity());
        }

        return entity;

    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    
    


}
