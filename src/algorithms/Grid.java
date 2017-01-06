package algorithms;

import option.OptionList;

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
    public OptionList getOptionList(){
        return s.getOptionList();
    }


    @Override
    public void reset() {

    }
}
