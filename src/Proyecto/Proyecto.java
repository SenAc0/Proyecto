package Proyecto;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
    ArrayList<Linea> lista3;
    Choice selector;


    Label lb = new Label("String");
    
    JEditorPane editor;
   
    Point u;
    Point w;

    
    public Panel(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        lista3 =  new ArrayList<>();
        
        add(lb);
        this.addMouseListener(this); //Hace posible el dar click
        this.addMouseMotionListener(this); //Hace posible el dar click
        this.setBackground(Color.white);
        selector = new Choice();
        selector.add("Seleccionar");
        selector.add("Ovalo");
        selector.add("Rectangulo");
        selector.add("Label");
        selector.add("Linea");
        add(selector);
        selector.addItemListener(this);
        JTextField editor = new JTextField();
        editor.setText("ola");
        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setBounds(50,50,300,300);
        add(scrollPane);
        u = null;
        w = null;
    }
    
    
    
    
    public ArrayList<Ovalo> getLista() {
        return lista;
    }
    public ArrayList<Rectangulo> getLista2() {
        return lista2;
    }
    public ArrayList<Linea> getLista3() {
        return lista3;
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
                String nombreClase = JOptionPane.showInputDialog("Class");
                getLista2().add(new Rectangulo(e.getX(),e.getY(), nombreClase));
            }
            if (selector.getSelectedItem()=="Label") {
               //Aqui deberia ponerse una Label, para colocar
            }
            if(selector.getSelectedItem()=="Linea"){
                 for(Rectangulo objRectangulo : getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),100,100).contains(e.getPoint())){
                         if (u==null) {
                            u = new Point(e.getX(),e.getY());
                        }else{
                            w = new Point(e.getX(),e.getY());
                            getLista3().add(new Linea( u.x, u.y ,w.x ,w.y ));
                            repaint();
                            u = null;
                            w = null;
                        }
                    
                    }
                 }
                
                   
                
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
        for (Ovalo objOvalo : getLista()) {
            objOvalo.paint(g);
        }
        for (Linea objLinea : getLista3()) {
            g.setColor(Color.black);
            objLinea.paint(g);
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
    String nombreClase;
    public Rectangulo(int x, int y, String nombreClase){
        this.x=x;
        this.y=y;
        this.nombreClase=nombreClase;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void paint(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(x, y, 100, 100);
        g.setColor(Color.red);
        g.drawString(nombreClase,x+50,y+10);
    }
}
class PanelDeControl extends JPanel{
    public PanelDeControl(){
        
        this.setBackground(Color.blue);

    }
}
class Linea{
    int x1;
    int y1;
    int x2;
    int y2;
    public Linea(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    public void paint(Graphics g){
        g.drawLine(x1, y1, x2, y2);
    }
}