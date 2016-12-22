package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class RandomHVLines extends Randomizer {


    private JCheckBox verticalBox,horizontalBox;


    public RandomHVLines(boolean vertical, boolean horizontal){
        super();
        verticalBox = new JCheckBox("vertical lines");
        verticalBox.setSelected(vertical);
        horizontalBox = new JCheckBox("horizontal lines");
        horizontalBox.setSelected(horizontal);
    }

    @Override
    public void step(int width, int height) {
        if(horizontalBox.isSelected()) {
            for (int row = 0; row < height; row++) {
                if (color) {
                    IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                }
                if (r.nextDouble() > 0.7) {
                    IMG.drawLine(0, row, width, row);
                }
            }
        }

        if(verticalBox.isSelected()) {
            for (int column = 0; column < width; column++) {
                if (color) {
                    IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                }
                if (r.nextDouble() > 0.7) {
                    IMG.drawLine(column, 0, column, height);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "HVLines";
    }


    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(verticalBox);
        list.add(horizontalBox);
        return list;
    }

    @Override
    public void reset() {
        //nuffin
    }
}
