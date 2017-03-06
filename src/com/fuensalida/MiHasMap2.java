/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida;

import com.fuensalida.beans.ButacaSesion;
import java.util.HashMap;
import javax.swing.JToggleButton;

/**
 *
 * @author vPalomo
 */
public class MiHasMap2 {
    private HashMap butaca;
    private HashMap butacaJT;
    public MiHasMap2(){
        butaca=new HashMap();
        butacaJT=new HashMap();
    }
    /**
     * 
     * @param i Key de la butaca que se quiere recuperar
     * @return 
     */
    public ButacaSesion getButaca(int i){
        return (ButacaSesion) butaca.get(i);
    }
    public JToggleButton getButacaJT(int i){
        return (JToggleButton)butacaJT.get(i);
    }
    public void put(int key, ButacaSesion b, JToggleButton bJT){
        butaca.put(key, b);
        butacaJT.put(key, bJT);
    }
    /**
     * Añade el boton y crea la butaca si no existe (butaca libre sin datos, solo con el id)
     * @param key
     * @param bJT 
     */
    public void put(int key, JToggleButton bJT){
        butacaJT.put(key, bJT);
        if(butaca.get(key)==null){
            butaca.put(key, new ButacaSesion(key));
        }
    }
    /*public void put(int key, Butaca b){
        butaca.put(key, b);
    }*/
     public void put(ButacaSesion b){
        butaca.put(b.getIdButaca(), b);
    }
    public boolean hasEstado(int key){
        if(butaca.get(key)==null){
            return false;
        }else{
            return true;
        }
    }
    public HashMap getButacaJT(){
        return butacaJT;
    }
    public HashMap getButaca(){
        return butaca;
    }
}
