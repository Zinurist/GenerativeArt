package algorithms.randomizer;

import image.Color;
import option.OptionList;

public class Mountains extends Randomizer{

    //cumulative probability distribution
    //if in up mode:
    //(0) +3 (0.5) +2 (0.25) +1 (0.55) +0 (0.75) -1 (0.9) -2 (0.95) -3 (1.0)
    //else: mirrored (-3 -2 ...)
    private static double[] prob =   {0.5, 0.25, 0.55, 0.75, 0.9, 0.95, 1.0};


    private int stepX = 1;
    private int stepY = 1;
    private boolean slopes;
    private boolean loop;
    private boolean resetAtLoop;
    private boolean instant;

    private boolean reset;
    private boolean up;
    private int curY, curX;

    public Mountains(){
        super(false);
        slopes = false;
        loop = false;
        resetAtLoop = false;
        instant = false;
        reset();
    }

    @Override
    public void step(int width, int height) {
        if(reset) init();

        int xOld, yOld;
        double d;

        do {
            xOld = curX;
            yOld = curY;
            d = r.nextDouble();
            for (int i = 0; i < prob.length; i++) {
                if (d < prob[i]) {
                    curY += stepY * (up ? (3 - i) : (i - 3));
                    curX += stepX;
                    up = (up && curY >= yOld) || (!up && curY > yOld);
                    break;
                }
            }

            IMG.setColor(Color.BLACK);

            if (slopes) {
                IMG.drawLine(xOld, yOld, curX, curY);
            } else {
                IMG.drawLine(xOld, yOld, curX, yOld);
                IMG.drawLine(curX, yOld, curX, curY);
            }
        }while(instant && curX<width);


        if(curX >= width){
            reset = resetAtLoop;
            curX = 0;
            if(!loop){
                stop();
            }
        }
    }

    @Override
    public String toString() {
        return "Mountains";
    }

    @Override
    public void reset() {
        reset = false;
        up = true;
        curY = IMG.getHeight()/2;
        curX = 0;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();

        list.addOption("slopes", slopes, val -> slopes = val);
        list.addOption("loop", loop, val -> loop = val);
        list.addOption("reset at loop", resetAtLoop, val -> resetAtLoop = val);
        list.addOption("step x", stepX, 1, 100, val -> stepX = val);
        list.addOption("step y", stepY, 1, 100, val -> stepY = val);
        list.addOption("instant", instant, val -> instant = val);

        return list;
    }

}
