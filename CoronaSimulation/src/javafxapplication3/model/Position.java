/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3.model;

import javafx.scene.layout.Pane;

/**
 *
 * @author yahya
 */
public class Position {
    
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Position(Pane world, int radius){
        this(radius + Math.random()*(world.getWidth() - 2 - radius), 
                radius + Math.random()*(world.getHeight() - 2 - radius));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double distance(Position other){
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    void move(Heading heading, Pane world, Position origin) {
        this.x += heading.getDx();
        this.y += heading.getDy();
        if(x < Person.radius || x > world.getWidth()- Person.radius || distance(origin) > Person.distance){
            heading.bounceX();
            x += heading.getDx();
        }
        
        if(y < Person.radius || y > world.getHeight() - Person.radius || distance(origin) > Person.distance){
            heading.boinceY();
            y += heading.getDy();
        }
    }
    
    public boolean collision(Position other){
        return distance(other) < 2 * Person.radius;
    }

 
    
}
