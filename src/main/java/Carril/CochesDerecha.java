package Carril;

public class CochesDerecha extends Thread{
    Calle carril; 
    Animacion anim;

    public CochesDerecha(Calle p, Animacion a) {
        this.carril = p;
        this.anim = a;
    }
    
    public void run(){
        
        while(true) {
            while(carril.transitoDer() == true && carril.cochesDer() > 0){
                carril.quitarCocheDer();
                System.out.println("Un coche de Derecha a Izquierda transitando - (En la fila): " + carril.cochesDer());
                //anim.MoverCarro(false);
                try {
                    sleep(2500);
                } catch (InterruptedException e) { } 
            }
            
            if(carril.transitoDer() == false && carril.cochesDer() <= 0){
                while(carril.transitoDer() == false){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) { } 
                }
            }
        }
    }
}
