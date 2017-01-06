package algorithms;

import image.Color;
import option.OptionList;

public class Circles extends Algorithm{

    private boolean circle;
    private int radX, radY;

    public Circles() {
        circle = true;
        radX = 5;
        radY = 5;
        reset();
    }

    @Override
    public String toString() {
        return "Circles";
    }

    @Override
    public void step() {
        if(isRunning()){
            emptyIMG();
        }

        IMG.setColor(Color.BLACK);

        int rx = radX;
        int ry = circle ? radX : radY;

        boolean shifted = false;
        int width = rx*2;
        int height = ry*2;
        int deltaY  = (int)(Math.sqrt(3) * ry + 0.5);
        //first approach for circles:
        //sqrt(rad*rad + deltaY*deltaY) = 2*rad
        //rad*rad + deltaY*deltaY = 4*rad*rad
        //deltaY*deltaY = 3*rad*rad
        //deltaY = sqrt(3)*rad

        //second approach for both ovals and circles:
        //the angle from the middle points of the ovals stays the same, which is 60°
        //->radY*sin(60°) is the change in y to the colliding point
        //->radY*sin(60°) * 2 is the change in y between both middle points
        //radY*sin(60°)*2 = radY*sqrt(3)/2*2 = radY*sqrt(3)

        for(int y = 0; y<IMG.getHeight()+deltaY; y+=deltaY){
            for(int x = shifted ? 0 : rx; x<IMG.getWidth()+width; x+=width){
                IMG.drawOval(x - rx, y - ry, width, height);
            }
            shifted = !shifted;
        }


    }

    @Override
    public void reset() {
        //reset variables
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();

        list.addOption("Radius x", radX, 2, 500, val -> radX = val);
        list.addOption("Radius y", radY, 2, 500, val -> radY = val);
        list.addOption("Type", new String[]{"circle", "oval"}, 0, val -> circle = val==0);
        return list;
    }
}
