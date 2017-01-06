package algorithms.randomizer;

import image.Color;
import option.OptionList;

public class RandomCircles extends Randomizer{

    private int num = 10;

    @Override
    public void step(int width, int height) {
        int numTemp = num;
        int num1, num2, num3;
        for(int i=0; i<numTemp; i++){
            num1=r.nextInt(50)+50;
            num2=r.nextInt(width)-(num1/2);
            num3=r.nextInt(height)-(num1/2);
            if(color){
                IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                IMG.fillOval(num2,num3, num1, num1);
                IMG.setColor(Color.BLACK);
            }
            IMG.drawOval(num2, num3, num1, num1);
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("#circles", num, 0, 1000, 10, (OptionList.IntegerOptionListener)val -> num = val);
        return list;
    }

    @Override
    public String toString() {
        return "Circles";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
