package algorithms;

import algorithms.randomizer.RandomPixels;
import algorithms.randomizer.RandomRifts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The base structure for a algorithm used as drawing program.
 */
public abstract class Algorithm {

    public static BufferedImage IMG = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);

    /**
     * Creates an array of all algorithms.
     */
    public static Algorithm[] createAlgorithmsList(){
        Algorithm[] alg = new Algorithm[3];
        alg[0] = new EmptyAlgorithm();
        alg[1] = new RandomPixels();
        alg[2] = new RandomRifts();

        return alg;
    }

    protected Algorithm(){
        reset();
    }

    public abstract String toString();
    public abstract void step();
    public abstract void reset();

}
