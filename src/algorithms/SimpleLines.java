package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import image.Color;

public class SimpleLines extends Algorithm {

    private JCheckBox verticalBox,horizontalBox;

    public SimpleLines(){
        this(false, true);
    }

    public SimpleLines(boolean vertical, boolean horizontal){
        super();
        verticalBox = new JCheckBox("vertical lines");
        verticalBox.setSelected(vertical);
        horizontalBox = new JCheckBox("horizontal lines");
        horizontalBox.setSelected(horizontal);
    }

    @Override
    public String toString() {
        return "Simple Lines";
    }

    @Override
    public void step() {
        IMG.setColor(Color.BLACK);
        if(verticalBox.isSelected()){
            for(int i=0; i<IMG.getWidth();){
                IMG.fillRect(i,0,5,IMG.getHeight());
                i+=10;
            }
        }
        if(horizontalBox.isSelected()){
            for(int i=0; i<IMG.getHeight();){
                IMG.fillRect(0,i,IMG.getWidth(),5);
                i+=10;
            }
        }

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();
        list.add(verticalBox);
        list.add(horizontalBox);
        return list;
    }

    @Override
    public void reset() {

    }
}
