package Proyecto;

import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Graphics;
import java.io.Serializable;

public class Linea implements Serializable{
    Color color;
    int x1;
    int y1;
    int x2;
    int y2;
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
    
    public Linea(int x1, int y1, int x2, int y2,Color color1){
        if (color1==null) {
            this.color=black;
        }else{
            this.color = color1;
        }
        
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    public void paint(Graphics g){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}