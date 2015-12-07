package algorithms.randomizer;

import java.awt.*;

public class RandomLines extends Randomizer{

    int lines = 100;

    @Override
    public void step(Graphics g, int width, int height) {
        for(int i=0; i<lines; i++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
    }

    @Override
    public String toString() {
        return "Lines";
    }
}
