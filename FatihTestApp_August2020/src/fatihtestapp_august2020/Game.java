/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import static fatihtestapp_august2020.Plane.PLANE_RAND_Y;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author fatih
 */
public class Game extends Canvas implements Runnable, MouseListener {
    
    public static final int WIDTH = 749;
    public static final int HEIGHT = 534;
    public static final int TICKS = 25;
    public static final int PLANE_LIMIT = 10;
    public static final int PLANE_SPAWN_RAND = 50;
    public static final int PLANE_SPAWN_BASE = 20;
    
    public static int planeCount;
    public static int ticksUntilPlane;
    public static int points;
    public static int planesLeft;
    
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    
    public Game() {
        new Window (WIDTH, HEIGHT, "Plane Destruction!", this);
        
        planeCount = 0;
        points = 0;
        planesLeft = 3;
        Random rand = new Random();
        ticksUntilPlane = PLANE_SPAWN_BASE + rand.nextInt(PLANE_SPAWN_RAND);
        handler = new Handler();
        addMouseListener(this);
    }
    
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = TICKS;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while( delta >= 1) {
                tick();
                delta--;
            }
            if( running) {
                render();
            }
            frames++;
            
            if( System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames + " FPS");
                frames = 0;
            }
            
            if (planesLeft < 1) {
                running = false;
                JOptionPane.showMessageDialog(null, "You finished with " + points + " points", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        stop();
    }
    
    public void mousePressed(MouseEvent e) {
       
    }
    
    private void tick() {
        //System.out.println("a new tick!");
        ticksUntilPlane--;
        if (ticksUntilPlane < 1 && planeCount < PLANE_LIMIT) {
            handler.addObject( new Plane( ID.Plane));
            Random rand = new Random();
            ticksUntilPlane = PLANE_SPAWN_BASE + rand.nextInt(PLANE_SPAWN_RAND);
            planeCount += 1;
        }        
        
        handler.tick();
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fatihtestapp_august2020\\backgroundImageTest1.jpg"));
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
        g.drawImage(backgroundImage, 0, 0, this);
        
        handler.render(g, this);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String args[]) {
        new Game();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
        handler.addObject(new Missile( e.getX(), e.getY(), ID.Missile));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
