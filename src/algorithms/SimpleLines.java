package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import image.Color;

public class SimpleLines extends Algorithm {

    private boolean vertical, horizontal;

    public SimpleLines(){
        this(false, true);
    }

    public SimpleLines(boolean vertical, boolean horizontal){
        super();
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public String toString() {
        return "Simple Lines";
    }

    @Override
    public void step() {
        IMG.setColor(Color.BLACK);
        if(vertical){
            for(int i=0; i<IMG.getWidth();){
                IMG.fillRect(i,0,5,IMG.getHeight());
                i+=10;
            }
        }
        if(horizontal){
            for(int i=0; i<IMG.getHeight();){
                IMG.fillRect(0,i,IMG.getWidth(),5);
                i+=10;
            }
        }

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();
        JCheckBox verticalBox = new JCheckBox("vertical lines");
        verticalBox.setSelected(vertical);
        verticalBox.addItemListener(l->this.vertical = verticalBox.isSelected());
        JCheckBox horizontalBox = new JCheckBox("horizontal lines");
        horizontalBox.setSelected(horizontal);
        horizontalBox.addItemListener(l->this.horizontal = horizontalBox.isSelected());

        list.add(verticalBox);
        list.add(horizontalBox);
        return list;
    }

    @Override
    public void reset() {

    }
}
