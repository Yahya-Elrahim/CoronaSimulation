/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author yahya
 */
public class Person {
    
    public static int radius = 5;
    public static int healTime = 5 * 50;
    public static int distance = 200;
    
    
    private State state;
    private Position loc;
    private Position origin;
    private Heading heading;
    private Circle circle;
    private Pane world;
    private int sickTime = 0;

    public Person(State state, Pane world) {
        this.state = state;
        this.world = world;
        this.heading = new Heading();
        this.loc = new Position(world, radius);
        this.origin = new Position(loc.getX(), loc.getY());
        this.circle = new Circle(radius, state.getColor());
        this.circle.setStroke(Color.BLACK);
        world.getChildren().add(circle);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        this.circle.setFill(state.getColor());
    }
    
    public void move(){
        loc.move(this.heading, world, origin);
    }
    
    public void draw(){
        this.circle.setRadius(radius);
        this.circle.setTranslateX(this.loc.getX());
        this.circle.setTranslateY(this.loc.getY());
    }
    
    public void collisionCheck(Person other){
        if(loc.collision(other.loc)){
            if(other.getState() == State.INFFECTED && state == State.SUSCEPTIBLE){
                setState(State.INFFECTED);
            }
        }
    }
    
    public void feelBetter(){
        if(this.state == State.INFFECTED){
            sickTime ++;
            if(sickTime > healTime){
                setState(State.RECOVERED);
            }
        }
    }
    
    
}
