package Proyecto;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Ventana extends JFrame{
    Ventana v;
    Panel p1;
    Panel p2;
    public PanelDeControl p3;
    JPanel contenedor = new JPanel(new BorderLayout());
    public Ventana(){
        v=this;
        this.setLayout(new BorderLayout());
        
        p1 = new Panel();
        p2 = new Panel();
        p3 = new PanelDeControl(contenedor,p1,p2,v);
        
        contenedor.add(p1,BorderLayout.CENTER);
        
        add(contenedor,BorderLayout.CENTER); //se agrega al centro
        add(p3,BorderLayout.NORTH); //se agrega al centro
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//activar cierre en la X 
        this.setSize(1000,800); //se pueden pasar como parámetros del constructor
        this.setVisible(true);  //para que se abra y sea visible
        this.setTitle("Creador de Umls");
    }
    public Panel getP1(){
        return this.p1;
    }
    public Panel getP2(){
        return this.p2;
    }
    public PanelDeControl getP3(){
        return this.p3;
    }
    public Pizarra getPizarra1(){
        return p3.getPizarra1();
    }
    public Pizarra getPizarra2(){
        return p3.getPizarra2();
    }
    
    public void setPanelPizarra1(Pizarra nuevaPizarra){
        this.p1.setPizarra(nuevaPizarra);
    }
    public void setPanelPizarra2(Pizarra nuevaPizarra){
        this.p2.setPizarra(nuevaPizarra);
    }
    
    
}
