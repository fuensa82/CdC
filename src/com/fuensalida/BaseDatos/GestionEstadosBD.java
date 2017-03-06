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
