package algorithms.randomizer;

import java.awt.*;

public class RandomPolygon extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        int num1=r.nextInt(40)+10;
        int[] xPoints=new int[num1];
        int[] yPoints=new int [num1];
        for(int i=0; i<num1; i++){
            xPoints[i]=r.nextInt(width);
            yPoints[i]=r.nextInt(height);
        }
        if(color){
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.fillPolygon(xPoints, yPoints, num1);
            g.setColor(Color.BLACK);
        }
        g.drawPolygon(xPoints, yPoints, num1);
    }

    @Override
    public String toString() {
        return "Polygon";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
