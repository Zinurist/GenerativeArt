package algorithms;

import algorithms.randomizer.RandomPixelBlocks;
import algorithms.randomizer.RandomRifts;
import algorithms.randomizer.Randomizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class Shadow extends Algorithm {

    private Algorithm divider;
    private Randomizer shadow, algo;
    private JSpinner xOffset, yOffset;
    JComboBox<Algorithm> dividerSelector;
    JComboBox<Algorithm> shadowSelector;
    JComboBox<Algorithm> algoSelector;

    public Shadow(){
        this(new Grid(), new RandomRifts(), new RandomRifts());
    }

    public Shadow(Algorithm div, Randomizer sh, Randomizer alg){
        super();
        this.divider = div;
        this.shadow = sh;
        this.algo = alg;

        xOffset = new JSpinner(new SpinnerNumberModel(-100,-1000,1000,10));
        yOffset = new JSpinner(new SpinnerNumberModel(0,-1000,1000,10));

        dividerSelector = new JComboBox<>(Algorithm.getAlgorithmsList());
        shadowSelector = new JComboBox<>(Randomizer.getRandomizersList());
        algoSelector = new JComboBox<>(Randomizer.getRandomizersList());

        dividerSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                divider = (Algorithm) dividerSelector.getSelectedItem();
            }
        });
        dividerSelector.setSelectedItem(divider);

        shadowSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shadow = (Randomizer) shadowSelector.getSelectedItem();
            }
        });
        shadowSelector.setSelectedItem(shadow);

        algoSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algo = (Randomizer) algoSelector.getSelectedItem();
            }
        });
        algoSelector.setSelectedItem(algo);
    }

    @Override
    public String toString() {
        return "Shadow";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        g.translate((Integer)xOffset.getValue(), (Integer)yOffset.getValue());

        Randomizer.color = true;

        long seed = System.currentTimeMillis();
        Randomizer.r = new Random(seed);
        shadow.step(g,IMG.getWidth(),IMG.getHeight());

        divider.step();

        g = IMG.createGraphics();

        Randomizer.r = new Random(seed);
        algo.step(g, IMG.getWidth(), IMG.getHeight());
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();

        list.add(new JLabel("Divider: "));
        list.add(dividerSelector);
        list.add(new JLabel("Shadow: "));
        list.add(shadowSelector);
        list.add(new JLabel("Algorithm: "));
        list.add(algoSelector);
        list.add(new JLabel("x offset: "));
        list.add(xOffset);
        list.add(new JLabel("y offset: "));
        list.add(yOffset);

        list.addAll(divider.getOptionList());
        list.addAll(shadow.getOptionList());
        list.addAll(algo.getOptionList());
        return list;
    }

    @Override
    public void reset() {

    }
}
