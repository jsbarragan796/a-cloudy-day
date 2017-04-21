/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
@Entity
public class ConductorEntity extends BaseEntity implements Serializable 
{
    private String usuario;
    private Long cedula;
    private String nombres;
    private String apellidos;

//    @PodamExclude
//    @OneToMany (mappedBy = "conduce",cascade = CascadeType.ALL)
//    private List<CarroEntity> carros = new ArrayList<>();
//
//    @PodamExclude
//    @OneToMany(mappedBy = "misFavoritos",cascade = CascadeType.ALL)
//    private List<FavoritoEntity> favoritos = new ArrayList<>();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

//    public List<CarroEntity> getCarros() {
//        return carros;
//    }
//
//    public void setCarros(List<CarroEntity> carros) {
//        this.carros = carros;
//    }
//
//    public List<FavoritoEntity> getFavoritos() {
//        return favoritos;
//    }
//
//    public void setFavoritos(List<FavoritoEntity> favoritos) {
//        this.favoritos = favoritos;
//    }

   
    
    
}
