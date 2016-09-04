package algorithms.randomizer;

import algorithms.*;

import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public abstract class Randomizer extends Algorithm {

    protected static boolean[][] bools = new boolean[IMG.getHeight()][IMG.getWidth()];
    private static boolean changed = true;
    public static boolean color = false;
    private static boolean empty = false;
    public static Random r = new Random();

    @Override
    public void step(Graphics g){
        changed = true;
        if(empty) {
            init();
        }

        g.setColor(Color.BLACK);
        step(g, IMG.getWidth(), IMG.getHeight());
    }

    public abstract void step(Graphics g, int width, int height);

    @Override
    public List<Component> getOptionList(){
        JCheckBox colorBox = new JCheckBox("Color mode");
        colorBox.setSelected(color);
        colorBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                color = colorBox.isSelected();
            }
        });


        JCheckBox emptyBox = new JCheckBox("Empty");
        emptyBox.setSelected(empty);
        emptyBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                empty = emptyBox.isSelected();
            }
        });

        List<Component> list = new LinkedList<Component>();
        list.add(colorBox);
        list.add(emptyBox);
        return list;
    }

    protected static void resetBools() {
        if(changed) {
            bools = new boolean[IMG.getHeight()][IMG.getWidth()];
            changed = false;
        }
    }

    protected static Color randomColor(){
        return new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
    }
}
