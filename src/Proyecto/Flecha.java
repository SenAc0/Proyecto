package Proyecto;

import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Flecha implements Serializable {
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
    
    public Flecha(int x1, int y1, int x2, int y2,Color color1){
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
    public void paint(final Graphics2D g){
        g.setColor(color);
        final int ARR_SIZE = 5;

        // Get the current transform
        AffineTransform saveAT = g.getTransform();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        // Perform transformation
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, (int) len, 0);
        g.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
                new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);

        // Restore original transform
        g.setTransform(saveAT);
    }
}

