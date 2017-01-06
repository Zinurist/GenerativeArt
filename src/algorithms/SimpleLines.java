package algorithms;

import image.Color;
import option.OptionList;

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
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("vertical lines", vertical, val -> vertical = val);
        list.addOption("horizontal lines", horizontal, val -> horizontal = val);
        return list;
    }

    @Override
    public void reset() {

    }
}
