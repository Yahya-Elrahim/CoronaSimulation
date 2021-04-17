/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3.model;

/**
 *
 * @author yahya
 */
public class Heading {
    
    public static int SPEED = 2;
    
    private double dx;
    private double dy;

    public Heading(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Heading() {
        double dir = Math.random() * 2 * Math.PI;
        dx = Math.sin(dir);
        dy = Math.cos(dir);
    }

    public double getDx() {
        return dx * SPEED;
    }

    public double getDy() {
        return dy * SPEED;
    }

    public void bounceX(){
        this.dx *= -1;
    }
    public void boinceY(){
        this.dy *=-1;
    }

    
    
    
}
