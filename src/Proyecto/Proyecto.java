package Proyecto;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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


class PanelDeControl extends JPanel implements ActionListener{
    JButton b1;
    JButton b2;
    public PanelDeControl(){
        this.setLayout(new FlowLayout());
        b1 = new JButton("1");
        b2 = new JButton("2");
        add(b1);
        add(b2);
        this.setBackground(Color.blue);
        b1.addActionListener(this);
        b2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)){
           // panel2.
           System.out.println("1");
        }
        if(e.getSource().equals(b2)){
           // panel2.
           System.out.println("2");
        }
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }
}