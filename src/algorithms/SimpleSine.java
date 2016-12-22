package algorithms;

import gui.Plotter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;
import java.util.function.Function;
import image.Color;

public class SimpleSine extends Algorithm{

    private boolean plotted;
    private int a,b;

    public SimpleSine(){
        plotted = false;
    }

    @Override
    public String toString() {
        return "Simple sin(x)";
    }

    @Override
    public void step() {
        if(!plotted) {
            init();
            Plotter.plot(IMG, new Function<Double, Double>() {
                @Override
                public Double apply(Double x) {
                    return (a/10.0) * Math.sin((b/1000.0) * x);
                }
            }, IMG.getWidth() / 2, IMG.getHeight() / 2);
            plotted = true;
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();

        JLabel funcLbl = new JLabel((a/10.0)+"*sin(x*"+(b/1000.0)+")");

        JSlider aSlider = new JSlider(0,IMG.getHeight()*4, IMG.getHeight()/8);
        JSlider bSlider = new JSlider(0,2000,10);
        aSlider.addChangeListener(l->{
            this.a = aSlider.getValue();
            plotted = false;
            funcLbl.setText((a/10.0)+"*sin(x*"+(b/1000.0)+")");
        });
        bSlider.addChangeListener(l -> {
            this.b = bSlider.getValue();
            plotted = false;
            funcLbl.setText((a/10.0)+"*sin(x*"+(b/1000.0)+")");
        });

        list.add(aSlider);
        list.add(bSlider);
        list.add(funcLbl);
        return list;
    }
}
