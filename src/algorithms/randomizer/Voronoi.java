package algorithms.randomizer;

import java.awt.*;
import java.util.ArrayList;

public class Voronoi extends Randomizer{

    //TODO make options out of these
    private boolean euclid = true;
    private boolean showPoints = true;
    private boolean border = true;
    private int num = 50;

    private ArrayList<Integer> points;

    public Voronoi(){
        super();
        points = new ArrayList<>(2*num);
        reset();
    }

    @Override
    public void step(Graphics g, int width, int height) {


        g.setColor(Color.BLACK);
        int off = points.size()/2;
        for(int i=0; i<off; i++){
            g.fillOval(points.get(i)-3, points.get(off+i), 6, 6);
        }

    }

    @Override
    public String toString() {
        return "Voronoi diagram";
    }

    @Override
    public void reset() {
        points.ensureCapacity(2*num);

        for(int i=0; i<points.size()/2; i++){
            points.set(i, r.nextInt(IMG.getWidth()));
            points.set(num+i, r.nextInt(IMG.getHeight()));
        }
    }

}
