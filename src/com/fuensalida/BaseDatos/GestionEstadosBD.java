/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.BaseDatos;

import com.fuensalida.NewJFrame;
import com.fuensalida.beans.EstadoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionEstadosBD {
    static final int LIBRE=0;
    static final int VENDIDA=1;
    static final int RESERVADA=2;
    static final int NO_VENDER=3;
    static final int INVITACION=4;
    static final int ABONO=5;
    static final int OTROS=99;
    
    static String getEstado2(String estado){
        if("0".equalsIgnoreCase(estado)){
            return "LIBRE";
        }else if("1".equalsIgnoreCase(estado)){
            return "VENDIDA";
        }else if("2".equalsIgnoreCase(estado)){
            return "RESERVADA";
        }else if("3".equalsIgnoreCase(estado)){
            return "NO VENDER";
        }else if("4".equalsIgnoreCase(estado)){
            return "INVITACION";
        }else if("5".equalsIgnoreCase(estado)){
            return "ABONO";
        }else if("99".equalsIgnoreCase(estado)){
            return "OTROS";
        }else{
            return "DESCONOCIDO";
        }
    }
    public static EstadoBean getEstado(int estado){
        return getEstado(""+estado);
    }
    
    public static EstadoBean getEstado(String estado){
        EstadoBean result = null;
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select idEstado, Descripcion, Color from estados where idEstado=?");
            consulta.setString(1, estado);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                result=new EstadoBean();
                result.setIdEstado(Integer.parseInt(resultado.getString(1)));
                result.setDescripcion(resultado.getString(2));
                result.setColor(resultado.getString(3));
            }
            System.out.println(result);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionAuditorioBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
