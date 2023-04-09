package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.util.*;

/**
 * Write a description of class Estanteria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Estanteria
{
    
    private Color color;
    private ArrayList<Caja> cajas;

    /**
     * Constructor for objects of class Estanteria
     */
    public Estanteria(Color pColor)
    {
        color = pColor;
        cajas = new ArrayList<Caja>();
    }
    
    public void anadirCaja(Caja pCaja){
        cajas.add(pCaja);
    }
    
    public void cajaServida(Caja pCaja){
        cajas.remove(pCaja);
    }
    
    public Color getColor(){
        return color;
    }
    
    public ArrayList<Caja> getCajas(){
        return cajas;
    }
    
    public Caja recuperarPrimeraCaja(){
        return cajas.get(0);
    }
}
