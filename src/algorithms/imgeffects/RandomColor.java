package algorithms.imgeffects;

import java.util.Random;
import image.Color;
import option.OptionList;

public class RandomColor extends ImageEffect {

    private int color;

    public RandomColor(){
        super();
        color = 0;
    }

    @Override
    public String toString() {
        return "Random color";
    }

    @Override
    public void step() {
        Color c;
        double factor = new Random().nextDouble()*2;
        double factor2 = new Random().nextDouble()*2;
        double factor3 = new Random().nextDouble()*2;
        //blur using averaging algorithm
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(original.getRGB(x, y));
                switch(color){
                    case 0:
                        IMG.setColor(new Color(c.getRed(), c.getGreen(), (int)Math.min(Math.round(c.getBlue() * factor) , 255) ));
                        break;
                    case 1:
                        IMG.setColor(new Color((int)Math.min(Math.round(c.getRed() * factor) , 255), c.getGreen(), c.getBlue() ));
                        break;
                    case 2:
                        IMG.setColor(new Color(c.getRed(), (int)Math.min(Math.round(c.getGreen() * factor) , 255), c.getBlue() ));
                        break;
                    case 3:
                        IMG.setColor(new Color((int)Math.min(Math.round(c.getRed() * factor) , 255), (int)Math.min(Math.round(c.getGreen() * factor2) , 255), (int)Math.min(Math.round(c.getBlue() * factor3) , 255) ));
                        break;
                    default:
                        System.out.println("shouldn't happen");
                        break;
                }
                IMG.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("Color", new String[]{"Blue", "Red", "Green", "All"}, color, val -> color = val);
        return list;
    }

}
