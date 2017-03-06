/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.beans;

/**
 *
 * @author vPalomo
 */
public class ButacaSesion {
    private int idButaca;
    private int idActividad;
    private int idSesion;
    private int idEstado;
    private String motivo;
    private String fechaMotivo;

    public ButacaSesion(int id){
        idButaca=id;
    }
    public ButacaSesion(){}
    
    public String toString(){
        return getIdButaca()+" "+getIdEstado();
    }
    
    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaMotivo() {
        return fechaMotivo;
    }

    public void setFechaMotivo(String fechaMotivo) {
        this.fechaMotivo = fechaMotivo;
    }
    
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    
}
