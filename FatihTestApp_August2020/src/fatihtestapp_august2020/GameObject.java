/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import java.awt.Graphics;

/**
 *
 * @author fatih
 */
public abstract class GameObject {
    
    protected int x;
    protected int y;
    protected ID id;
    protected int velX;
    protected int velY;

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    
    public GameObject(ID id) {
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g, Game game);
    
}
