
package Proyecto;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Ventana extends JFrame{
    public Ventana(){
        this.setLayout(new BorderLayout());
        Panel p = new Panel();
        add(p,BorderLayout.CENTER); //se agrega al centro
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//activar cierre en la X 
        this.setSize(1000,800); //se pueden pasar como parámetros del constructor
        this.setVisible(true);  //para que se abra y sea visible
        this.setTitle("Esta es una prueba inicial");
    }
}