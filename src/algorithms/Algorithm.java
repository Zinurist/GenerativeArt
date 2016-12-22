package algorithms;

import algorithms.imgeffects.*;
import algorithms.randomizer.*;
import gui.MainFrame;
import java.awt.Component;
import image.*;

import java.util.List;
import java.util.LinkedList;

/**
 * The base structure for algorithms.
 */
public abstract class Algorithm {

    /**
     * The image in which algorithms should draw.
     */
    public static Image IMG = new Image(1000, 1000);

    //these array are used to make the creation of the algorithms easier
    private static Randomizer[] randAlg;
    private static ImageEffect[] imgAlg;
    private static Algorithm[] alg;


    //Register of all algorithms
    static {
        //initImageEffect();

        //Adding a new algorithm: When adding a randomizer (/image effect/normal algorithm), increase R (/I/N) by 1,
        //create a new object of the algorithm and add it at the end of randAlg (/imgAlg/alg).
        //randAlg and imgAlg are both copied into alg (see for-loops below), changes to these arrays need to be done before that
        int counter = 0;
        int R = 21;
        randAlg = new Randomizer[R];
        randAlg[counter++] = new RandomPixels();
        randAlg[counter++] = new RandomPixelFog();
        randAlg[counter++] = new RandomFadingPixels();
        randAlg[counter++] = new RandomConcentratingPixels();
        randAlg[counter++] = new RandomRifts();
        randAlg[counter++] = new RandomConnectedRifts();
        randAlg[counter++] = new RandomThinRifts();
        randAlg[counter++] = new RandomPixelBlocks();
        randAlg[counter++] = new RandomLines();
        randAlg[counter++] = new RandomVerticalPreset();
        randAlg[counter++] = new RandomQuadrables();
        randAlg[counter++] = new RandomPolygon();
        randAlg[counter++] = new RandomStrings();
        randAlg[counter++] = new RandomCircles();
        randAlg[counter++] = new RandomHVLines(true, true);
        randAlg[counter++] = new RandomPath();
        randAlg[counter++] = new RandomPositions();
        randAlg[counter++] = new Voronoi();
        randAlg[counter++] = new Mountains();
        randAlg[counter++] = new RandomGraph();
        randAlg[counter++] = new RandomBigCircles();
        int I = 17;
        counter = 0;
        imgAlg = new ImageEffect[I];
        imgAlg[counter++] = new Pixelate();
        imgAlg[counter++] = new ImageFilter();
        imgAlg[counter++] = new ImageInvert();
        imgAlg[counter++] = new ColorSwap();
        imgAlg[counter++] = new SineEffect();
        imgAlg[counter++] = new ExponentialEffect();
        imgAlg[counter++] = new LogEffect();
        imgAlg[counter++] = new BlackPixels();
        imgAlg[counter++] = new Grey();
        imgAlg[counter++] = new Rectify();
        imgAlg[counter++] = new RandomColor();
        imgAlg[counter++] = new TransitionMask();
        imgAlg[counter++] = new ImageTransition();
        imgAlg[counter++] = new SineWiggle();
        imgAlg[counter++] = new SemiInvert();
        imgAlg[counter++] = new Cartoon();
        imgAlg[counter++] = new ColorFlip();
        int N = R+I+25;
        alg = new Algorithm[N];
        for(int i=0; i<R; i++) alg[i] = randAlg[i];
        for(int i=0; i<I; i++) alg[R+i] = imgAlg[i];
        counter = R+I;
        alg[counter++] = new ChaosAlgorithm();
        alg[counter++] = new AntsAlgorithm();
        alg[counter++] = new Squares();
        alg[counter++] = new SimpleLines();
        alg[counter++] = new Grid();
        alg[counter++] = new Shadow();
        alg[counter++] = new RandomSplitSquares();
        alg[counter++] = new RandomSplitSquaresBugged();
        alg[counter++] = new Ripples();
        alg[counter++] = new SimpleSine();
        alg[counter++] = new Polar();
        alg[counter++] = new EndlessCircles();
        alg[counter++] = new Vectorfield();
        alg[counter++] = new Fractal();
        alg[counter++] = new FillAnimation();
        alg[counter++] = new YouTubeLoad();
        alg[counter++] = new BallAndWind();
        alg[counter++] = new CoordinateColor();
        alg[counter++] = new Net();
        alg[counter++] = new Circles();
        alg[counter++] = new Eyes();
        alg[counter++] = new Stars();
        alg[counter++] = new Balls();
        alg[counter++] = new Search();
        alg[N-1] = new EmptyAlgorithm();
    }
    /**
     * Returns the array of all algorithms.
     */
    public static Algorithm[] getAlgorithmsList(){
        return alg;
    }

    /**
     * Returns the array of all randomizers.
     */
    public static Randomizer[] getRandomizersList(){
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
