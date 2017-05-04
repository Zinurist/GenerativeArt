package algorithms;

import option.OptionList;

/**
 * Empty algorithm that does nothing. Serves as placeholder and template.
 */
public class EmptyAlgorithm extends Algorithm{

    //variables

    public EmptyAlgorithm() {
        //constructor: initialize variables here
        reset();
    }

    @Override
    public String toString() {
        return "Empty";
    }

    @Override
    public void step() {
        emptyIMG();
        //IMG.setColor(Color.BLACK);
        //IMG.drawLine(0,0,x,y);
    }

    @Override
    public void reset() {
        //reset variables
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        //add options here
        return list;
    }
}
