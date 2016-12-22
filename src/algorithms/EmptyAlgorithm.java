package algorithms;


import java.awt.Component;
import image.Color;

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
        init();
        //IMG.setColor(Color.BLACK);
        //IMG.drawLine(0,0,x,y);
    }

    @Override
    public void reset() {
        //reset variables
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new java.util.LinkedList<>();
        //add components here
        //JCheckBox cbBool = new JCheckBox("bool", bool);
        //cbBool.addChangeListener(l->{ bool = cbBool.isSelected(); });
        //list.add(cbBool);
        return list;
    }
}
