package algorithms;

import algorithms.randomizer.*;

import java.awt.image.BufferedImage;

/**
 * The base structure for a algorithm used as drawing program.
 */
public abstract class Algorithm {

    public static BufferedImage IMG = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

    /**
     * Creates an array of all algorithms.
     */
    public static Algorithm[] createAlgorithmsList(){
        int N = 15;
        Algorithm[] alg = new Algorithm[N];
        alg[0] = new RandomPixels();
        alg[1] = new RandomPixelFog();
        alg[2] = new RandomFadingPixels();
        alg[3] = new RandomConcentratingPixels();
        alg[4] = new RandomRifts();
        alg[5] = new RandomConnectedRifts();
        alg[6] = new RandomThinRifts();
        alg[7] = new RandomPixelBlocks();
        alg[8] = new RandomLines();
        alg[9] = new RandomVerticalPreset();
        alg[10] = new RandomQuadrables();
        alg[11] = new RandomPolygon();
        alg[12] = new RandomStrings();
        alg[13] = new RandomCircles();

        alg[N-1] = new EmptyAlgorithm();
        return alg;
    }

    protected Algorithm(){
        reset();
    }

    public abstract String toString();
    public abstract void step();
    public abstract void reset();

}
