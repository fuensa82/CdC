/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.BaseDatos;

import com.fuensalida.beans.ButacaSesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionAuditorioBD {
    public static ArrayList getEstadoButacas(int idActividad, int idSesion){
        ArrayList result=new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select idButaca, idActividad, idSesion, idEstado, Motivo from butacassesion where idActividad=? and idSesion=?");
            consulta.setString(1, ""+idActividad);
            consulta.setString(2, ""+idSesion);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ButacaSesion b=new ButacaSesion();
                b.setIdButaca(Integer.parseInt(resultado.getString(1)));
                b.setIdActividad(Integer.parseInt(resultado.getString(2)));
                b.setIdSesion(Integer.parseInt(resultado.getString(3)));
                b.setIdEstado(Integer.parseInt(resultado.getString(4)));
                result.add(b);
            }
            System.out.println(result);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public static String getNumButaca(int idButaca){
        String result="nulo";
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select fila, asiento from butacas where idButaca=?");
            consulta.setString(1, ""+idButaca);
            ResultSet resultado = consulta.executeQuery();
            
            //Solo debe haber una butaca, pero lo recorremos
            resultado.next();
            result="Fila "+resultado.getString(1)+", asiento "+resultado.getString(2);
            
            System.out.println(result);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    /**
     * select count(idButaca) from butacassesion where idActividad=? and idSesion=? and idEstado!=1
     */
    /**
     * Calcula las butacas ocupadas de la secion y actividad dada. Cuentas todas las butacas menos las que están libres
     * @param actividad
     * @param sesion
     * @return 
     */
    public static int getOcupadas(int actividad, int sesion){
        int result=0;
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select count(idButaca) from butacassesion where idActividad=? and idSesion=? and idEstado!=1");
            consulta.setString(1, ""+actividad);
            consulta.setString(2, ""+sesion);
            ResultSet resultado = consulta.executeQuery();
            resultado.next();
            result=Integer.parseInt(resultado.getString(1));
            //result="Fila "+resultado.getString(1)+", asiento "+resultado.getString(1);
            
            System.out.println("Total ocupadas: "+result);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                System.out.println("Saliendo de la base de datos");
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    /**
     * Calcula las butacas en el estado dado de la secion y actividad dada.
     * @param actividad
     * @param sesion
     * @param estado idEstado que se quiere consultar
     * @return 
     */
    public static int getButacasEstado(int actividad, int sesion, int estado){
        int result=0;
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select count(idButaca) from butacassesion where idActividad=? and idSesion=? and idEstado=?");
            consulta.setString(1, ""+actividad);
            consulta.setString(2, ""+sesion);
            consulta.setString(3, ""+estado);
            ResultSet resultado = consulta.executeQuery();
            resultado.next();
            result=Integer.parseInt(resultado.getString(1));
            //result="Fila "+resultado.getString(1)+", asiento "+resultado.getString(1);
            
            System.out.println("Total butacas estado "+estado+": "+result);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                System.out.println("Saliendo de la base de datos");
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public static int getButacasVendidas(int actividad, int sesion){
        return getButacasEstado(actividad, sesion,2);      
    }
    public static int getButacasReservadas(int actividad, int sesion){
        return getButacasEstado(actividad, sesion,3);      
    }
    public static int getButacasNoVender(int actividad, int sesion){
        return getButacasEstado(actividad, sesion,4);      
    }
    public static int getButacasInvitaciones(int actividad, int sesion){
        return getButacasEstado(actividad, sesion,5);      
    }
    public static int getButacasAbonos(int actividad, int sesion){
        return getButacasEstado(actividad, sesion,6);      
    }
}
