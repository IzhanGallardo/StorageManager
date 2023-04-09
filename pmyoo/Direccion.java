package pmyoo;

public enum Direccion
{
    ESTE(0),
    SUR(90),
    OESTE(180),
    NORTE(270);
    
    private int direccion;
    
    Direccion(int pDireccion){
        direccion = pDireccion;
    }
    
    public int getValor(){
        return direccion;
    }
}
