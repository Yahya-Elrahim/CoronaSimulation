/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3.model;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 * @author yahya
 */
public class Simulation {
    
    ArrayList<Person> people;
    private Pane world;
    public static int NUMPEOPLE = 100; 
    public Simulation(Pane world){
        this.world = world;
        this.people = new ArrayList<Person>();
        
        for (int i = 0; i < NUMPEOPLE; i++) {
            this.people.add(new Person(State.SUSCEPTIBLE, world));
            
        }
        
        this.people.add(new Person(State.INFFECTED, world));
        draw();
    }
    
    public void addPeople(int numPeople){
        for (int i = 0; i < numPeople; i++) {
            people.add(new Person(State.SUSCEPTIBLE, world));
            
        }
    }
    
    public void move(){
        for (Person p : people) {
            p.move();
        }
    }
    public void draw(){
        for (Person p : people) {
            p.draw();
        }
    }
    
    public void resolveCollion(){
        for (Person p : people) {
            for (Person q : people) {
                if(p != q){
                    p.collisionCheck(q);
                }
            }
        }
    }
    public void feelBetter(){
        for (Person p : people) {
            p.feelBetter();
        }
    }

    public Iterable<Person> getPeople() {
       return this.people;
    }
}
