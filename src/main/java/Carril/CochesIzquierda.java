package Carril;

public class CochesIzquierda extends Thread {
    Calle carril; 
    Animacion anim;
    
    public CochesIzquierda(Calle p, Animacion a) {
       this.carril = p;
       this.anim = a;
    }
    
    public void run(){
        
        while(true) {
            while(carril.transitoIzq() == true && carril.cochesIzq() > 0){
                carril.quitarCocheIzq();
                System.out.println("Un coche de Izquierda a Derecha transitando - (En la fila): " + carril.cochesIzq());
                //anim.MoverCarro(true);
                try {
                    sleep(2500);
                } catch (InterruptedException e) { } 
            }
            
            if(carril.transitoIzq() == false && carril.cochesIzq() <= 0){
                while(carril.transitoIzq() == false){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) { } 
                }
            }
        }
    }
}
