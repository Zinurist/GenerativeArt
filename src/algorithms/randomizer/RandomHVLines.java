package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class RandomHVLines extends Randomizer {

    private boolean vertical, horizontal;


    public RandomHVLines(boolean vertical, boolean horizontal){
        super();
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public void step(int width, int height) {
        if(horizontal) {
            for (int row = 0; row < height; row++) {
                if (color) {
                    IMG.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                }
                if (r.nextDouble() > 0.7) {
                    IMG.drawLine(0, row, width, row);
                }
            }
        }

        if(vertical) {
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

        JCheckBox verticalBox = new JCheckBox("vertical lines");
        verticalBox.setSelected(vertical);
        verticalBox.addItemListener(l->vertical = verticalBox.isSelected());
        JCheckBox horizontalBox = new JCheckBox("horizontal lines");
        horizontalBox.setSelected(horizontal);
        horizontalBox.addItemListener(l->horizontal = horizontalBox.isSelected());

        list.add(verticalBox);
        list.add(horizontalBox);
        return list;
    }

    @Override
    public void reset() {
        //nuffin
    }
}
