package algorithms.imgeffects;

import java.awt.*;

public class ImageBlur extends ImageEffect {

    private int factor;
    private boolean up;

    public ImageBlur(){
        super();
    }

    @Override
    public String toString() {
        return "Image blur";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int avgr, avgb, avgg, count;
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<original.getHeight(); y+=factor){
            for(int x=0; x<original.getWidth(); x+=factor){
                avgr = 0; avgg = 0; avgb = 0; count = 0;
                for(int i=0; i<factor; i++){
                    if(y+i >= original.getHeight()) break;
                    for(int j=0; j<factor; j++){
                        if(x+j >= original.getWidth()) break;
                        c = new Color(original.getRGB(x+j, y+i));
                        avgr += c.getRed();
                        avgg += c.getGreen();
                        avgb += c.getBlue();
                        count++;
                    }
                }
                avgr /= count;
                avgg /= count;
                avgb /= count;

                g.setColor(new Color(avgr,avgg,avgb));
                g.fillRect(x, y, factor, factor);
            }
        }

        if(up){
            factor++;
            if(factor>=original.getWidth()/20) up = false;
        }else{
            factor--;
            if(factor<=1) up = true;
        }
    }

    @Override
    public void reset() {
        super.reset();
        factor = 1;
        up = true;
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        return list;
    }
}