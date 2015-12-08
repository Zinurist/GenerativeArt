package algorithms;

import algorithms.randomizer.RandomPixelBlocks;
import algorithms.randomizer.Randomizer;

import java.awt.*;
import java.util.Random;

public class ShadowPixelBlocks extends Algorithm {

    private Squares sq;
    private RandomPixelBlocks bs, blocks;

    public ShadowPixelBlocks(){
        super();
        sq = new Squares();
        bs = new RandomPixelBlocks();
        blocks = new RandomPixelBlocks();
    }

    @Override
    public String toString() {
        return "Shadow pixel blocks";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        g.translate(-50, -30);

        Randomizer.color = true;

        long seed = System.currentTimeMillis();
        Randomizer.r = new Random(seed);
        bs.step(g,IMG.getWidth(),IMG.getHeight());

        sq.step();

        g = IMG.createGraphics();

        Randomizer.r = new Random(seed);
        blocks.step(g, IMG.getWidth(), IMG.getHeight());
    }

    @Override
    public void reset() {

    }
}
