/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuensalida.printer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


class Ticket implements Printable {

    public int print(Graphics g, PageFormat f, int pageIndex) {
        if (pageIndex == 0) {
            g.drawString("Fuensalida Casa de Cultura", 0, 10);
            g.drawLine(0, 20, 300, 20);
            
            try {
                //Para poder pintar el escudo y las imagenes que necesitemos.
                g.drawImage(ImageIO.read(new File("Recursos/Imagenes/ima.jpg")), 30, 0, null);
            } catch (IOException ex) {
                Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }
}
