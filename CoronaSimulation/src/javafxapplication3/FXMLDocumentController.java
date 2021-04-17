/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.net.URL;
import java.util.EnumMap;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafxapplication3.model.Heading;
import javafxapplication3.model.Person;
import javafxapplication3.model.Simulation;
import javafxapplication3.model.State;

/**
 *
 * @author yahya
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    Pane world;
    
    @FXML
    Slider recoverySlider;
    
    @FXML
    Slider sizeSlider;
     
    @FXML
    Slider distanceSlider;
    
    @FXML
    Slider peopleNumSlider;
    
    @FXML
    Label daysNumbers;
    
    @FXML
    Pane histogram;
    
    @FXML
    Pane chart;
    
    @FXML
    Slider simulationSpeed; 
            
     Simulation sim;
    Movement movement;
    
   public class Movement extends AnimationTimer{
       private long FRAMES_PER_SEC = 100L;
       private long INTERVAL = 1000000000 / FRAMES_PER_SEC;
       private long last = 0;
       private int counter = 0, days=0, temp = 24;
       private int ticks = 0;
       
       
        @Override
        public void handle(long now) {
            if(now - last > INTERVAL){
                step();
                last = now;
                ticks ++;
                counter ++;
                if(counter > temp){
                    temp += 24;
                    daysNumbers.setText(String.valueOf(++days));
                }
                
            }
        }

        public int getTicks() {
            return ticks;
        }

        public void setTicks(int ticks) {
            this.ticks = ticks;
        }
        
        
        
    } 
    
    
   
    
   
    
    EnumMap<State, Rectangle> hRectangles = new EnumMap<State, Rectangle>(State.class);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.movement = new Movement();
        
        this.world.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               changeSize();
            }  
        });
        
         recoverySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               setRecovery();
            }  
        });
         
         distanceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               setDistance();
            }  
        });
          
         simulationSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               setSpeed();
            }  
        });
          
         peopleNumSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               addPeople();
            }  
        });
        
    }    
    
    private void changeSize() {
       Person.radius = (int)this.sizeSlider.getValue();
       sim.draw();
    }
    private void setRecovery(){
        Person.healTime = 50 * (int)this.recoverySlider.getValue();
    }
    
    private void setDistance(){
        Person.distance = (int)this.distanceSlider.getValue();
    }
    private void setSpeed(){
        Heading.SPEED = (int)simulationSpeed.getValue();
    }
    
     private void addPeople(){
        Simulation.NUMPEOPLE = (int)peopleNumSlider.getValue();
    }
    
    @FXML
    public void step(){
        sim.move();
        sim.feelBetter();
        sim.resolveCollion();
        sim.draw();
        drawsCharts();
    }
    
    @FXML
    public void start(){
        this.sim = new Simulation(world);
        this.movement.start();
        
        int offest = 0;
        for(State s : State.values()){
            Rectangle r = new Rectangle(60, 0, s.getColor());
            r.setTranslateX(offest);
            offest += 60;
            hRectangles.put(s, r);
            histogram.getChildren().add(r);
        }
        
        step();
    }
    
     @FXML
    public void stop(){
        this.movement.stop();
    }
    
    @FXML
    public void reset(){
        world.getChildren().clear();
        this.sim = new Simulation(world);
        this.histogram.getChildren().clear();
        this.chart.getChildren().clear();
   
    }
    
    private void drawsCharts(){
        EnumMap<State, Integer> pos = new EnumMap<State, Integer>(State.class);
        
        for (Person p : sim.getPeople()) {
            if(!pos.containsKey(p.getState())){
                pos.put(p.getState(), 0);
            }
            pos.put(p.getState(), 1 + pos.get(p.getState()));
        }
        
        for (State s : hRectangles.keySet()) {
            if(pos.containsKey(s)){
                hRectangles.get(s).setHeight(pos.get(s));
                hRectangles.get(s).setTranslateY(30 + 100 - pos.get(s));
                
                Circle c = new Circle(1, s.getColor());
                c.setTranslateX(movement.getTicks()/ 5.0);
                c.setTranslateY(130- pos.get(s));
                chart.getChildren().add(c);
            }
        }
    }
}
