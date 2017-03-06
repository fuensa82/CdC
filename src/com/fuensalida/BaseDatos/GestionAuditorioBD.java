/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.BaseDatos;

import com.fuensalida.NewJFrame;
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
    public static ArrayList getEstadoButacas(String idActividad, String idSesion){
        ArrayList result=new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select idButaca, idActividad, idSesion, idEstado, Motivo from butacassesion where idActividad=? and idSesion=?");
            consulta.setString(1, idActividad);
            consulta.setString(2, idSesion);
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
    public static String getNumButaca(int idButaca){
        String result="nulo";
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement("select fila, asiento from butacas where idButaca=?");
            consulta.setString(1, ""+idButaca);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                result="Fila "+resultado.getString(1)+", asiento "+resultado.getString(2);
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
