package algorithms.randomizer;

import image.Color;

public class RandomPositions extends Randomizer{

    //like Random pixels, but "animated"

    private int tick;

    @Override
    public void step(int width, int height) {
        if(color){
            IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        }

        int x = r.nextInt(width);
        int y = r.nextInt(height);
        IMG.drawLine(x,y,x,y);

        tick++;
        if(tick > width*height/2){
            stop();
            tick = 0;
        }
    }

    @Override
    public String toString() {
        return "Random point";
    }

    @Override
    public void reset() {
        tick = 0;
    }

}
