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

    private static Randomizer[] randAlg;
    private static Algorithm[] alg;
    private static void initAlgorithms(){
        int R=15;
        randAlg = new Randomizer[R];
        randAlg[0] = new RandomPixels();
        randAlg[1] = new RandomPixelFog();
        randAlg[2] = new RandomFadingPixels();
        randAlg[3] = new RandomConcentratingPixels();
        randAlg[4] = new RandomRifts();
        randAlg[5] = new RandomConnectedRifts();
        randAlg[6] = new RandomThinRifts();
        randAlg[7] = new RandomPixelBlocks();
        randAlg[8] = new RandomLines();
        randAlg[9] = new RandomVerticalPreset();
        randAlg[10] = new RandomQuadrables();
        randAlg[11] = new RandomPolygon();
        randAlg[12] = new RandomStrings();
        randAlg[13] = new RandomCircles();
        randAlg[14] = new RandomHVLines(true, true);
        int N=R+11;
        alg = new Algorithm[N];
        for(int i=0; i<R; i++) alg[i] = randAlg[i];
        alg[R] = new ChaosAlgorithm();
        alg[R+1] = new AntsAlgorithm();
        alg[R+2] = new Squares();
        alg[R+3] = new SimpleLines();
        alg[R+4] = new Grid();
        alg[R+5] = new Shadow();
        alg[R+6] = new RandomSplitSquares();
        alg[R+7] = new RandomSplitSquaresBugged();
        alg[R+8] = new Ripples();
        alg[R+9] = new SimpleSine();
        alg[N-1] = new EmptyAlgorithm();
    }
    /**
     * Returns the array of all algorithms.
     */
    public static Algorithm[] getAlgorithmsList(){
        if(alg==null) initAlgorithms();
        return alg;
    }

    /**
     * Returns the array of all randomizers.
     */
    public static Randomizer[] getRandomizersList(){
        if(randAlg==null) initAlgorithms();
        return randAlg;
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
