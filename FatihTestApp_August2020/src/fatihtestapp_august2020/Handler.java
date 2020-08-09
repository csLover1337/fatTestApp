/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatihtestapp_august2020;

import static fatihtestapp_august2020.Game.HEIGHT;
import static fatihtestapp_august2020.Game.WIDTH;
import static fatihtestapp_august2020.Game.planeCount;
import static fatihtestapp_august2020.Game.planesLeft;
import static fatihtestapp_august2020.Game.points;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author fatih
 */
public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void tick() {
        for( int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
            if (tempObject.getX() > WIDTH || tempObject.getY() < 0 || tempObject.getX() < 0 || tempObject.getY() > HEIGHT) {
                if( tempObject.id == ID.Plane) {
                    planesLeft -= 1;
                }
                removeObject(tempObject);
            }
            if (tempObject.id == ID.Plane) {
                for( int j = i + 1; j < object.size(); j++) {
                    if( object.get(j).id == ID.Missile) {
                        if( Math.abs(object.get(j).getX() - tempObject.getX()) < Plane.PLANE_WIDTH + Missile.MISSILE_WIDTH && Math.abs(object.get(j).getY() - tempObject.getY()) < Plane.PLANE_HEIGHT + Missile.MISSILE_HEIGHT) {
                            points++;
                            removeObject(object.get(j));
                            removeObject(tempObject);
                        }
                    }
                }
            }
            else {
                for( int j = i + 1; j < object.size(); j++) {
                    if( object.get(j).id == ID.Plane) {
                        if( Math.abs(object.get(j).getX() - tempObject.getX()) < Plane.PLANE_WIDTH + Missile.MISSILE_WIDTH && Math.abs(object.get(j).getY() - tempObject.getY()) < Plane.PLANE_HEIGHT + Missile.MISSILE_HEIGHT) {
                            points++;
                            removeObject(object.get(j));
                            removeObject(tempObject);
                        }
                    }
                }
            }
        }
    }
    
    public void render( Graphics g, Game game) {
        for( int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g, game);
        }
    }
    
    public void addObject(GameObject object) {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object) {
        if (object.id == ID.Plane) {
            planeCount--;
        }
        this.object.remove(object);
    }
}
