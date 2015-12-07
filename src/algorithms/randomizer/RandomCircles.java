package algorithms.randomizer;

import java.awt.*;

public class RandomCircles extends Randomizer{
    @Override
    public void step(Graphics g, int width, int height) {
        int num1=r.nextInt(90)+10;
        int num2, num3;
        for(int i=0; i<num1; i++){
            num1=r.nextInt(50)+50;
            num2=r.nextInt(width)-(num1/2);
            num3=r.nextInt(height)-(num1/2);
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
                g.fillOval(num2,num3, num1, num1);
                g.setColor(Color.BLACK);
            }
            g.drawOval(num2, num3, num1, num1);
        }
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
