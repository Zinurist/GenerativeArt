package algorithms.randomizer;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class RandomLines extends Randomizer{

    JSpinner lines = new JSpinner(new SpinnerNumberModel(100, 0, 1000, 10));

    @Override
    public void step(Graphics g, int width, int height) {
        for(int i=0; i<(Integer)lines.getValue(); i++){
            if(color){
                g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            }
            g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
    }

    @Override
    public List<Component> getOptionList(){
        List<Component> list = super.getOptionList();
        list.add(lines);
        return list;
    }

    @Override
    public String toString() {
        return "Lines";
    }
}
