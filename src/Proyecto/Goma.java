package Proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Goma implements Serializable{
    int x,y,w,h;
    public Goma(int x, int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w = w;
        this.h = h;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, w, h);
    }
}
