package algorithms.imgeffects;

import image.Color;
import option.OptionList;

public class ColorFlip extends ImageEffect {

    private int type = 0;

    @Override
    public String toString() {
        return "Color flip";
    }

    @Override
    public void step() {
        Color c1,c2;

        //blur using averaging algorithm
        int min = height > width ? width:height;
        for(int y=0; y<min; y++){
            for(int x=y; x<min; x++){
                c1 = new Color(IMG.getRGB(x, y));
                c2 = new Color(IMG.getRGB(y, x));
                if(type == 0){

                    IMG.setColor(new Color(c2.getRed(),c1.getGreen(),c1.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c1.getRed(),c2.getGreen(),c2.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }else if(type == 1){

                    IMG.setColor(new Color(c1.getRed(),c2.getGreen(),c1.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c2.getRed(),c1.getGreen(),c2.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }else{

                    IMG.setColor(new Color(c1.getRed(),c1.getGreen(),c2.getBlue()));
                    IMG.drawLine(x, y, x, y);
                    IMG.setColor(new Color(c2.getRed(),c2.getGreen(),c1.getBlue()));
                    IMG.drawLine(y, x, y, x);
                }

            }
        }
    }

    @Override
    public void reset() {

    }


    @Override
    public OptionList getOptionList() {
        OptionList list = super.getOptionList();
        String[] colors = new String[]{"Red", "Green", "Blue"};
        list.addOption("Color", colors, type, val -> type = val);
        return list;
    }

}
