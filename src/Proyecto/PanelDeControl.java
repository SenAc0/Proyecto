/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import static java.lang.Thread.sleep;
import java.lang.InterruptedException;

/**
 *
 * @author Okrim
 */
public class PanelDeControl extends JPanel implements ActionListener{
    Ventana v;
    JButton b1;
    JButton b2;
    JButton bguardar;
    JButton bcargar;
    JButton bBorrarTodo;
    JPanel p;
    Panel p1;
    Panel p2;
    boolean quiereguardar;
    public PanelDeControl(JPanel p,Panel opcion1, Panel opcion2, Ventana v){
        this.v=v;
        quiereguardar=false;
        this.p=p;
        this.p1=opcion1;
        this.p2=opcion2;
        this.setLayout(new FlowLayout());
        b1 = new JButton("1");
        b2 = new JButton("2");
        bguardar = new JButton("Guardar");
        bcargar = new JButton("Cargar");
        bBorrarTodo = new JButton("Borrar todo");
        add(b1);add(b2);add(bguardar);add(bcargar);add(bBorrarTodo);
        this.setBackground(Color.blue);
        b1.addActionListener(this);b2.addActionListener(this);bguardar.addActionListener(this);bcargar.addActionListener(this);bBorrarTodo.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)){
           // panel2.
           System.out.println("1");
           p.add(p1);
           p1.setVisible(true);
           p2.setVisible(false);
           p.validate();
           p.repaint();
        }
        if(e.getSource().equals(b2)){
           // panel2.
           System.out.println("2");
           p2.setVisible(true);
           p.add(p2);
           p1.setVisible(false);
           p.validate();
           p.repaint();
        }
        if(e.getSource().equals(bBorrarTodo)){
            Pizarra pTemp = new Pizarra();
            if(p1.isVisible()){
                p1.setPizarra(pTemp);
            }else{
                p2.setPizarra(pTemp);
            }
        }
        if(e.getSource().equals(bguardar)){
            try{
                    ObjectOutputStream escribiendo_fichero1=new ObjectOutputStream(new FileOutputStream("pruebaser1.dat"));
                    escribiendo_fichero1.writeObject(v.getPizarra1());
                    escribiendo_fichero1.flush();
                    escribiendo_fichero1.close();
        
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PanelDeControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanelDeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            
        }
        if(e.getSource().equals(bcargar)){
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream("pruebaser1.dat");
                ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
                Pizarra p3 = (Pizarra) objectInputStream.readObject();
                objectInputStream.close();
                v.setPanelPizarra1(p3);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PanelDeControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanelDeControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PanelDeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }
    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }
    public Panel getP1(){
        return this.p1;
    }
    public boolean quiereGuardar(){
        return this.quiereguardar;
    }
    public Pizarra getPizarra1(){
        return p1.getPizarra();
    }
    public Pizarra getPizarra2(){
        return this.p2.getPizarra();
    }
    public void setPanelPizarra1(Pizarra nuevaPizarra){
        this.p1.setPizarra(nuevaPizarra);
        this.repaint();
    }
    public void setPanelPizarra2(Pizarra nuevaPizarra){
        this.p2.setPizarra(nuevaPizarra);
    }
}
