package algorithms.randomizer;

import java.awt.*;

public class RandomStrings extends Randomizer {
    @Override
    public void step(Graphics g, int width, int height) {
        int num1=r.nextInt(100)+50;
        int num2;
        for(int i=0; i<num1; i++){
            String s = "";
            num2=r.nextInt(5)+5;
            for(int n=0; n<num2;n++){
                char c=(char) (r.nextInt(26)+97);
                s+=c;
            }

            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            g.drawString(s, r.nextInt(width)-5, r.nextInt(height)-5);
        }
    }

    @Override
    public String toString() {
        return "Strings";
    }

    @Override
    public void reset() {
        //nuffin
    }
}
