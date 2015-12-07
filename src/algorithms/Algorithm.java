package algorithms;

import algorithms.randomizer.*;

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
        int N = 5;
        Algorithm[] alg = new Algorithm[N];
        alg[0] = new RandomPixels();
        alg[1] = new RandomConnectedRifts();
        alg[2] = new RandomRifts();
        alg[3] = new RandomPixelFog();

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
