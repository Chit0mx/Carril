package Carril;

import static java.lang.Thread.sleep;

public class Carril {
    public static void main(String[] args) {

        //Coches totales y transito
        Calle carril   = new Calle();
        
        Animacion anim = new Animacion(carril);
        
        //Generar coches y determinar preferencia
        Coches transito = new Coches(carril);
        CochesIzquierda izq = new CochesIzquierda(carril,anim);
        CochesDerecha der = new CochesDerecha(carril,anim);
        
        transito.start();
        
        try {
            sleep(500);
        } catch (InterruptedException e) { };
        izq.start();
        der.start();
    }
}
