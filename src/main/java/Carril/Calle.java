package Carril;

import java.util.Random;

public class Calle {

    private boolean cochesIzq = false;
    private boolean cochesDer = false;
    
    private int contIzq = 2;
    private int contDer = 2;
    
    public synchronized int determinarPreferencia() {

        Random r = new Random();
        int valorDado = r.nextInt(2)+1;
        
        notifyAll();
        return valorDado;
   }
    
    
    
    //Añadir coches
    public synchronized void agregarCocheIzq() {
        contIzq++;
        System.out.println("Llega coche a Carril Izquierdo (En la Fila Izquierda): "+ contIzq);
        notifyAll();
   }
    public synchronized void agregarCocheEste() {   
        contDer++;
        System.out.println("Llega coche a Carril Derecho (En la Fila Derecha): "+ contDer);
        notifyAll();
   }
    
    
    
    //Se eliminan coches
    public synchronized void quitarCocheIzq() {
        contIzq--;
        if(contIzq == 0){
            cochesIzq = false;
            cochesDer = true;
        }
        notifyAll();
   }
    public synchronized void quitarCocheDer() {
        contDer--;
        if(contDer == 0){
            cochesDer = false;
            cochesIzq = true;
        }
        notifyAll();
   }
    
    
    
    //Cantidad de coches
    public synchronized boolean transitoIzq() {
        return cochesIzq;
   }
    public synchronized boolean transitoDer() {
        return cochesDer;
   }
    
    
    //Coches Totales
    public synchronized int cochesIzq() {
        return contIzq;
   }
    public synchronized int cochesDer() {
        return contDer;
   }
    
    
    
    //Definir coches
    public synchronized void setCochesIzquierda() {
        cochesIzq = true;
   }
    public synchronized void setCochesDerecha() {
        cochesDer = true;
   }
}
