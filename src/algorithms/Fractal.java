package algorithms;

import java.awt.*;
import java.util.LinkedList;

public class Fractal extends Algorithm{

    private LinkedList<int[]> points;
    private int r;


    @Override
    public String toString() {
        return "Fractal";
    }

    @Override
    public void step(Graphics g) {
        if(r<1) return;

        g.setColor(Color.BLACK);

        LinkedList<int[]> newp = new LinkedList<>();

        for(int[] p : points){
            //drawing points
            //g.drawOval(p[0]-r,p[1]-r,r*2,r*2);
            //g.fillOval(p[0]-r,p[1]-r,r*2,r*2);
            g.drawRect(p[0]-r,p[1]-r,r*2,r*2);
            //g.fillRect(p[0]-r,p[1]-r,r*2,r*2);

            //new points
            int mod = r;
            /*
            newp.add(new int[]{p[0] + mod, p[1]});
            newp.add(new int[]{p[0], p[1] + mod});
            newp.add(new int[]{p[0] - mod, p[1]});
            newp.add(new int[]{p[0], p[1] - mod});
            */

            newp.add(new int[]{p[0] + mod, p[1] + mod});
            newp.add(new int[]{p[0] - mod, p[1] + mod});
            newp.add(new int[]{p[0] - mod, p[1] - mod});
            newp.add(new int[]{p[0] + mod, p[1] - mod});
        }

        r = r/2;
        if(r>=1) points = newp;
        else{
            points = null;
            stop();
        }

    }

    @Override
    public void reset() {
        emptyIMG();
        points = new LinkedList<>();
        points.add(new int[]{IMG.getWidth()/2, IMG.getHeight()/2});
        r = IMG.getWidth()/4;
    }
}
