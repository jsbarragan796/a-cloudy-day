package co.edu.uniandes.rest.corazon.resources;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.rest.restaurantes.resources;
//
//
//
//import  co.edu.uniandes.rest.restaurantes.dtos.ReservaDTO; 
//import co.edu.uniandes.rest.restaurantes.dtos.ReservaDetailDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.ReservaLogicException;
//import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;
//import co.edu.uniandes.sisteam.restaurantes.api.IReservaLogic;
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import java.util.List;
//import javax.inject.Inject;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
///**
// * URI: sucursales/{sucursalId: \\d+}-{fecha}/reservas
// *
// * @author BarraganJeronimo
// */
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class SucursalReservasResourse {
//    
//    
//   // @Inject
//    private IReservaLogic reservaLogic;
//
//   
//    private List<ReservaDetailDTO> listEntityDTO(List<ReservaEntity> entityList) {
//
//        List<ReservaDetailDTO> list = new ArrayList<>();
//        for (ReservaEntity entity : entityList) {
//            list.add(new ReservaDetailDTO(entity));
//        }
//        return list;
//    }
//
//  
//
//    /**
//     * Obtiene el listado de reservaciones.
//     *
//     * @param sudursalId
//     * @param nFecha
//     * @param fecha
//     * @return lista de reservas para un cliente con id dado por parametro
//     * @throws ReservaLogicException excepción retornada por la lógica ( cuando id del cliente no tiene reservas)
//     * @throws co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
//     * @throws java.text.ParseException
//     */
//    @GET 
// 
//    public List<ReservaDetailDTO> getReservas(@PathParam("sucursalId") Long sudursalId, Date nFecha /**@PathParam("fecha") String fecha*/)  throws ReservaLogicException, SucursalLogicException, ParseException {
//       
////        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
////        Date nFecha = sdf.parse(fecha);
//        
//       return listEntityDTO(reservaLogic.getReservaSucursal(sudursalId, nFecha));
//    }
//    
//   
//    
//    
//}
