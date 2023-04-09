package pmyoo;


/**
 * Enumeration class ColorFila - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum ColorFila
{
     ROJO(245, 255, 0, 15, 0, 15), VERDE(0, 40, 240, 255, 0, 15), AMARILLO(240, 255, 240, 255, 0, 15), AZUL(0, 15, 0, 15, 240, 255);
     private int Rmin, Rmax, Gmin, Gmax, Bmin, Bmax;
     ColorFila(int pRmin,int pRmax,int pGmin,int pGmax,int pBmin,int pBmax){
         Rmin=pRmin;
         Rmax=pRmax;
         Gmin=pGmin;
         Gmax=pGmax;
         Bmin=pBmin;
         Bmax=pBmax;
     }
}
