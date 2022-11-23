package Proyecto;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sebaa
 */
public class Proyecto {

    public static void main(String[] args) {
        Ventana v = new Ventana();
    }
    
}

class Panel extends JPanel implements MouseMotionListener, MouseListener, ItemListener{
    ArrayList<Ovalo> lista;
    ArrayList<Rectangulo> lista2;
    Choice selector;


    Label lb = new Label("String");
    
    BotonSO s1;
    BotonSR s2;

    
    public Panel(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        add(lb);
        this.addMouseListener(this); //Hace posible el dar click
        this.addMouseMotionListener(this); //Hace posible el dar click
        s1 = new BotonSO();
        s2 = new BotonSR();
        this.add(s1);
        this.add(s2);
        this.setBackground(Color.white);
        selector = new Choice();
        selector.add("Seleccionar");
        selector.add("Ovalo");
        selector.add("Rectangulo");
        selector.add("Label");
        add(selector);
        selector.addItemListener(this);
    }
    
    
    
    
    public ArrayList<Ovalo> getLista() {
        return lista;
    }
    public ArrayList<Rectangulo> getLista2() {
        return lista2;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK){
            lb.setLocation(e.getPoint());
            lb.repaint();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
            if (selector.getSelectedItem()=="Ovalo") {
                getLista().add(new Ovalo(e.getX(),e.getY()));
            }
            if (selector.getSelectedItem()=="Rectangulo") {
                getLista2().add(new Rectangulo(e.getX(),e.getY()));
            }
            if (selector.getSelectedItem()=="Label") {
               //Aqui deberia ponerse una Label, para colocar
            }
            repaint();
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Rectangulo objRectangulo : getLista2()){
            objRectangulo.paint(g);
        }
        for (Ovalo objOvalo : getLista( )) {
            objOvalo.paint(g);
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {

    }


    
    private class BotonSO extends JButton implements ActionListener{
        BotonSO(){
            super("Ovalo");
            this.addActionListener(this);
            
        }
        public void actionPerformed(ActionEvent e){
        }
    
    }
    private class BotonSR extends JButton implements ActionListener{
        BotonSR(){
            super("Rectangulo");
            this.addActionListener(this);
            
        }
        public void actionPerformed(ActionEvent e){
        }
    }

}




class Ovalo{
    int x,y;
    public Ovalo(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void paint(Graphics g){
        g.fillOval(x, y, 100, 100);
    }
}
class Rectangulo{
    int x,y;
    public Rectangulo(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void paint(Graphics g){
        g.fillRect(x, y, 100, 100);
    }
}