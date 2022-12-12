package Proyecto;

import java.awt.Choice;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.cyan;
import static java.awt.Color.gray;
import static java.awt.Color.green;
import static java.awt.Color.magenta;
import static java.awt.Color.pink;
import static java.awt.Color.red;
import static java.awt.Color.yellow;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Panel extends JPanel implements MouseMotionListener, MouseListener, ItemListener{
    
    Pizarra p;
    
    Choice selector;
    Choice selectorMover;
    Choice selectorColor;
    
    Color color;
    
    private Rectangulo RectanguloSeleccionado = null;
    private Rectangulo RectanguloSelec = null;
    private int Rectanguloi;
    private Texto TextoSeleccionado = null;
    
    ButtonGroup bg;
    JRadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9;
    
    Point u;
    Point w;
    
    public Panel(){
        p = new Pizarra();
        
        this.addMouseListener(this); //Hace posible el dar click
        this.addMouseMotionListener(this); //Hace posible el dar click
        this.setBackground(Color.white);
        
        selector = new Choice();
        selectorMover = new Choice();
        selectorColor = new Choice();
        
        color = black;
        
        selector.add("Que conector desea");
        selector.add("Linea");
        selector.add("Flecha");
        
        selectorMover.add("Que desea mover");
        selectorMover.add("Rectangulo");
        selectorMover.add("Texto");
        selectorMover.add("Rectangulo y linea");
        
        selectorColor.add("Color");
        selectorColor.add("Rojo");
        selectorColor.add("Azul");
        selectorColor.add("Negro");
        selectorColor.add("Verde");
        selectorColor.add("Gris");
        selectorColor.add("Amarillo");
        selectorColor.add("Celeste");
        selectorColor.add("Magenta");
        selectorColor.add("Rosa");
        
        
        r1=new JRadioButton("Dibujar");    
        r2=new JRadioButton("Rectangulo"); 
        r3=new JRadioButton("Ovalo");    
        r4=new JRadioButton("Borrar"); 
        r5=new JRadioButton("Texto");
        r6=new JRadioButton("Mover");
        r7=new JRadioButton("Conectores");
        r8=new JRadioButton("Goma");
        r9=new JRadioButton("Borrado multiple");
                
        bg=new ButtonGroup();    
        bg.add(r1);bg.add(r2);bg.add(r3);bg.add(r4);bg.add(r9);bg.add(r5);bg.add(r6);bg.add(r7);bg.add(r8);
        add(r8);add(r1);add(r2);add(r3);add(r4);add(r9); add(r5);add(r6);add(r7);
        
        add(selector);
        add(selectorMover);
        add(selectorColor);
        selector.addItemListener(this);
        selectorColor.addItemListener(this);
        u = null;
        w = null;
    }

    
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(r9.isSelected()){
            w = new Point(e.getX(), e.getY());
        }
        if(selectorMover.getSelectedItem()=="Rectangulo"&&r6.isSelected()){
            if(RectanguloSeleccionado != null){
                RectanguloSeleccionado.setXY(e.getX()-50,e.getY()-50);
            }
        }
        if(selectorMover.getSelectedItem()=="Texto"&&r6.isSelected()){
            if(TextoSeleccionado!=null){
                TextoSeleccionado.setXY(e.getX(),e.getY());
            }
        }
        if(r1.isSelected()){
            p.getLista5().add(new Ovalo(e.getX(),e.getY(),2,2));
        }
        if(r8.isSelected()){
            p.getLista7().add(new Goma(e.getX(),e.getY(),5,5));
        }
        if (selectorMover.getSelectedItem()=="Rectangulo y linea"&&r6.isSelected()){
            if(RectanguloSelec != null){
                this.p.getLista2().set(Rectanguloi,new Rectangulo(e.getX(),e.getY(),RectanguloSelec.getNombreClase()));
                int iE = 0;
                int iA = 0;
                for (Linea objLinea : p.getLista3()) {
                    if(new Rectangle(objLinea.getX1()-50, objLinea.getY1()-50,100 , 100).contains(e.getPoint())){
                        p.getLista3().set(iE,new Linea(e.getX(),e.getY(),objLinea.getX2(),objLinea.getY2(),color));
                    }
                    else if(new Rectangle(objLinea.getX2()-50, objLinea.getY2()-50, 100, 100).contains(e.getPoint())){
                        p.getLista3().set(iE,new Linea(objLinea.getX1(),objLinea.getY1(),e.getX(),e.getY(),color));
                    }
                    iE++;
                }
                for (Flecha objFlecha : p.getLista6()) {
                    if(new Rectangle(objFlecha.getX1()-50, objFlecha.getY1()-50,100 , 100).contains(e.getPoint())){
                        p.getLista6().set(iA,new Flecha(e.getX(),e.getY(),objFlecha.getX2(),objFlecha.getY2(),color));
                    }
                    else if(new Rectangle(objFlecha.getX2()-50, objFlecha.getY2()-50, 100, 100).contains(e.getPoint())){
                        p.getLista6().set(iA,new Flecha(objFlecha.getX1(),objFlecha.getY1(),e.getX(),e.getY(),color));
                    }
                    iA++;
                }
            }
        }
        repaint();
    
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
            if (r3.isSelected()) {
                p.getLista().add(new Ovalo(e.getX()-50,e.getY()-50,100,100));
            }
            if (r2.isSelected()) {
                String nombreClase = JOptionPane.showInputDialog("Indique el nombre de la Clase:");
                p.getLista2().add(new Rectangulo(e.getX()-50,e.getY()-50, nombreClase));
            }
            if (r8.isSelected()) {
                p.getLista7().add(new Goma(e.getX()-50,e.getY()-50,100,100));
            }
            if(selector.getSelectedItem()=="Linea"&&r7.isSelected()){
                 for(Rectangulo objRectangulo : p.getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                         if (u==null) {
                            u = new Point(e.getX(),e.getY());
                        }else{
                            w = new Point(e.getX(),e.getY());
                            p.getLista3().add(new Linea( u.x, u.y ,w.x ,w.y ,color));
                            repaint();
                            u = null;
                            w = null;
                        }
                    }
                 }
            }
            if(selector.getSelectedItem()=="Flecha"&&r7.isSelected()){
                 for(Rectangulo objRectangulo : p.getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                         if (u==null) {
                            u = new Point(e.getX(),e.getY());
                        }else{
                            w = new Point(e.getX(),e.getY());
                            p.getLista6().add(new Flecha( u.x, u.y ,w.x ,w.y ,color));
                            repaint();
                            u = null;
                            w = null;
                        }
                    }
                 }
            }
            if(r5.isSelected()){
                String Txt = JOptionPane.showInputDialog("Indique el texto que desea escribir:");
                p.getLista4().add(new Texto(e.getX(),e.getY(), Txt));
            }
            if(selectorMover.getSelectedItem()=="Rectangulo"&&r6.isSelected()){
                for(int i=0 ; i<p.getLista2().size() ; i++){
                    if(e.getX()>= p.getLista2().get(i).getX() && e.getY()<=p.getLista2().get(i).getY()+170 && e.getX()<=p.getLista2().get(i).getX()+100 && e.getY()>=p.getLista2().get(i).getY()){
                        RectanguloSeleccionado = p.getLista2().get(i);
                    }
                }
            }
            if(selectorMover.getSelectedItem()=="Texto"&&r6.isSelected()){
                for(int i=0 ; i<p.getLista4().size() ; i++){
                    if(e.getX()>= p.getLista4().get(i).getX() && e.getY()<=p.getLista4().get(i).getY()+6 && e.getX()<=p.getLista4().get(i).getX()+7*(p.getLista4().get(i).getTexto().length()) && e.getY()>=p.getLista4().get(i).getY()-6){
                        TextoSeleccionado = p.getLista4().get(i);
                    }
                }
            }
            if(r4.isSelected()){
                for (int i = 0; i < p.getLista2().size(); i++) {
                    if (new Rectangle(p.getLista2().get(i).getX(),p.getLista2().get(i).getY(),170,170).contains(e.getPoint())){
                        p.getLista2().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista3().size(); i++) {
                    if (new Rectangle(p.getLista3().get(i).getX1(),p.getLista3().get(i).getY1(),170,170).contains(e.getPoint())){
                        p.getLista3().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista6().size(); i++) {
                    if (new Rectangle(p.getLista6().get(i).getX1(),p.getLista6().get(i).getY1(),170,170).contains(e.getPoint())){
                        p.getLista6().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista().size(); i++) {
                    if (new Rectangle(p.getLista().get(i).getX(),p.getLista().get(i).getY(),170,170).contains(e.getPoint())){
                        p.getLista().remove(i);
                }
                }
                for (int i = 0; i < p.getLista5().size(); i++) {
                    if (new Rectangle(p.getLista5().get(i).getX(),p.getLista5().get(i).getY(),170,170).contains(e.getPoint())){
                        p.getLista5().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista4().size(); i++) {
                    if (new Rectangle(p.getLista4().get(i).getX(),p.getLista4().get(i).getY(),170,170).contains(e.getPoint())){
                        p.getLista4().remove(i);
                    }
                }
            }
            repaint();
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if(r9.isSelected()){
            u = new Point(e.getX(), e.getY());
            w = u;
        }
        if (selectorMover.getSelectedItem()=="Rectangulo y linea"&&r6.isSelected()) {
            int iN = 0;
            for(Rectangulo objRectangulo : p.getLista2()){
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
        if(r9.isSelected()){
            for(int i = 0; i < p.getLista2().size(); i++){
                    if (new Rectangle(u.x,u.y,w.x,w.y).contains(new Rectangle(p.getLista2().get(i).getX(),p.getLista2().get(i).getY(),170,170))) {
                        p.getLista2().remove(i);
                }
            }
            for (int i = 0; i < p.getLista3().size(); i++) {
                    if (new Rectangle(u.x,u.y,w.x,w.y).contains(new Rectangle(p.getLista3().get(i).getX1(),p.getLista3().get(i).getY1(),170,170))){
                        p.getLista3().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista6().size(); i++) {
                    if (new Rectangle(u.x,u.y,w.x,w.y).contains(new Rectangle(p.getLista6().get(i).getX1(),p.getLista6().get(i).getY1(),170,170))){
                        p.getLista6().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista().size(); i++) {
                    if (new Rectangle(u.x,u.y,w.x,w.y).contains(new Rectangle(p.getLista().get(i).getX(),p.getLista().get(i).getY(),170,170))){
                        p.getLista().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista5().size(); i++) {
                    if (new Rectangle(u.x,u.y,w.x,w.y).contains(new Rectangle(p.getLista5().get(i).getX(),p.getLista5().get(i).getY(),170,170))){
                        p.getLista5().remove(i);
                    }
                }
                for (int i = 0; i < p.getLista4().size(); i++) {
                    if (new Rectangle(p.getLista4().get(i).getX(),p.getLista4().get(i).getY(),170,170).contains(e.getPoint())){
                        p.getLista4().remove(i);
                    }
                }

            
            u = null;
            w = null;
            
        }
        if (selector.getSelectedItem()=="Rectangulo y linea"&&r6.isSelected()){
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
        if(selectorColor.getSelectedItem()=="Rojo"){
            color = red;
        }
        if(selectorColor.getSelectedItem()=="Azul"){
            color= blue;
        }
        if(selectorColor.getSelectedItem()=="Negro"){
            color= black;
        }
        if(selectorColor.getSelectedItem()=="Verde"){
            color= green;
        }
        if(selectorColor.getSelectedItem()=="Gris"){
            color= gray;
        }
        if(selectorColor.getSelectedItem()=="Amarillo"){
            color= yellow;
        }
        if(selectorColor.getSelectedItem()=="Celeste"){
            color= cyan;
        }
        if(selectorColor.getSelectedItem()=="Magenta"){
            color= magenta;
        }
        if(selectorColor.getSelectedItem()=="Rosa"){
            color= pink;
        }

    }
        @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);
        for(Rectangulo objRectangulo : p.getLista2()){
            if(p.getLista2().size()>0){
                objRectangulo.paint(g);
            }
            
        }
        for (Ovalo objOvalo : p.getLista()) {
            objOvalo.paint(g);
        }
        for (Linea objLinea : p.getLista3()) {
            objLinea.paint(g);
        }
        for (Flecha objFlecha : p.getLista6()) {
            objFlecha.paint(g2);
        }
        for(Texto Txt : p.getLista4()){
            Txt.paint(g);
        }
        for (Ovalo objOvalo : p.getLista5()) {
            objOvalo.paint(g);
        }
        for (Goma objGoma : p.getLista7()) {
            objGoma.paint(g);
        }
        if(r9.isSelected()){
            if (u != null && w != null) {
                g2.setPaint(Color.red);
                Shape r = makeRectangle(u.x, u.y, w.x, w.y);
                g2.draw(r);
            }
        }
        
    }
    public Pizarra getPizarra(){
        return p;
    }
    public void setPizarra(Pizarra nuevaPizarra){
        this.p = nuevaPizarra;
        this.repaint();
    }
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    
}

