package algorithms;

import algorithms.randomizer.*;

import java.util.List;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * The base structure for a algorithm used as drawing program.
 */
public abstract class Algorithm {

    public static BufferedImage IMG = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

    /**
     * Creates an array of all algorithms.
     */
    public static Algorithm[] createAlgorithmsList(){
        int N = 26;
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
        alg[14] = new ChaosAlgorithm();
        alg[15] = new AntsAlgorithm();
        alg[16] = new RandomHLines();
        alg[17] = new RandomVLines();
        alg[18] = new RandomHVLines();
        alg[19] = new Squares();
        alg[20] = new SimpleHLines();
        alg[21] = new ShadowRifts();
        alg[22] = new ShadowRiftsLines();
        alg[23] = new ShadowPixelBlocks();
        alg[24] = new ShadowPixelBlocksLines();

        alg[N-1] = new EmptyAlgorithm();
        return alg;
    }

    protected Algorithm(){
        init();
    }

    public abstract String toString();
    public abstract void step();
    public abstract void reset();

    public List<Component> getOptionList(){
        return new LinkedList<Component>();
    }

    public void init(){
        Graphics g = IMG.createGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        reset();
    }

}
