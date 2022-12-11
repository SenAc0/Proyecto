package Proyecto;

import java.awt.Graphics;
import java.io.Serializable;

public class Ovalo implements Serializable{
    int x,y,w,h;
    public Ovalo(int x, int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w = w;
        this.h = h;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void paint(Graphics g){
        g.fillOval(x, y, w, h);
    }
}
