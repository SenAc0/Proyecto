package Proyecto;

import java.awt.Graphics;

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