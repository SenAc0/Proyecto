package Proyecto;

import java.awt.Choice;
import java.awt.Color;
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
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class Panel extends JPanel implements MouseMotionListener, MouseListener, ItemListener, Serializable{
    ArrayList<Ovalo> lista;
    ArrayList<Rectangulo> lista2;
    ArrayList<Linea> lista3;
    ArrayList<Texto> lista4;
    ArrayList<Ovalo> lista5;
    ArrayList<Flecha> lista6;
    
    ButtonGroup bg;
    
    Choice selector;
    Choice selectorMover;
    
    private Rectangulo RectanguloSeleccionado = null;
    private Rectangulo RectanguloSelec = null;
    private int Rectanguloi;
    private Texto TextoSeleccionado = null;
    
    JRadioButton r1,r2,r3,r4,r5,r6,r7;
    
    Point u;
    Point w;
    
    public Panel(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        lista3 =  new ArrayList<>();
        lista4 =  new ArrayList<>();
        lista5 =  new ArrayList<>();
        lista6 =  new ArrayList<>();
        
        this.addMouseListener(this); //Hace posible el dar click
        this.addMouseMotionListener(this); //Hace posible el dar click
        this.setBackground(Color.white);
        
        r1=new JRadioButton("Dibujar");    
        r2=new JRadioButton("Rectangulo"); 
        r3=new JRadioButton("Ovalo");    
        r4=new JRadioButton("Borrar"); 
        r5=new JRadioButton("Texto");
        r6=new JRadioButton("Mover");
        r7=new JRadioButton("Conectores");
        
        bg=new ButtonGroup();    
        bg.add(r1);bg.add(r2);bg.add(r3);bg.add(r4);bg.add(r5);bg.add(r6);bg.add(r7);
        add(r1);add(r2);add(r3);add(r4); add(r5);add(r6);add(r7);
        
        selector = new Choice();
        selectorMover = new Choice();
        
        selector.add("Que conector desea");
        selector.add("Linea");
        selector.add("Flecha");
        
        selectorMover.add("Que desea mover");
        selectorMover.add("Rectangulo");
        selectorMover.add("Texto");
        selectorMover.add("Testeo de Selector");
        selectorMover.add("Rectangulo y linea");
        
        add(selector);
        add(selectorMover);
        selector.addItemListener(this);
        selectorMover.addItemListener(this);
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

    public ArrayList<Flecha> getLista6() {
        return lista6;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectorMover.getSelectedItem()=="Testeo de Selector"&&r6.isSelected()){
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
            lista5.add(new Ovalo(e.getX(),e.getY(),2,2));
        }
        if (selectorMover.getSelectedItem()=="Rectangulo y linea"&&r6.isSelected()){
            if(RectanguloSelec != null){
                this.getLista2().set(Rectanguloi,new Rectangulo(e.getX(),e.getY(),RectanguloSelec.getNombreClase()));
                int iE = 0;
                int iA = 0;
                for (Linea objLinea : getLista3()) {
                    if(new Rectangle(objLinea.getX1()-50, objLinea.getY1()-50,100 , 100).contains(e.getPoint())){
                        getLista3().set(iE,new Linea(e.getX(),e.getY(),objLinea.getX2(),objLinea.getY2()));
                    }
                    else if(new Rectangle(objLinea.getX2()-50, objLinea.getY2()-50, 100, 100).contains(e.getPoint())){
                        getLista3().set(iE,new Linea(objLinea.getX1(),objLinea.getY1(),e.getX(),e.getY()));
                    }
                    iE++;
                }
                for (Flecha objFlecha : getLista6()) {
                    if(new Rectangle(objFlecha.getX1()-50, objFlecha.getY1()-50,100 , 100).contains(e.getPoint())){
                        getLista6().set(iA,new Flecha(e.getX(),e.getY(),objFlecha.getX2(),objFlecha.getY2()));
                    }
                    else if(new Rectangle(objFlecha.getX2()-50, objFlecha.getY2()-50, 100, 100).contains(e.getPoint())){
                        getLista6().set(iA,new Flecha(objFlecha.getX1(),objFlecha.getY1(),e.getX(),e.getY()));
                    }
                    iA++;
                }
            }
        }
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getButton()==1){
            if (r3.isSelected()) {
                getLista().add(new Ovalo(e.getX()-50,e.getY()-50,100,100));
            }
            if (r2.isSelected()) {
                String nombreClase = JOptionPane.showInputDialog("Indique el nombre de la Clase:");
                getLista2().add(new Rectangulo(e.getX()-50,e.getY()-50, nombreClase));
            }
            if (selector.getSelectedItem()=="Label") {
               //Aqui deberia ponerse una Label, para colocar
            }
            if(selector.getSelectedItem()=="Linea"&&r7.isSelected()){
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
            if(selector.getSelectedItem()=="Flecha"&&r7.isSelected()){
                 for(Rectangulo objRectangulo : getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                         if (u==null) {
                            u = new Point(e.getX(),e.getY());
                        }else{
                            w = new Point(e.getX(),e.getY());
                            getLista6().add(new Flecha( u.x, u.y ,w.x ,w.y ));
                            repaint();
                            u = null;
                            w = null;
                        }
                    }
                 }
            }
            if(r5.isSelected()){
                String Txt = JOptionPane.showInputDialog("Indique el texto que desea escribir:");
                getLista4().add(new Texto(e.getX(),e.getY(), Txt));
            }
            if(selectorMover.getSelectedItem()=="Rectangulo"&&r6.isSelected()){
                for(int i=0 ; i<getLista2().size() ; i++){
                    if(e.getX()>= getLista2().get(i).getX() && e.getY()<=getLista2().get(i).getY()+170 && e.getX()<=getLista2().get(i).getX()+100 && e.getY()>=getLista2().get(i).getY()){
                        RectanguloSeleccionado = getLista2().get(i);
                    }
                }
            }
            if(selectorMover.getSelectedItem()=="Texto"&&r6.isSelected()){
                for(int i=0 ; i<getLista4().size() ; i++){
                    if(e.getX()>= getLista4().get(i).getX() && e.getY()<=getLista4().get(i).getY()+6 && e.getX()<=getLista4().get(i).getX()+7*(getLista4().get(i).getTexto().length()) && e.getY()>=getLista4().get(i).getY()-6){
                        TextoSeleccionado = getLista4().get(i);
                    }
                }
            }
            if(r4.isSelected()){
                /*for(Rectangulo objRectangulo : getLista2()){
                    if (new Rectangle(objRectangulo.getX(),objRectangulo.getY(),170,170).contains(e.getPoint())){
                        getLista2().remove(objRectangulo);
                    }
                 }*/
                for (int i = 0; i < getLista2().size(); i++) {
                    if (new Rectangle(getLista2().get(i).getX(),getLista2().get(i).getY(),170,170).contains(e.getPoint())){
                        getLista2().remove(i);
                    }
                }
                for (int i = 0; i < getLista3().size(); i++) {
                    if (new Rectangle(getLista3().get(i).getX1(),getLista3().get(i).getY1(),170,170).contains(e.getPoint())){
                        getLista3().remove(i);
                    }
                }
                for (int i = 0; i < getLista6().size(); i++) {
                    if (new Rectangle(getLista6().get(i).getX1(),getLista6().get(i).getY1(),170,170).contains(e.getPoint())){
                        getLista6().remove(i);
                    }
                }
                for (int i = 0; i < getLista().size(); i++) {
                    if (new Rectangle(getLista().get(i).getX(),getLista().get(i).getY(),170,170).contains(e.getPoint())){
                        getLista().remove(i);
                }
                }
                for (int i = 0; i < getLista5().size(); i++) {
                    if (new Rectangle(getLista5().get(i).getX(),getLista5().get(i).getY(),170,170).contains(e.getPoint())){
                        getLista5().remove(i);
                }
                }
                /*for(Linea objLinea : getLista3()){
                    if (new Rectangle(objLinea.getX1(),objLinea.getY1(),170,170).contains(e.getPoint())){
                        getLista3().remove(objLinea);
                    }
                 }*/
            }
            repaint();
        }
        
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if(selectorMover.getSelectedItem()=="Testeo de Selector"&&r6.isSelected()){
            u = new Point(e.getX(), e.getY());
            w = u;
        }
        if (selectorMover.getSelectedItem()=="Rectangulo y linea"&&r6.isSelected()) {
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
        if(selector.getSelectedItem()=="Testeo de Selector"&&r6.isSelected()){
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

    }
        @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
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
        for (Flecha objFlecha : getLista6()) {
            objFlecha.paint(g2);
        }
        for(Texto Txt : getLista4()){
            Txt.paint(g);
        }
        for (Ovalo objOvalo : getLista5()) {
            objOvalo.paint(g);
        }
        if(selector.getSelectedItem()=="Testeo de Selector"){

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
