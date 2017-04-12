package algorithms.imgeffects;

import image.Color;

import java.util.Random;

public class Dithering extends ImageEffect {

    @Override
    public String toString() {
        return "Dithering";
    }

    @Override
    public void step() {
        int val;
        Color c;
        Random r = new Random();

        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(original.getRGB(x, y));
                val = (c.getRed() + c.getBlue() + c.getGreen()) / 3;

                if(r.nextInt(255) >= val){
                    IMG.setColor(Color.BLACK);
                }else{
                    IMG.setColor(Color.WHITE);
                }
                IMG.drawLine(x, y, x, y);
            }
        }

    }

    @Override
    public void reset() {

    }

}
