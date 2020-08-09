/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import static fatihtestapp_august2020.Game.HEIGHT;
import static fatihtestapp_august2020.Game.WIDTH;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author fatih
 */
public class Missile extends GameObject {
    
    public static final int MISSILE_X = WIDTH / 2;
    public static final int MISSILE_Y = 9 * (HEIGHT / 10);
    public static final int MISSILE_VEL = 10;
    public static final int MISSILE_WIDTH = 5;
    public static final int MISSILE_HEIGHT = 5;

    public Missile( int clickedX, int clickedY, ID id) {
        super( id);
        
        double xDis = clickedX - MISSILE_X;
        double yDis = MISSILE_Y - clickedY;
        double totalDis = Math.sqrt((xDis * xDis) + (yDis * yDis));
        double velAdjuster = MISSILE_VEL / totalDis;
        //System.out.println("Missile created " + xDis + " " + yDis + " " + velAdjuster);
        setVelX((int) (xDis * velAdjuster));
        setVelY((int) (yDis * velAdjuster * -1));
        
        setX( MISSILE_X);
        setY( MISSILE_Y);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g, Game game) {
        g.setColor(Color.black);
        g.fillOval( x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }
    
}
