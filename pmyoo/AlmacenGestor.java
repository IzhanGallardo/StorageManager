package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;
import java.util.*;


public class AlmacenGestor
{
    private ArrayList<Pedido> pedidos;
    private RobotPreparador robot;
    private Stock stock;
    private Pedido pedido;
    public AlmacenGestor(Stock pStock, RobotPreparador pRobot)
    {
        robot=pRobot;
        stock = pStock;
        pedidos = new ArrayList<Pedido>();
    }
    
    public Stock getStock(){
        return stock;
    }
    
    public void recibePedido(Color pColor){
        Pedido pedido = new Pedido(pColor);
        pedidos.add(pedido);
    }
    
    public void despacharPedidos(){
        for(int i=0; i<pedidos.size();i++){
            robot.prepararPedido(pedidos.get(i).getColor());
            pedidos.remove(pedidos.get(i));
        }
    }
    
    public Caja recuperarPrimeraCaja(Color pColor){
        return stock.recuperarPrimeraCaja(pColor);
    }
    
    public void cajaServida(Caja pCaja, Color pColor){
        stock.cajaServida(pColor, pCaja);
        pCaja.removeSelf();
        if (pedidos.size()==0){
            robot.irAcasa();
        }
    }
}
