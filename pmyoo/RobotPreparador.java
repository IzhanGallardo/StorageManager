package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.util.*;
import java.awt.*;

public class RobotPreparador extends RobotSeguidorLinea implements UltrasonicListener{
    private UltrasonicSensor sensorUltrasonico;
    private UltrasonicListener listenerU;
    private Direccion direccion;
    private Tarea tarea;
    private RecogedorCaja recogedorCaja;
    private Caja caja;
    private AlmacenGestor gestor;
    private Caja caja2;

    public RobotPreparador(){
        super();
        sensorUltrasonico = new UltrasonicSensor(SensorPort.S3);
        super.getLegoRobot().addPart(sensorUltrasonico);
        sensorUltrasonico.setBeamAreaColor(Color.GREEN);
        sensorUltrasonico.setDirection(-180);
        sensorUltrasonico.setProximityCircleColor(Color.DARK_GRAY);
        recogedorCaja = new RecogedorCaja(MotorPort.C);
        direccion = Direccion.ESTE;
        tarea = Tarea.EN_CASA;
        sensorUltrasonico.addUltrasonicListener(listenerU);
    }
    
    public void near(SensorPort port, int num){
        if(tarea==Tarea.BUSCANDO_CAJA){
            super.parar();
            setTarea(Tarea.CARGANDO);
        }
    }
    
    public void far(SensorPort port,int num){
        //vacio
    }
    
    public UltrasonicSensor getUltrasonicSensor(){
        return sensorUltrasonico;
    }
    
    public RecogedorCaja getRecogedorCaja(){
        return recogedorCaja;
    }
    
    public Direccion getDireccion(){
        return direccion;
    }
    
    public void setDireccion(Direccion pdireccion){
        direccion = pdireccion;
    }
    
    public Tarea getTarea(){
        return tarea;
    }
    
    public void setTarea(Tarea ptarea){
        tarea = ptarea;
    }
    
    public void darLaVuelta(){
        super.darLaVuelta();
        
        if(direccion==Direccion.ESTE){
            setDireccion(Direccion.OESTE);
        }else if(direccion==Direccion.OESTE){
            setDireccion(Direccion.ESTE);
        }else if(direccion==Direccion.NORTE){
            setDireccion(Direccion.SUR);
        }else if(direccion==Direccion.SUR){
            setDireccion(Direccion.NORTE);
        }
    }
    
    public boolean debeSeguirBorde(Color color){
        if(tarea==Tarea.EN_CASA||tarea==Tarea.CARGANDO){
            return false;
        }else{
            return super.debeSeguirBorde(color);
        }
    }
    
    public void buscarFila(Color color){
         if(tarea!=Tarea.DEJANDO_CAJA){
             setTarea(Tarea.BUSCANDO_FILA);
         } 
         super.buscarFila(color);
    }
    
    public void girar(){
        if(direccion==Direccion.ESTE){
            super.girarI(330);
            setDireccion(Direccion.NORTE);
        }else if(direccion==Direccion.OESTE){
            super.girarD(330);
            setDireccion(Direccion.NORTE);
        }else if(direccion==Direccion.SUR){
            super.adelante();
            super.getMotorD().delay(75);
            super.getMotorI().delay(75);
            super.girarI(330);
            setDireccion(Direccion.ESTE);
        }
    }       
    
    public void buscarCaja(Color color){
        setTarea(Tarea.BUSCANDO_FILA);
        buscarFila(color);
        girar();
        setTarea(Tarea.BUSCANDO_CAJA);
        super.seguirBorde(color);
    }
    
    public void cargarCaja(Color color){
        tarea = Tarea.CARGANDO;
        recogedorCaja.bajar();
        caja2 = gestor.getStock().recuperarPrimeraCaja(color);
        recogedorCaja.recogerCaja(caja2);
    }
    
    public void descargarCaja(){
        recogedorCaja.elevar();
        setTarea(Tarea.LIBRE);
        recogedorCaja.dejarCaja(caja2);
    }
    
    public void dejarCaja(Color color){
        setTarea(Tarea.DEJANDO_CAJA);
        while(super.getColorSensor().getColor()!=Color.BLACK){
            super.seguirBorde(color);
        }
        girar();
        buscarFila(Color.ORANGE);
        descargarCaja();
    }
    
    public void prepararPedido(Color color){
        buscarCaja(color);
        cargarCaja(color);
        dejarCaja(color);
        gestor.cajaServida(caja2,color);
    }
    
    public void irAcasa(){
        if(tarea==Tarea.LIBRE){
            super.irAcasa();
        }
    }
    
    public void pressed(SensorPort port){
        if(tarea==Tarea.LIBRE){
            tarea=Tarea.EN_CASA;
        }
        super.pressed(port);
    }
    
    public void setGestor(AlmacenGestor pGestor){
        gestor = pGestor;
    }
}