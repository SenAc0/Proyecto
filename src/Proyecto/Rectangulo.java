package Proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangulo implements Serializable{
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