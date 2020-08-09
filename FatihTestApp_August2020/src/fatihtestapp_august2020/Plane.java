/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import static fatihtestapp_august2020.Game.HEIGHT;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author fatih
 */
public class Plane extends GameObject {
    
    public static final int PLANE_WIDTH = 50;
    public static final int PLANE_HEIGHT = 22;
    public static final int PLANE_BASE_VELX = 2;
    public static final int PLANE_RAND_VELX = 5;
    public static final int PLANE_BASE_Y = HEIGHT / 10;
    public static final int PLANE_RAND_Y = HEIGHT / 2;
    
    
    public Plane( ID id) {
        super(id);
        //System.out.println("A new plane!");
        Random rand = new Random();
        int randInt1 = rand.nextInt(PLANE_RAND_Y);
        int randInt2 = rand.nextInt(PLANE_RAND_VELX);
        setX(0);
        setVelY(0);
        setY(PLANE_BASE_Y + randInt1);
        setVelX(PLANE_BASE_VELX + randInt2);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g, Game game) {
        BufferedImage planeImage = null;
        try {
            planeImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fatihtestapp_august2020\\plane2dResized50.png"));
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        g.drawImage(planeImage, x, y, game);
    }
    
   
}
