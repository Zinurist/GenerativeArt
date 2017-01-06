package algorithms;

import gui.Plotter;
import option.OptionList;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.function.Function;

public class SimpleSine extends Algorithm{

    private boolean plotted;
    private double a,b;

    public SimpleSine(){
        a = IMG.getHeight()/8.;
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
                    return a * Math.sin(b * x);
                }
            }, IMG.getWidth() / 2, IMG.getHeight() / 2);
            plotted = true;
        }
    }

    @Override
    public void reset() {}

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("a", a, 0., IMG.getHeight()*4/10., 1., val -> {a = val; plotted = false;});
        list.addOption("b", b, 0., 1., 0.001, val -> {b = val; plotted = false;});
        list.addInfo("a * sin(b * x)");
        return list;
    }
}
