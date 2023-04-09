package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.util.*;

public class Stock
{
    private Estanteria[] estanteria;
    private Color[] colores;
    public Stock(Color[] pColores)
    {
        colores = pColores;
        estanteria = new Estanteria[colores.length];
        for(int i=0; i<estanteria.length; i++){
            estanteria[i] = new Estanteria(colores[i]);
        }
    }
    
    public Estanteria[] getEstanterias(){
        return estanteria;
    }
    
    public Color[] getColores(){
        return colores;
    }
    
    public int getNumEstanterias(){
        return estanteria.length;
    }
    
    public int buscarPosicion(Color color){
        boolean encontrado=false;
        int i=0;
        while(!encontrado&&i<colores.length){
            if(colores[i]==color){
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void anadirCaja(Color pColor, Caja pCaja){
        estanteria[buscarPosicion(pColor)].anadirCaja(pCaja);
    }
    
    public Caja recuperarPrimeraCaja(Color pColor){
        return estanteria[buscarPosicion(pColor)].recuperarPrimeraCaja();
    }
    
    public void cajaServida(Color pColor,Caja pCaja){
        estanteria[buscarPosicion(pColor)].cajaServida(pCaja);
    }
}
