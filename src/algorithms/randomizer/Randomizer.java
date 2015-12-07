package algorithms.randomizer;

import algorithms.Algorithm;

import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public abstract class Randomizer extends Algorithm {

    protected boolean[][] bools;
    protected boolean color;
    protected Random r;
    //TODO options for color selection

    protected Randomizer(){
        r = new Random();
        color = false;
    }

    public void step(){
        init();
        Graphics g = IMG.createGraphics();
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

        List<Component> list = new LinkedList<Component>();
        list.add(colorBox);
        return list;
    }

    @Override
    public void reset() {
        bools = new boolean[IMG.getHeight()][IMG.getWidth()];
    }
}
