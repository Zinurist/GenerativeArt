package algorithms.imgeffects;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import image.Color;

public class SineWiggle extends ImageEffect {

    private int a,b; //a*sin(b*x)
    private int t;//percent
    private boolean loop;

    public SineWiggle(){
        super();
        reset();
        a = 0;
        b = 10;
    }

    @Override
    public String toString(){
        return "Sine wiggle";
    }

    @Override
    public void step() {
        emptyIMG();

        Color c;
        double ad = a/10.0;
        double bd = b/(double)IMG.getHeight();
        double progress = Math.PI*2*(t/100.0);
        int offset;
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                c = new Color(original.getRGB(x, y));
                IMG.setColor(c);

                offset = (int)(ad*Math.sin(y*bd + progress)+0.5);

                IMG.drawLine(x+offset, y, x+offset, y);
            }
        }

        t++;
        if(t>99){
            if(loop){
                stop();
            }
            t = 0;
        }
    }

    @Override
    public void reset(){
        t = 0;
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();

        JLabel lblA = new JLabel("Amplitude a: "+a);
        JLabel lblB = new JLabel("Factor b: "+b);
        JSlider sa = new JSlider(0,IMG.getWidth()*2, a);
        JSlider sb = new JSlider(0,200,b);
        sa.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                a = sa.getValue();
                lblA.setText("Amplitude a: "+a);
            }
        });
        sb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                b = sb.getValue();
                lblB.setText("Factor b: " + b);
            }
        });

        JCheckBox cbLoop = new JCheckBox("loop perfectly",loop);
        cbLoop.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                loop = cbLoop.isSelected();
            }
        });

        list.add(lblA);
        list.add(sa);
        list.add(lblB);
        list.add(sb);
        list.add(new JLabel("a/10 * sin( x * b/height)"));
        list.add(cbLoop);
        return list;
    }

}
