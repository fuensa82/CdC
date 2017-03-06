/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.printer;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author vPalomo
 */
public class Imprimir {
    static public void main(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Ticket());
        if(job.printDialog()){
            try{
                job.print();
            }catch(PrinterException e){
                System.out.println(e);
            }
        }
    }

}
