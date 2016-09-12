package algorithms;

import java.awt.*;

public class CoordinateColor extends Algorithm{

    private int mode;

    public CoordinateColor(){
        super();
        reset();
    }

    @Override
    public String toString() {
        return "Coordinate Color";
    }

    @Override
    public void step(Graphics g) {
        Color c;
        float height = IMG.getHeight();
        float width = IMG.getWidth();
        for(int y = 0; y < IMG.getHeight(); y++){
            for(int x = 0; x < IMG.getWidth(); x++){
                switch(mode){
                    case 0: c = new Color(x/width,y/height, 1-(float)Math.sqrt((x*y)/(width*height)) ); break;
                    case 1: c = new Color(1-(float)Math.sqrt((x*y)/(width*height)),x/width,y/height); break;
                    case 2: c = new Color(y/height,1-(float)Math.sqrt((x*y)/(width*height)),x/width); break;
                    default: c = new Color(0,0,0);
                }

                g.setColor(c);
                g.drawLine(x,y,x,y);
            }
        }

        mode++;
        if(mode > 2) mode = 0;
    }

    @Override
    public void reset() {
        mode = 0;
    }

}
