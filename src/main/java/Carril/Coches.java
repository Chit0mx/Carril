package Carril;

import java.util.Random;

public class Coches extends Thread{
    Calle carril; 
    
    public Coches(Calle p) {
       this.carril = p;
    }

    public void run(){
        
        while(true) {
            Random r = new Random();
            int tiempo = r.nextInt(4200-500)+500;
            //System.out.println(tiempo);
            int valor = carril.determinarPreferencia();
            if(valor == 1){
                if(carril.transitoDer() == false){
                    carril.setCochesIzquierda();
                }
                //carril.agregarCocheOeste();
            }else if(valor == 2){
                if(carril.transitoIzq()== false){
                    carril.setCochesDerecha();
                }
                //carril.agregarCocheEste();
            }
            try {
                sleep(tiempo);
            } catch (InterruptedException e) { }
        }
    }
}

