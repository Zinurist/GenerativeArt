package algorithms;

import algorithms.randomizer.RandomRifts;
import algorithms.randomizer.Randomizer;

import java.awt.*;
import java.util.Random;

public class ShadowRiftsLines extends Algorithm {

    private SimpleLines sl;
    private RandomRifts rs, rifts;

    public ShadowRiftsLines(){
        super();
        sl = new SimpleLines();
        rs = new RandomRifts();
        rifts = new RandomRifts();
    }

    @Override
    public String toString() {
        return "Shadow rifts lines";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        g.translate(-100, 0);

        Randomizer.color = true;

        long seed = System.currentTimeMillis();
        Randomizer.r = new Random(seed);
        rs.step(g,IMG.getWidth(),IMG.getHeight());

        sl.step();

        g = IMG.createGraphics();

        Randomizer.r = new Random(seed);
        rifts.step(g, IMG.getWidth(), IMG.getHeight());
    }

    @Override
    public void reset() {

    }
}
