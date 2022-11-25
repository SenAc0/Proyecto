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
import javax.swing.SwingUtilities;

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
    ArrayList<Texto> lista4;
    Choice selector;
    private Point startPoint;

    Label lb = new Label("String");
    
    JEditorPane editor;
   
    Point u;
    Point w;
    
    public Panel(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        lista3 =  new ArrayList<>();
        lista4 =  new ArrayList<>();
        
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
        selector.add("Texto");
        selector.add("Mover Ultimo Rectangulo");
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
    public Rectangulo getRect(){
        return lista2.get(0);
    }
    public ArrayList<Linea> getLista3() {
        return lista3;
    }
    public ArrayList<Texto> getLista4() {
        return lista4;
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
                getLista().add(new Ovalo(e.getX()-50,e.getY()-50));
            }
            if (selector.getSelectedItem()=="Rectangulo") {
                String nombreClase = JOptionPane.showInputDialog("Indique el nombre de la Clase:");
                getLista2().add(new Rectangulo(e.getX()-50,e.getY()-50, nombreClase));
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
            if(selector.getSelectedItem()=="Texto"){
                String Txt = JOptionPane.showInputDialog("Indique el texto que desea escribir:");
                getLista4().add(new Texto(e.getX(),e.getY(), Txt));
            }
            if(selector.getSelectedItem()=="Mover Ultimo Rectangulo"){
                if(getLista2().size()>0){
                    getLista2().get(getLista2().size()-1).setXY(e.getX(), e.getY());
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
        for(Texto Txt : getLista4()){
            Txt.paint(g);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
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
    String nombreClase = " ";
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
    public void setXY(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 15);
        g.drawRect(x, y, 100, 85);
        g.drawRect(x, y, 100, 170);
        g.setColor(Color.red);
        g.drawString(nombreClase,x+40,y+12);
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

class Texto{
    private int x;
    private int y;
    private String Texto;
    public Texto(int x,int y,String s){
        this.x = x;
        this.y = y;
        this.Texto = s;
    }
    public void paint(Graphics g){
        g.drawString(Texto, x, y);
    }
}