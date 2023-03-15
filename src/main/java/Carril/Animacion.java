package Carril;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Animacion  extends JFrame implements Runnable{

    private BufferedImage buffer, animacion,temp;
    private Thread hilo;
    int h,w,origenCarroX,origenCarroY,xe,xo,y;
    Graphics g=this.getGraphics();
    Calle carril;

    
    public Animacion(Calle c){
        this.carril=c;
        h=500;
        w=(int)(h*2);
        origenCarroX=100;
        origenCarroY=375;
        xo=100;
        xe=w-175;

        
        setTitle("Carril AAMR 19310170");
        setSize(w,h+100);
        setLayout(null);
        
        JButton btnIzq=new JButton("+1 Izquierda");
        btnIzq.setBounds(50,h,200,50);
        add(btnIzq);
        
        JButton btnDer=new JButton("+1 Derecha");
        btnDer.setBounds(w-250,h,200,50);
        add(btnDer);
        
        btnIzq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.agregarCocheIzq();
            }
        });
        
        btnDer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.agregarCocheEste();
            }
        });
        hilo=new Thread(this);
        hilo.start();
        buffer=new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        g=this.getGraphics();
        
    }

    public void paint(Graphics g){
        y=375;
        super.paint(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, w, 300);
        g.setColor(Color.green);
        g.fillRect(280,0,440,270);
        
        g.setColor (Color.gray);
        g.drawLine (0, 400, w, 400);
        g.fillRect(0, 300, w, 200);
        
        g.fillRect(50, 0, 200, 400);
        g.fillRect(w-250, 0, 200, 400);

        g.setColor (Color.yellow);
        int rallaX = 120;
        g.fillRect(rallaX,375,100,35);
        g.fillRect(rallaX+210,375,100,35);
        g.fillRect(rallaX+420,375,100,35);
        g.fillRect(rallaX+630,375,100,35);

        FilaN(carril.cochesIzq(),carril.cochesDer());

        if(carril.transitoDer()&&carril.cochesDer()>0){
            Carro(g,xe,y,Color.black);
            xe-=50;
            if(xe<=-75){
                if(carril.cochesDer()==0){
                    carril.setCochesIzquierda();
                }
                xe=w-175;
            }
        }
        
        if(carril.transitoIzq()&&carril.cochesIzq()>0){
            Carro(g,xo,y,Color.white);
            xo+=50;
            if(xo>=w){
                if(carril.cochesIzq()==0){
                    carril.setCochesDerecha();
                }
                xo=100;
            }
        }
        
        //System.out.println("Este->"+carril.transitoEste());
        //System.out.println("Oeste->"+carril.transitoOeste());
    }
    
    public void Carro(Graphics g, int x, int y, Color co){
        //Llantas
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 35, 35);
        g.fillOval(x+50, y, 35, 35);
        //Cuerpo
        g.setColor(co);
        g.fillRect(x-25, y-25, 125, 35);
        //Parabrisas
        g.setColor(Color.cyan);
        g.fillRect(x,y-60,80,35);
        g.setColor(co);
        g.drawRect(x,y-60,80,35);

    }
    
    /*Lado
        true - izq
        false - der
    */
    public void MoverCarro(Boolean lado){
        int x=origenCarroX;
        int y=origenCarroY;
        if(lado){
            while (x<=w){
                //g.clearRect(0, 0, w, h);
                repaint();
                Carro(g,x,y,Color.white);
                x+=10;
                try {
                   sleep(10);
                } catch (InterruptedException e) { } 
            }
        }else{
            x=w-175;
            while (x>=-75){
                //g.clearRect(0, 0, w, h);
                repaint();
                Carro(g,x,y,Color.black);
                x-=10;
                try {
                   sleep(10);
                } catch (InterruptedException e) { } 
            }
        }
    }
    
    public void FilaN(int nD, int nI){
        int y=origenCarroY-100;
        for(int i=0;i<nD;i++){
            Carro(g,origenCarroX,y,Color.white);
            y-=100;
        }
        y=origenCarroY-100;
        int x=w-175;
        for(int i=0;i<nI;i++){
            Carro(g,x,y,Color.black);
            y-=100;
        }
    }
    
    @Override
    public void run(){
        while(true){
            try{
                repaint();
                hilo.sleep(100);
            }catch(InterruptedException ex){
            }
        }
    }
     
     
}
