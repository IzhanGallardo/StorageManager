package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.util.*;
import java.awt.*;

public class RobotSeguidorLinea implements TouchListener
{
    private LegoRobot robot;
    private ColorSensor sensorColor;
    private TouchSensor sensorTouch;
    private Motor motorD, motorI;
    private TouchListener listener;
    
    public RobotSeguidorLinea(){
        robot = new LegoRobot();
        sensorColor = new ColorSensor(SensorPort.S1);
        sensorTouch = new TouchSensor(SensorPort.S4);
        motorD = new Motor(MotorPort.A);
        motorI = new Motor(MotorPort.B);
        robot.addPart(sensorColor);
        robot.addPart(sensorTouch);
        robot.addPart(motorI);
        robot.addPart(motorD);
        sensorTouch.addTouchListener(listener);
    }

    public void pressed(SensorPort port){
        parar();
        atras();     
    }
    
    public void released(SensorPort port){
        parar();
        darLaVuelta();
        adelante();
        motorI.delay(50);
        motorD.delay(50);
        parar();
    }
    
    public LegoRobot getLegoRobot(){
        return robot;
    }
    
    public Motor getMotorD(){
        return motorD;
    }
    
    public Motor getMotorI(){
        return motorI;
    }
    
    public ColorSensor getColorSensor(){
        return sensorColor;
    }
    
    public TouchSensor getTouchSensor(){
        return sensorTouch;
    }
    
    public void setVelocidad(int velocidad){
        motorI.setSpeed(velocidad);
        motorD.setSpeed(velocidad);
    }
    
    public void adelante(){
        motorI.forward();
        motorD.forward();
    }
    
    public void atras(){
        motorI.backward();
        motorD.backward();    
    }
    
    public void parar(){
        motorI.stop();
        motorD.stop();    
    }
    
    public void darLaVuelta(){
        motorI.forward();
        motorD.backward();
        motorI.delay(660);
        motorD.delay(660);
        motorD.stop();
        motorI.stop();
    }
    
    public void girarI(int tiempo){
        motorI.forward();
        motorD.backward();
        motorI.delay(tiempo);
        motorD.delay(tiempo);
        motorD.stop();
        motorI.stop();
    }
    
    public void girarD(int tiempo){
        motorI.backward();
        motorD.forward();
        motorI.delay(tiempo);
        motorD.delay(tiempo);
        motorD.stop();
        motorI.stop();
    }
    
    public boolean debeSeguirBorde(Color color){
        if((sensorColor.getColorLabel().equals(ColorLabel.WHITE))||(sensorColor.getColorLabel().equals(color))){
            return true;
        }else{
            return false;
        }
    }
    
    public void seguirBorde(Color color){
        if(debeSeguirBorde(Color.WHITE)){
            girarI(75);
        }else if((sensorColor.getColorLabel().equals(color))){
            girarD(75);
        }
    }
    
    public void buscarFila(Color color){
        adelante();
        if(sensorColor.getColorLabel().equals(color)){
            parar();
        }
    }
    
    public void irAcasa(){
        adelante();
    }
    
    public void act(){
        irAcasa();
    }
    
}    
