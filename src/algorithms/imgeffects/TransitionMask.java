package algorithms.imgeffects;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class TransitionMask extends ImageEffect {

    private int tick = 0;
    private List<int[]>[] pixels;

    public TransitionMask(){
        super();
        pixels = new List[256];

        for(int i=0; i<pixels.length; i++){
            pixels[i] = new LinkedList<>();
        }
    }

    @Override
    protected void loadImage(int type){
        super.loadImage(type);
        if(type == 1){
            for(int i=0; i<pixels.length; i++){
                pixels[i] = new LinkedList<>();
            }

            Color c;
            for(int y=0; y<mask.getHeight(); y++) {
                for (int x = 0; x < mask.getWidth(); x++) {
                    c = new Color(mask.getRGB(x, y));
                    pixels[c.getBlue()].add(new int[]{x,y});
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Transition mask";
    }

    @Override
    public void step() {
        if(tick > 255) reset();

        Graphics g = IMG.getGraphics();
        Color c;

        /*
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
        */
        g.setColor(Color.BLACK);
        for(int[] p : pixels[tick]){
            g.drawLine(p[0],p[1],p[0],p[1]);
        }

        tick++;
    }

    @Override
    public void reset() {
        super.reset();
        tick = 0;
    }

}
