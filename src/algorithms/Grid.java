package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Grid extends Algorithm {

    private SimpleLines s;

    public Grid(){
        super();
        s = new SimpleLines(true,true);
    }

    @Override
    public String toString() {
        return "Grid";
    }

    @Override
    public void step() {
        s.step();
    }


    @Override
    public java.util.List<Component> getOptionList(){
        return s.getOptionList();
    }


    @Override
    public void reset() {

    }
}
