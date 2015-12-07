package algorithms.randomizer;

import java.awt.*;

public class RandomQuadrables extends Randomizer{

    int num = 5;

    @Override
    public void step(Graphics g, int width, int height) {
        int[] xPoints=new int[4];
        int[] yPoints=new int[4];
        for(int i=0; i<num; i++){
            for(int n=0; n<4; n++){
                if(n<2){
                    xPoints[n]=(r.nextInt(50)+i*50);
                }else{
                    xPoints[n]=width-(r.nextInt(50)+i*50);
                }
                if(n%3==0){
                    yPoints[n]=(r.nextInt(50)+i*50);
                }else{
                    yPoints[n]=height-(r.nextInt(50)+i*50);
                }
            }
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
            }
            g.drawPolygon(xPoints, yPoints, 4);
        }
    }

    @Override
    public String toString() {
        return "Quadrables";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
