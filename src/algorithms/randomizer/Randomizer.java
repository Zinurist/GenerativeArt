package algorithms.randomizer;

import algorithms.*;

import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * An abstraction for algorithms which are mainly based on drawing randomly in the image.
 */
public abstract class Randomizer extends Algorithm {

    /**
     * A boolean array. Some randomizers use this to detect whether certain pixels were already drawn or selected by the algorithm.
     */
    protected static boolean[][] bools = new boolean[IMG.getHeight()][IMG.getWidth()];
    /**
     * This is only used to prevent the bools array to be recreated too often. E.g. if the user clicks resets multiple times, the bools array would be reallocated each time, even though it hasn't been edited.
     * TODO This might need to be changed if the user changes the size of the image!!
     */
    private static boolean changed = true;
    /**
     * If the color option is set to true, then randomizers should draw images with colors (contrary to black & white images).
     */
    public static boolean color = false;
    /**
     * If the empty option is set to true, then the image get emptied at the start of every step.
     */
    private static boolean empty = false;
    /**
     * The random object to be used by randomizers.
     */
    public static Random r = new Random();

    /**
     * If set to true, then the options list won't contain the empty option and the value of empty will be ignored.
     * Useful for certain algorithms like RandomPath.
     */
    private final boolean emptiable;

    /**
     * Default constructor for en emptiable randomizer.
     */
    protected Randomizer(){
        emptiable = true;
    }

    /**
     * Constructor that allows to set emptiable
     * @param emptiable true, if the randomizer should be able to by emptied
     */
    protected Randomizer(boolean emptiable){
        this.emptiable = emptiable;
    }

    /**
     * The step function now empties the image, if the option for this is selected.
     * @param g the graphics object of the image in which to draw
     */
    @Override
    public void step(Graphics g){
        changed = true;
        if(emptiable && empty) {
            init();
        }

        g.setColor(Color.BLACK);
        step(g, IMG.getWidth(), IMG.getHeight());
    }

    /**
     * The step function to be implemented by randomizer algorithms. Width and height are here given, since many randomizers need these and the code therefore gets more readable.
     * @param g the graphics object of the image in which to draw
     * @param width width of the image
     * @param height height of the image
     */
    public abstract void step(Graphics g, int width, int height);

    /**
     * The option list of randomizers already have default options. Subclasses should call this function and extend the list by own/new options, instead of creating a new list.
     * @return
     */
    @Override
    public List<Component> getOptionList(){
        List<Component> list = new LinkedList<Component>();
        JCheckBox colorBox = new JCheckBox("Color mode");
        colorBox.setSelected(color);
        colorBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                color = colorBox.isSelected();
            }
        });
        list.add(colorBox);

        if(emptiable) {
            JCheckBox emptyBox = new JCheckBox("Empty");
            emptyBox.setSelected(empty);
            emptyBox.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    empty = emptyBox.isSelected();
                }
            });
            list.add(emptyBox);
        }

        return list;
    }

    /**
     * Resets the bools to false.
     */
    protected static void resetBools() {
        if(changed) {
            bools = new boolean[IMG.getHeight()][IMG.getWidth()];
            changed = false;
        }
    }

    /**
     * Creates a random color. If the color-option is set to false, a random grey color is created.
     * @return a random color
     */
    public static Color randomColor(){
        if(color) {
            return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }else{
            int c = r.nextInt(255);
            return new Color(c,c,c);
        }
    }
}
