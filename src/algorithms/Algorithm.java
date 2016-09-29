package algorithms;

import algorithms.imgeffects.*;
import algorithms.randomizer.*;
import gui.MainFrame;

import java.util.List;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static algorithms.imgeffects.ImageEffect.initImageEffect;

/**
 * The base structure for algorithms.
 */
public abstract class Algorithm {

    /**
     * The image in which algorithms should draw.
     */
    public static BufferedImage IMG = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

    //these array are used to make the creation of the algorithms easier
    private static Randomizer[] randAlg;
    private static ImageEffect[] imgAlg;
    private static Algorithm[] alg;

    /**
     * Creates all algorithms. In the future this might get changed, algorithms should be created dynamically.
     * This method functions as a register of all algorithms. New algorithms need to be registered here, or else they won't be displayed in the GUI.
     */
    private static void initAlgorithms(){
        initImageEffect();

        //Adding a new algorithm: When adding a randomizer (/image effect/normal algorithm), increase R (/I/N) by 1,
        //create a new object of the algorithm and add it at the end of randAlg (/imgAlg/alg).
        //randAlg and imgAlg are both copied into alg (see for-loops below), changes to these arrays need to be done before that

        int R = 19;
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
        randAlg[15] = new RandomPath();
        randAlg[16] = new RandomPositions();
        randAlg[17] = new Voronoi();
        randAlg[18] = new Mountains();
        int I = 14;
        imgAlg = new ImageEffect[I];
        imgAlg[0] = new Pixelate();
        imgAlg[1] = new ImageBlur();
        imgAlg[2] = new ImageInvert();
        imgAlg[3] = new ColorSwap();
        imgAlg[4] = new SineEffect();
        imgAlg[5] = new ExponentialEffect();
        imgAlg[6] = new LogEffect();
        imgAlg[7] = new BlackPixels();
        imgAlg[8] = new Grey();
        imgAlg[9] = new Rectify();
        imgAlg[10] = new RandomColor();
        imgAlg[11] = new TransitionMask();
        imgAlg[12] = new ImageTransition();
        imgAlg[13] = new SineWiggle();
        int N = R+I+19;
        alg = new Algorithm[N];
        for(int i=0; i<R; i++) alg[i] = randAlg[i];
        for(int i=0; i<I; i++) alg[R+i] = imgAlg[i];
        R += I;
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
        alg[R+10] = new Polar();
        alg[R+11] = new EndlessCircles();
        alg[R+12] = new Vectorfield();
        alg[R+13] = new Fractal();
        alg[R+14] = new FillAnimation();
        alg[R+15] = new YouTubeLoad();
        alg[R+16] = new BallAndWind();
        alg[R+17] = new CoordinateColor();
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

    /**
     * Returns the name of the algorithm. Used to display algorithms for selection in the GUI.
     * @return name of the algorithm
     */
    public abstract String toString();

    /**
     * Called in every animation frame.
     * @param g the graphics object of the image in which to draw
     */
    public abstract void step(Graphics g);

    /**
     * Called when the users presses reset (amongst others, see init). This should reset the current progress in the algorithm/animation.
     * Initializing variables etc. should still be done in the constructor of the algorithm. It's not guaranteed that reset will be called at least once before calling the step-function.
     */
    public abstract void reset();

    /**
     * Returns a list of GUI-elements to be displayed in the options menu.
     * @return the list of GUI-elements
     */
    public List<Component> getOptionList(){
        return new LinkedList<>();
    }

    /**
     * Called when the users presses resets. This empties the image (to a blank white image) and calls reset.
     */
    public void init(){
        emptyIMG();
        reset();
    }

    /**
     * Called by the main loop to initiate steps.
     */
    public void step(){
        Graphics g = IMG.getGraphics();
        step(g);
    }

    /**
     * Starts the animation. Can be used by algorithms to pause/play the animation.
     */
    protected void start(){
        MainFrame.MF.startAnimation();
    }

    /**
     * Stops the animation. Can be used by algorithms to pause/play the animation.
     */
    protected void stop(){
        MainFrame.MF.stopAnimation();
    }

    /**
     * Can be used by algorithms to check whether the given points is within the given range.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width upper limit of x
     * @param height upper limit of y
     * @return true if (x,y) is within [0,width]X[0,height]
     */
    protected static boolean inBounds(int x, int y, int width, int height){
        return (x>=0 && x<width && y>=0 && y<height);
    }

    /**
     * Empties the image in the sense that it becomes a blank white image.
     */
    public void emptyIMG(){
        Graphics g = IMG.createGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
    }

}
