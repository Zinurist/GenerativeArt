package algorithms.randomizer;


import image.Color;
import option.OptionList;

public class ChaosGame extends Randomizer {

    private int numPoints;
    private double factor;
    private boolean circle;

    private double x, y;
    private int[][] points;

    public ChaosGame(){
        numPoints = 3;
        factor = 0.5;
        circle = true;
        reset();
    }

    @Override
    public void step(int width, int height) {
        int moveTo = r.nextInt(points.length);

        double dx = points[moveTo][0] - x;
        double dy = points[moveTo][1] - y;

        x = x + dx*factor;
        y = y + dy*factor;

        IMG.setColor(Color.BLACK);
        IMG.draw((int)(x+0.5),(int)(y+0.5));
        IMG.setColor(Color.BLUE);
        for(int[] p : points)
            IMG.fillCircle(p[0]-5,p[1]-5,10);
    }

    @Override
    public String toString() {
        return "Chaos Game";
    }

    @Override
    public void reset() {
        int w = IMG.getWidth();
        int h = IMG.getWidth();

        x = r.nextInt(w);
        y = r.nextInt(h);
        points = new int[numPoints][];
        if(circle){
            int circleWidth = (w*3)/8;
            int circleHeight = (h*3)/8;

            double dphi = Math.PI * 2.0 / numPoints;
            for(int i=0; i<numPoints; i++){
                //pi/2 so that it starts at the top, minus instead of add since y is reversed
                double phi = i * dphi - Math.PI/2;
                points[i] = new int[]{
                        (int)(w/2 + Math.cos(phi) * circleWidth + 0.5),
                        (int)(h/2 + Math.sin(phi) * circleHeight + 0.5)
                };
            }

        }else {
            for (int i = 0; i < numPoints; i++) {
                points[i] = new int[]{r.nextInt(w), r.nextInt(h)};
            }
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("num points", numPoints, 3, 30, val -> numPoints = val);
        list.addOption("factor", factor, 0., 1., 0.001, val -> factor = val);
        list.addOption("align to circle", circle, val -> circle = val);
        return list;
    }
}
