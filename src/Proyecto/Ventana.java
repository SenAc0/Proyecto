package Proyecto;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Ventana extends JFrame{
    Panel p1 = new Panel();
    Panel p2 = new Panel();
    PanelDeControl p3 = new PanelDeControl();
    Panel contenedor;
    public Ventana(){
        this.setLayout(new BorderLayout());
        contenedor = p1;
        
        add(contenedor,BorderLayout.CENTER); //se agrega al centro
        add(p3,BorderLayout.NORTH); //se agrega al centro
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//activar cierre en la X 
        this.setSize(1000,800); //se pueden pasar como parámetros del constructor
        this.setVisible(true);  //para que se abra y sea visible
        this.setTitle("Creador de Umls");
    }
    
    
}