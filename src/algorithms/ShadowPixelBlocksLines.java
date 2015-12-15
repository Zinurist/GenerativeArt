package algorithms;

import algorithms.randomizer.RandomPixelBlocks;
import algorithms.randomizer.Randomizer;

import java.awt.*;
import java.util.Random;

public class ShadowPixelBlocksLines extends Algorithm {

    private SimpleLines sl;
    private RandomPixelBlocks bs, blocks;

    public ShadowPixelBlocksLines(){
        super();
        sl = new SimpleLines();
        bs = new RandomPixelBlocks();
        blocks = new RandomPixelBlocks();
    }

    @Override
    public String toString() {
        return "Shadow pixel blocks lines";
    }

    @Override
    public void step() {
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        g.translate(-30, -10);

        Randomizer.color = true;

        long seed = System.currentTimeMillis();
        Randomizer.r = new Random(seed);
        bs.step(g,IMG.getWidth(),IMG.getHeight());

        sl.step();

        g = IMG.createGraphics();

        Randomizer.r = new Random(seed);
        blocks.step(g, IMG.getWidth(), IMG.getHeight());
    }

    @Override
    public void reset() {

    }
}
