/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author fatih
 */
public class Window extends Canvas {
    
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        /*
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fatihtestapp_august2020\\backgroundImageTest1.jpg"));
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new ImagePanel(backgroundImage));
        /*
        JLabel backgroundImage = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\src\\fatihtestapp_august2020\\backgroundImageTest1.jpg"));
        frame.add(backgroundImage);
*/
        
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
