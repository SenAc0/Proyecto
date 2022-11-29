package Proyecto;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
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
    public static void drawArrow(final Graphics2D g, final int x1, final int y1, final int x2, final int y2) {
        final int ARR_SIZE = 5;

        // Get the current transform
        AffineTransform saveAT = g.getTransform();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        // Perform transformation
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, (int) len, 0);
        g.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
                new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);

        // Restore original transform
        g.setTransform(saveAT);
    }
    public static void main(String[] args) {
        Ventana v = new Ventana();
    }
    
}

class Panel extends JPanel implements MouseMotionListener, MouseListener, ItemListener, Serializable{
    ArrayList<Ovalo> lista;
    ArrayList<Rectangulo> lista2;
    ArrayList<Linea> lista3;
    ArrayList<Texto> lista4;
    ArrayList<Ovalo> lista5;
    Choice selector;
    
    private Rectangulo RectanguloSeleccionado = null;
    private Rectangulo RectanguloSelec = null;
    private int Rectanguloi;
    private Texto TextoSeleccionado = null;
    


   
    Point u;
    Point w;
    
    public Panel(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        lista3 =  new ArrayList<>();
        lista4 =  new ArrayList<>();
        lista5 =  new ArrayList<>();
        
        this.addMouseListener(this); //Hace posible el dar click
        this.addMouseMotionListener(this); //Hace posible el dar click
        this.setBackground(Color.white);
        
        selector = new Choice();
        selector.add("Seleccionar");
        selector.add("Ovalo");
        selector.add("Rectangulo");
        selector.add("Linea");
        selector.add("Texto");
        selector.add("Mover Rectangulo");
        selector.add("Mover Texto");
        selector.add("Dibujar");
        selector.add("Borrar");
        selector.add("Testeo de Selector");
        selector.add("Testeo de Movedor");
        
        add(selector);
        selector.addItemListener(this);
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
    public ArrayList<Ovalo> getLista5() {
        return lista5;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(selector.getSelectedItem()=="Testeo de Selector"){
            w = new Point(e.getX(), e.getY());
        }
        
        if(selector.getSelectedItem()=="Mover Rectangulo"){
            if(RectanguloSeleccionado != null){
                RectanguloSeleccionado.setXY(e.getX()-50,e.getY()-50);
            }
        }
        if(selector.getSelectedItem()=="Mover Texto"){
            if(TextoSeleccionado != null){
                TextoSeleccionado.setXY(e.getX(),e.getY());
            }
        }
        if(selector.getSelectedItem()=="Dibujar"){
            lista5.add(new Ovalo(e.getX(),e.getY(),2,2));
        }
        if (selector.getSelectedItem()=="Testeo de Movedor"){
            if(RectanguloSelec != null){
                this.getLista2().set(Rectanguloi,new Rectangulo(e.getX(),e.getY(),RectanguloSelec.getNombreClase()));
                int iE = 0;
                for (Linea objLinea : getLista3()) {
                    if(new Rectangle(objLinea.getX1()-50, objLinea.getY1()-50,100 , 100).contains(e.getPoint())){
                        getLista3().set(iE,new Linea(e.getX(),e.getY(),objLinea.getX2(),objLinea.getY2()));
                    }
                    else if(new Rectangle(objLinea.getX2()-50, objLinea.getY2()-50, 100, 100).contains(e.getPoint())){
                        getLista3().set(iE,new Linea(objLinea.getX1(),objLinea.getY1(),e.getX(),e.getY()));
                    }
                    iE++;
                }
            }
        }
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
            if (selector.getSelectedItem()=="Ovalo") {
                getLista().add(new Ovalo(e.getX()-50,e.getY()-50,100,100));
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
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
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
            if(selector.getSelectedItem()=="Mover Rectangulo"){
                for(int i=0 ; i<getLista2().size() ; i++){
                    if(e.getX()>= getLista2().get(i).getX() && e.getY()<=getLista2().get(i).getY()+170 && e.getX()<=getLista2().get(i).getX()+100 && e.getY()>=getLista2().get(i).getY()){
                        RectanguloSeleccionado = getLista2().get(i);
                        System.out.println("seleccionaste un rectangulo");
                    }
                }
            }
            if(selector.getSelectedItem()=="Mover Texto"){
                for(int i=0 ; i<getLista4().size() ; i++){
                    if(e.getX()>= getLista4().get(i).getX() && e.getY()<=getLista4().get(i).getY()+6 && e.getX()<=getLista4().get(i).getX()+7*(getLista4().get(i).getTexto().length()) && e.getY()>=getLista4().get(i).getY()-6){
                        TextoSeleccionado = getLista4().get(i);
                        System.out.println("seleccionaste un Texto");
                    }
                }
            }
            if(selector.getSelectedItem()=="Borrar"){
                for(Rectangulo objRectangulo : getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                        getLista2().remove(objRectangulo);
                    }
                 }
            }
            repaint();
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if(selector.getSelectedItem()=="Testeo de Selector"){
            u = new Point(e.getX(), e.getY());
            w = u;
        }
        if (selector.getSelectedItem()=="Testeo de Movedor") {
            int iN = 0;
            for(Rectangulo objRectangulo : getLista2()){
                if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                    RectanguloSelec = objRectangulo;
                    Rectanguloi=iN;
                    break;
                }
                iN++;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(selector.getSelectedItem()=="Testeo de Selector"){
            u = null;
            w = null;
        }
        if (selector.getSelectedItem()=="Testeo de Movedor"){
            RectanguloSelec = null;
            Rectanguloi = -1;
        }
        repaint();
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
        for (Ovalo objOvalo : getLista5()) {
            objOvalo.paint(g);
        }
        if(selector.getSelectedItem()=="Testeo de Selector"){
           Graphics2D g2 = (Graphics2D) g;
            if (u != null && w != null) {
                g2.setPaint(Color.red);
                Shape r = makeRectangle(u.x, u.y, w.x, w.y);
                g2.draw(r);
            }
        }
        
    }
        private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
            return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}




class Ovalo{
    int x,y,w,h;
    public Ovalo(int x, int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w = w;
        this.h = h;
    }
    public void paint(Graphics g){
        g.fillOval(x, y, w, h);
    }
}
class Rectangulo{
    int x,y;
    String nombreClase = " ";
    public Rectangulo(int x, int y, String nombreClase){
        this.x=x;
        this.y=y;
        if (nombreClase!=null) {
            this.nombreClase=nombreClase;
        }
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getNombreClase() {
        return nombreClase;
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

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
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
        if (s==null) {
            this.Texto = "";
        }else 
         this.Texto = s;
    }
    public void setXY(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getTexto(){
        return Texto;
    }
    public void paint(Graphics g){
        g.drawString(Texto, x, y);
    }
}