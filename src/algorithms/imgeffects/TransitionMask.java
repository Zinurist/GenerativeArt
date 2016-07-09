package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class TransitionMask extends ImageEffect {

    private int tick = 0;
    private List<int[]>[] pixels;
    private JCheckBox boxReset;
    private JSpinner add;
    private boolean reset;

    public TransitionMask(){
        super();
        pixels = new List[256];

        for(int i=0; i<pixels.length; i++){
            pixels[i] = new LinkedList<>();
        }

        boxReset = new JCheckBox("reset img");
        add = new JSpinner(new SpinnerNumberModel(1,1,255,1));
    }

    @Override
    protected void loadImage(int type){
        super.loadImage(type);
        if(type == 1){
            for(int i=0; i<pixels.length; i++){
                pixels[i] = new LinkedList<>();
            }

            Color c;
            for(int y=0; y<mask.getHeight(); y++) {
                for (int x = 0; x < mask.getWidth(); x++) {
                    c = new Color(mask.getRGB(x, y));
                    pixels[c.getBlue()].add(new int[]{x,y});
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Transition mask";
    }

    @Override
    public void step(Graphics g) {
        if(tick > 255){
            if(boxReset.isSelected() || reset) reset();
            else{
                reset = true;
                stop();
                return;
            }
        }

        /*
        Color c;

        for(int y=0; y<mask.getHeight(); y++){
            for(int x=0; x<mask.getWidth(); x++){
                c = new Color(mask.getRGB(x, y));

                if(c.getBlue() < tick){
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x, y);
                }

            }
        }
        */

        g.setColor(Color.BLACK);
        int upper = Math.min(tick+(int)add.getValue(), 255);
        for(int k=tick; k<upper; k++) {
            for (int[] p : pixels[k]) {
                g.drawLine(p[0], p[1], p[0], p[1]);
            }
        }

        //tick++;
        tick += (int)add.getValue();
    }

    @Override
    public void reset() {
        super.reset();
        tick = 0;
        reset = false;
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(add);
        list.add(boxReset);
        return list;
    }

}
