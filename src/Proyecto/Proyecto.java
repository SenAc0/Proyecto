package Proyecto;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sebaa
 */
public class Proyecto {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Ventana v=new Ventana();
        /*while(1==1){
            System.out.println(v.getP1().getPizarra().getLista().size());
            System.out.println(v.getP3().getP1().getPizarra().getLista().size());
            Thread.sleep(1000);
        }*/
            System.out.println(v.getP3().getP1().getPizarra().getLista().size());
        
            //Thread.sleep(2000);
                
            
            
            
    
}
    }
    



