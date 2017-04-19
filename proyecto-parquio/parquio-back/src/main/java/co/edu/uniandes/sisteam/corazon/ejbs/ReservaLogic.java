package co.edu.uniandes.sisteam.corazon.ejbs;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.ejbs;
//
//import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
//import co.edu.uniandes.sisteam.restaurantes.api.IMesaLogic;
//import co.edu.uniandes.sisteam.restaurantes.api.IReservaLogic;
//import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
//import co.edu.uniandes.sisteam.restaurantes.persistence.ReservaPersistence;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.NoResultException;
//
///**
// *
// * @author BarraganJeronimo
// */
//@Stateless
//public class ReservaLogic implements IReservaLogic {
//
//    @Inject
//    private ReservaPersistence persistence;
//
//    @Inject
//    private IMesaLogic mesaLogic;
//
//    @Inject
//    private IClienteLogic clienteLogic;
//    
//   
//
//    /**
//     * Retorna una lista de ReservasEntity que tiene una sucursal en una fecha
//     * determinada
//     *
//     * @param sucursalid Identificacion de la sucursal
//     * @param fecha Fecha en la que se desea cononcer las reservas.
//     * @return Lista de las ReservasEntity
//     */
//    @Override
//    public List<ReservaEntity> getReservaSucursal(Long sucursalid, Date fecha) {
//
//        List<MesaEntity> mesas = mesaLogic.getMesas(sucursalid);
//        List<ReservaEntity> resp = new ArrayList();
//        for (MesaEntity mesa : mesas) {
//            List<ReservaEntity> e = mesa.getRerservas();
//            if (!e.isEmpty()) {
//               for (ReservaEntity reserva : e)  {
//                   if(reserva.getFecha().compareTo(fecha)==0)
//                   resp.add(reserva);
//               }
//            
//            }
//        }
//
//        return resp;
//    }
//
//    /**
//     * Retorna la lista de ReservasEntity que estan asociadas a un cliente con
//     * el id dado.
//     *
//     * @param clienteid Identificador del cliente.
//     * @return retorna lista de reservas que cumplen con la busqueda.
//     */
//    @Override
//    public List<ReservaEntity> getReservasCliente(Long clienteid) {
//        ClienteEntity cliente = clienteLogic.getCliente(clienteid);
//        return cliente.getReservas();
//    }
//
//    /**
//     * sea socia un idcliente a un idmesa a una ReservaEntity y se persiste en
//     * la base de datos.
//     *
//     * @param clienteid Identificación del cliente.
//     * @param mesaid Identificación de la mesa.
//     * @param entity ReservaEntity que se desea persistir.
//     * @return retornar la ReservaEntity, en caso que no se puede almacenar en
//     * la base datos se retorna null.
//     * @throws co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException
//     */
//    @Override
//    public ReservaEntity createReserva(Long clienteid, Long mesaid, ReservaEntity entity) throws BusinessLogicException {
//
//        MesaEntity mesa = mesaLogic.getMesa(mesaid);
//        ClienteEntity cliente = clienteLogic.getCliente(clienteid);
//        entity.setMesa(mesa);
//        entity.setCliente(cliente);
//
//      
//        return persistence.create(entity);
//
//    }
//
//    /**
//     * Se actualiza los valores de una ReservaEntity
//     *
//     * @param clienteid Identificación del cliente.
//     * @param mesaid Identificación de la mesa.
//     * @param entity ReservaEntity con los datos que se desean actualizar.
//     * @return la ReservaEntity actualizada.
//     * @throws co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException
//     * @throws IllegalArgumentException Lanza esta excepción cuando no exista en
//     * la base datos la reserva que se desea actualizar.
//     */
//    @Override
//    public ReservaEntity updateReserva(Long clienteid, Long mesaid, ReservaEntity entity) throws BusinessLogicException {
//
//      
//            MesaEntity mesa = mesaLogic.getMesa(mesaid);
//            ClienteEntity cliente = clienteLogic.getCliente(clienteid);
//            entity.setMesa(mesa);
//            entity.setCliente(cliente);
//            return persistence.update(entity);
//        
//            
//        
//
//    }
//
//    /**
//     * eliminar la ReservaEntity con la identificación dada.
//     *
//     * @param reservaid identificación de la ReservaEntity.
//     * @throws IllegalArgumentException Lanza esta excepción cuando no se
//     * encuentre un una reserva con la identificación dada.
//     */
//    @Override
//    public void deleteReserva(Long reservaid) throws IllegalArgumentException {
//
//        try {
//            ReservaEntity aBorrar = getReservaId(reservaid);
//            persistence.delete(aBorrar.getId());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("La reserva no se puede eliminar ya que no existe");
//        }
//    }
//
//    /**
//     * Retorna la reserva con el id solicitado.
//     *
//     * @param reservaid EL identificador de la reserva
//     * @return reserva buscada.
//     * @throws IllegalArgumentException Lanza esta excepción cuando no se
//     * encuentre una reserva con la identificación dada.
//     */
//    @Override
//    public ReservaEntity getReservaId(Long reservaid) throws IllegalArgumentException {
//        try {
//            return persistence.find(reservaid);
//        } catch (NoResultException e) {
//            throw new IllegalArgumentException("La reserva no existe");
//        }
//
//    }
//
//    /**
//     * Retorna todas las reservas.
//     *
//     * @return lista de reservas.
//     */
//    @Override
//    public List<ReservaEntity> getAllReservas() {
//        return persistence.findAll();
//    }
//    
//    
//
//}
