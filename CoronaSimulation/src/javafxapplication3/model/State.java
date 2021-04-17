/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3.model;

import javafx.scene.paint.Color;


/**
 *
 * @author yahya
 */
public enum State {
    
    SUSCEPTIBLE{
        public Color getColor(){
            return Color.BLUE;
        }
    }, 
    
    INFFECTED{
        public Color getColor(){
            return Color.RED;
        }
    }, 
    
    RECOVERED{
        public Color getColor(){
            return Color.GREEN;
        }
    };
    public abstract Color getColor();
}
