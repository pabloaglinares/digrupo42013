/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author Gemo
 */
public class ImagenFondo implements Border{
    
    public BufferedImage back;

    public ImagenFondo() {
        try {
            URL imagePath = new URL(getClass().getResource("/resources/fondo.jpg").toString());
            back = ImageIO.read(imagePath);
        } catch (IOException ex) {
            
        }
    }
    
    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //He modificado esto para que al maximizar la aplicación se ajuste el fondo al tamaño de la ventana
//        int x0 = x+ (width-back.getWidth())/2;
//        int y0 = y+ (height-back.getHeight())/2;
//        g.drawImage(back,x0,y0,null);
        g.drawImage(back,0,0,c.getWidth(),c.getHeight(),null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
    
    
}
