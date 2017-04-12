package algorithms.imgeffects;

import image.Color;

public class Checkerboard extends ImageEffect {


    @Override
    public String toString() {
        return "Checkerboard";
    }

    @Override
    public void step() {

        double r, g, b;
        //blur using averaging algorithm
        for(int y=1; y<height-1; y++){
            for(int x = (y%2==0 ? 1 : 2); x<width-1; x+=2){
                r = g = b = 0;
                for(int[] p : new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}}){
                    Color c = new Color(IMG.getRGB(x+p[0], y+p[1]));
                    r += c.getRed();
                    g += c.getGreen();
                    b += c.getBlue();
                }
                r /= 4;
                g /= 4;
                b /= 4;
                IMG.setColor(new Color((int)(r+0.5),(int)(g+0.5),(int)(b+0.5)));
                IMG.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }


}
