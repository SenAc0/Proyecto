package Proyecto;

import java.awt.Graphics;

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