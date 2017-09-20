package algorithms;

import algorithms.imgeffects.*;
import algorithms.randomizer.*;
import gui.MainFrame;
import image.*;
import option.OptionList;

import java.util.Vector;

/**
 * The base structure for algorithms.
 */
public abstract class Algorithm {

    /**
     * The image in which algorithms should draw.
     */
    public static Image IMG = new Image(1000, 1000);

    //these lists are used to make the creation of the algorithms easier
    private static Vector<Randomizer> randAlg;
    private static Vector<ImageEffect> imgAlg;
    private static Vector<Algorithm> alg;


    //Register of all algorithms
    static {
        //initImageEffect();

        //Adding a new algorithm to the best fitting list.

        randAlg = new Vector<>();
        randAlg.add(new PixelCube());
        randAlg.add(new RandomPixels());
        randAlg.add(new RandomPixelFog());
        randAlg.add(new RandomFadingPixels());
        randAlg.add(new RandomConcentratingPixels());
        randAlg.add(new RandomRifts());
        randAlg.add(new RandomConnectedRifts());
        randAlg.add(new RandomThinRifts());
        randAlg.add(new RandomPixelBlocks());
        randAlg.add(new RandomLines());
        randAlg.add(new RandomVerticalPreset());
        randAlg.add(new RandomQuadrables());
        randAlg.add(new RandomPolygon());
        randAlg.add(new RandomStrings());
        randAlg.add(new RandomCircles());
        randAlg.add(new RandomHVLines(true, true));
        randAlg.add(new RandomPath());
        randAlg.add(new RandomPositions());
        randAlg.add(new Voronoi());
        randAlg.add(new Mountains());
        randAlg.add(new RandomGraph());
        randAlg.add(new RandomBigCircles());
        randAlg.add(new ChaosGame());

        imgAlg = new Vector<>();
        imgAlg.add(new Pixelate());
        imgAlg.add(new ImageFilter());
        imgAlg.add(new ImageInvert());
        imgAlg.add(new ColorSwap());
        imgAlg.add(new SineEffect());
        imgAlg.add(new ExponentialEffect());
        imgAlg.add(new LogEffect());
        imgAlg.add(new BlackPixels());
        imgAlg.add(new Grey());
        imgAlg.add(new Rectify());
        imgAlg.add(new RandomColor());
        imgAlg.add(new TransitionMask());
        imgAlg.add(new ImageTransition());
        imgAlg.add(new SineWiggle());
        imgAlg.add(new SemiInvert());
        imgAlg.add(new Cartoon());
        imgAlg.add(new ColorFlip());
        imgAlg.add(new Checkerboard());
        imgAlg.add(new Dithering());

        alg = new Vector<>();
        alg.addAll(randAlg);
        alg.addAll(imgAlg);
        alg.add(new ChaosAlgorithm());
        alg.add(new AntsAlgorithm());
        alg.add(new Squares());
        alg.add(new SimpleLines());
        alg.add(new Grid());
        alg.add(new Shadow());
        alg.add(new RandomSplitSquares());
        alg.add(new RandomSplitSquaresBugged());
        alg.add(new Ripples());
        alg.add(new SimpleSine());
        alg.add(new Polar());
        alg.add(new EndlessCircles());
        alg.add(new Vectorfield());
        alg.add(new Fractal());
        alg.add(new FillAnimation());
        alg.add(new YouTubeLoad());
        alg.add(new BallAndWind());
        alg.add(new CoordinateColor());
        alg.add(new ArithmeticPixel());
        alg.add(new ArithmeticPixelHSB());
        alg.add(new Net());
        alg.add(new Circles());
        alg.add(new Eyes());
        alg.add(new Stars());
        alg.add(new Balls());
        alg.add(new Search());
        alg.add(new SandPile());
        alg.add(new Cube());
        alg.add(new EmptyAlgorithm());
    }
    /**
     * Returns the array of all algorithms.
     */
    public static Vector<Algorithm> getAlgorithmsList(){
        return alg;
    }

    /**
     * Returns the array of all randomizers.
     */
    public static Vector<Randomizer> getRandomizersList(){
        return randAlg;
    }

    /**
     * Returns the name of the algorithm. Used to display algorithms for selection in the GUI.
     * @return name of the algorithm
     */
    public abstract String toString();

    /**
     * Called in every animation frame.
     */
    public abstract void step();

    /**
     * Called when the users presses reset (amongst others, see init). This should reset the current progress in the algorithm/animation.
     * Initializing variables etc. should still be done in the constructor of the algorithm. It's not guaranteed that reset will be called at least once before calling the step-function.
     */
    public abstract void reset();

    /**
     * Returns a list of GUI-elements to be displayed in the options menu.
     * @return the list of GUI-elements
     */
    public OptionList getOptionList(){
        return new OptionList();
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
    public void animate(){
        IMG.setColor(Color.BLACK);
        step();
        IMG.untranslate();
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
     * Returns true if the animation is currently running (and not paused).
     * @return true if animation is running
     */
    protected boolean isRunning(){
        return MainFrame.MF.isRunning();
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
        emptyIMG(new Color(255, 255, 255));
    }

    /**
     * Empties the image and fills it with the given color.
     */
    public void emptyIMG(Color c){
        IMG.setColor(c);
        IMG.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
    }

}
