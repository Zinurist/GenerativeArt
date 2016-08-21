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
            g.fillOval(points.get(i)-4, points.get(off+i)-4, 8, 7);//7 instead of 8, because it looks like shit otherwise
        }

    }

    @Override
    public String toString() {
        return "Voronoi diagram";
    }

    @Override
    public void reset() {
        points.ensureCapacity(2*num);

        for(int i=0; i<num; i++){
            points.add(i, r.nextInt(IMG.getWidth()));
            points.add(2*i+1, r.nextInt(IMG.getHeight()));
        }
    }

}
