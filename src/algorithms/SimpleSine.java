package algorithms;

import gui.Plotter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;
import java.util.function.Function;

public class SimpleSine extends Algorithm{

    private boolean plotted;
    private JSlider a, b;

    public SimpleSine(){
        plotted = false;
        a = new JSlider(0,IMG.getHeight()*2, IMG.getHeight()/8);
        b = new JSlider(0,200,5);
        a.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                plotted = false;
            }
        });
        b.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                plotted = false;
            }
        });
    }

    @Override
    public String toString() {
        return "Simple sin(x)";
    }

    @Override
    public void step(Graphics g) {
        if(!plotted) {
            init();
            Plotter.plot(IMG, new Function<Double, Double>() {
                @Override
                public Double apply(Double x) {
                    return a.getValue() * Math.sin((b.getValue()/100.0) * x);
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
        list.add(a);
        list.add(b);
        list.add(new JLabel("a*sin(x*b/100)"));
        return list;
    }
}
