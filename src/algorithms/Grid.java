package algorithms;

import java.awt.*;

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
    public void step(Graphics g) {
        s.step(g);
    }


    @Override
    public java.util.List<Component> getOptionList(){
        return s.getOptionList();
    }


    @Override
    public void reset() {

    }
}
