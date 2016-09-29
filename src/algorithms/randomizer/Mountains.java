package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;

public class Mountains extends Randomizer{

    //cumulative probability distribution
    //if in up mode:
    //(0) +3 (0.5) +2 (0.25) +1 (0.55) +0 (0.75) -1 (0.9) -2 (0.95) -3 (1.0)
    //else: mirrored (-3 -2 ...)
    private static double[] prob =   {0.5, 0.25, 0.55, 0.75, 0.9, 0.95, 1.0};


    private int stepX = 1;
    private int stepY = 1;
    private boolean slopes;
    private boolean loop;
    private boolean resetAtLoop;
    private boolean instant;

    private boolean reset;
    private boolean up;
    private int curY, curX;

    public Mountains(){
        super(false);
        slopes = false;
        loop = false;
        resetAtLoop = false;
        instant = false;
        reset();
    }

    @Override
    public void step(Graphics g, int width, int height) {
        if(reset) init();

        int xOld, yOld;
        double d;

        do {
            xOld = curX;
            yOld = curY;
            d = r.nextDouble();
            for (int i = 0; i < prob.length; i++) {
                if (d < prob[i]) {
                    curY += stepY * (up ? (3 - i) : (i - 3));
                    curX += stepX;
                    up = (up && curY >= yOld) || (!up && curY > yOld);
                    break;
                }
            }

            g.setColor(Color.BLACK);

            if (slopes) {
                g.drawLine(xOld, yOld, curX, curY);
            } else {
                g.drawLine(xOld, yOld, curX, yOld);
                g.drawLine(curX, yOld, curX, curY);
            }
        }while(instant && curX<width);


        if(curX >= width){
            reset = resetAtLoop;
            curX = 0;
            if(!loop){
                stop();
            }
        }
    }

    @Override
    public String toString() {
        return "Mountains";
    }

    @Override
    public void reset() {
        reset = false;
        up = true;
        curY = IMG.getHeight()/2;
        curX = 0;
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();

        JCheckBox cbSlopes = new JCheckBox("slopes",slopes);
        cbSlopes.addChangeListener(l->{slopes = cbSlopes.isSelected();});

        JCheckBox cbLoop = new JCheckBox("loop",loop);
        cbLoop.addChangeListener(l->{loop = cbLoop.isSelected();});
        JCheckBox cbReset = new JCheckBox("reset at loop",resetAtLoop);
        cbReset.addChangeListener(l->{resetAtLoop = cbReset.isSelected();});

        JCheckBox cbInstant = new JCheckBox("instant",instant);
        cbInstant.addChangeListener(l->{instant = cbInstant.isSelected();});

        JLabel lblX = new JLabel("step x: "+stepX);
        JSlider slX = new JSlider(1,100,stepX);
        slX.addChangeListener(l->{stepX = slX.getValue(); lblX.setText("step x: "+stepX);});

        JLabel lblY = new JLabel("step y: "+stepY);
        JSlider slY = new JSlider(1,100,stepY);
        slY.addChangeListener(l->{stepY = slY.getValue(); lblY.setText("step y: "+stepY);});

        list.add(cbSlopes);
        list.add(cbLoop);
        list.add(cbReset);
        list.add(lblX);
        list.add(slX);
        list.add(lblY);
        list.add(slY);
        list.add(cbInstant);
        return list;
    }

}
