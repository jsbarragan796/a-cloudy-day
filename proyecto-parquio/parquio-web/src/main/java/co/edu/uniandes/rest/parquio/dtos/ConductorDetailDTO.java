/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.parquio.dtos;

import co.edu.uniandes.sisteam.parquio.entities.CarroEntity;
import co.edu.uniandes.sisteam.parquio.entities.ConductorEntity;
import co.edu.uniandes.sisteam.parquio.entities.FavoritoEntity;
import co.edu.uniandes.sisteam.parquio.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public class ConductorDetailDTO extends ConductorDTO {

    private List<CarroDTO> carros = new ArrayList<>();

    private List<FavoritoDTO> favoritos = new ArrayList<>();
    
    private List<ReservaDTO> reservas = new ArrayList<>();

    public ConductorDetailDTO() {
        super();
    }


    public ConductorDetailDTO(ConductorEntity entity) {
        super(entity);

        if (entity != null) {

            List<CarroEntity> carrosEntity = entity.getCarros();
            for (CarroEntity carro : carrosEntity) {
                this.carros.add(new CarroDTO(carro));
            }

            List<FavoritoEntity> favoritosEntity = entity.getFavoritos();
            for (FavoritoEntity favorito : favoritosEntity) {
                this.favoritos.add(new FavoritoDTO(favorito));
            }
            
             List<ReservaEntity> reservasEntity = entity.getReservas();
            for (ReservaEntity reserva : reservasEntity) {
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
    }

    @Override
    public ConductorEntity toEntity() {

        ConductorEntity entity = super.toEntity();
 
        List<CarroDTO> carros2 = this.carros;
        for (CarroDTO carro : carros2) {
            entity.getCarros().add(carro.toEntity());
        }
        
        List<FavoritoDTO> favoritos2 = this.favoritos;
        for (FavoritoDTO favorito : favoritos2) {
            entity.getFavoritos().add(favorito.toEntity());
        }
        
        List<ReservaDTO> reservas2 = this.reservas;
        for (ReservaDTO reserva : reservas2) {
            entity.getReservas().add(reserva.toEntity());
        }

        return entity;

    }

    public List<CarroDTO> getCarros() {
        return carros;
    }

    public void setCarros(List<CarroDTO> carros) {
        this.carros = carros;
    }

    public List<FavoritoDTO> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<FavoritoDTO> favoritos) {
        this.favoritos = favoritos;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    
    


}
