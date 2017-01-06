package algorithms;

import image.Color;
import option.OptionList;

public class Squares extends Algorithm {

    private boolean circles;

    public Squares(){
        circles = false;
    }

    @Override
    public String toString() {
        return "Squares";
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("circles", circles, val -> circles = val);
        return list;
    }

    @Override
    public void step() {
        int width=1;
        int drawn=0;
        int xStart = IMG.getWidth()/2;
        int yStart = IMG.getHeight()/2;
        IMG.setColor(Color.BLACK);
        while(drawn<IMG.getWidth()){
            if(circles) {
                for (int i = 0; i < width && drawn < IMG.getWidth(); i++) {
                    IMG.drawOval(xStart - drawn, yStart - drawn, drawn * 2, drawn * 2);
                    drawn++;
                }
            }else{
                for (int i = 0; i < width && drawn < IMG.getWidth(); i++) {
                    IMG.drawRect(xStart - drawn, yStart - drawn, drawn * 2, drawn * 2);
                    drawn++;
                }
            }
            width++;
            drawn+=width;
            width++;
        }
    }

    @Override
    public void reset() {
        //nuffin
    }
}
