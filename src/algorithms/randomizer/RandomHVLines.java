package algorithms.randomizer;

import image.Color;
import option.OptionList;

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
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("vertical lines", vertical, val -> vertical = val);
        list.addOption("horizontal lines", horizontal, val -> horizontal = val);
        return list;
    }

    @Override
    public void reset() {
        //nuffin
    }
}
