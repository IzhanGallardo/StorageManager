package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.util.*;

public class Pedido
{
    private int id;
    private Color color;
    private int numero=0;
    
    public Pedido(Color pColor)
    {
        color = pColor;
        numero++;
        id = 13*numero;
    }

    public Color getColor(){
        return color;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public int getId(){
        return id;
    }
}
