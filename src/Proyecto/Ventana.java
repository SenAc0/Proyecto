package Proyecto;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Ventana extends JFrame{
    Panel p1;
    Panel p2 ;
    PanelDeControl p3;
    JPanel contenedor = new JPanel(new BorderLayout());
    public Ventana(){
        this.setLayout(new BorderLayout());
        
        p1 = new Panel();
        p2 = new Panel();
        p3 = new PanelDeControl(contenedor,p1,p2);
        contenedor.add(p1,BorderLayout.CENTER);
        add(contenedor,BorderLayout.CENTER); //se agrega al centro
        add(p3,BorderLayout.NORTH); //se agrega al centro
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//activar cierre en la X 
        this.setSize(1000,800); //se pueden pasar como parámetros del constructor
        this.setVisible(true);  //para que se abra y sea visible
        this.setTitle("Creador de Umls");
    }
    
    
}
