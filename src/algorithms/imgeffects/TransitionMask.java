package algorithms.imgeffects;

import java.awt.*;

public class TransitionMask extends ImageEffect {

    private int tick = 0;

    @Override
    public String toString() {
        return "Transition mask";
    }

    @Override
    public void step() {
        if(tick > 255) reset();

        Graphics g = IMG.getGraphics();
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<mask.getHeight(); y++){
            for(int x=0; x<mask.getWidth(); x++){
                c = new Color(mask.getRGB(x, y));

                if(c.getBlue() < tick){
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x, y);
                }

            }
        }

        tick++;
    }

    @Override
    public void reset() {
        super.reset();
        tick = 0;
    }

}
