package algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The base structure for a algorithm used as drawing program.
 */
public abstract class Algorithm {

    /**
     * Creates an array of all algorithms.
     */
    public static Algorithm[] createAlgorithmsList(){
        Algorithm[] alg = new Algorithm[5];
        alg[0] = new EmptyAlgorithm();
        alg[1] = new EmptyAlgorithm();

        return alg;
    }

    public abstract String toString();
    public abstract void step(BufferedImage img);

}
