package algorithms.imgeffects;

import algorithms.Algorithm;
import java.io.IOException;
import image.Image;
import image.Color;
import option.OptionList;

/**
 * This class provides a framework for algorithms which operate on images given by the user. These algorithms mainly consist of image processing algorithms.
 */
public abstract class ImageEffect extends Algorithm {



    /**
     * The original image to work on. Most image effects don't change this image. This image isn't drawn in the GUI, IMG of the Algorithm class is still used for that.
     * This image can be used, if the effect needs the unmodified data of the original image.
     * Per default the image "test.jpg" is loaded. (might be changed)
     */
    protected static Image original = new Image(IMG.getWidth(),IMG.getHeight());
    /**
     * A mask that can be used by image effects. This mask might not be loaded from a file.
     * At the start, this image is empty. It needs to be loaded by the user first.
     */
    protected static Image mask = new Image(IMG.getWidth(),IMG.getHeight());
    /**
     * Minimum width/height of original and IMG. Can be used to safely access pixels of both the IMG and original without going out of bounds.
     */
    protected static int width, height;

    private static String locationOrg, locationMask;
    private static boolean ignoreImageSize;

    /**
     * Loads an image from the path given in the location text fields. Type 0 means loading original, any other type means loading the mask.
     * @param type if 0 the original is loaded, else the mask
     */
    protected static void loadImage(int type){
        try {
            if(type == 0){
                original = new Image(locationOrg);
                //OptionList.log("Loaded image!");
                width = Math.min(IMG.getWidth(), original.getWidth());
                height = Math.min(IMG.getHeight(), original.getHeight());
            }else{
                mask = new Image(locationMask);
                if(mask.getWidth() != width || mask.getHeight() != height){
                    //OptionList.log("Warning: Mask size wrong!");
                }else{
                    //OptionList.log("Loaded mask!");
                }
            }
        } catch (IOException e) {
            OptionList.displayError(e.getMessage());
            width = IMG.getWidth();
            height = IMG.getHeight();
        }
    }

    //This part initializes all option-elements for image effects. It also loads the original image.
    static {
        IMG.setColor(Color.WHITE);
        IMG.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());

        locationOrg = "test.jpg";
        locationMask = "mask.jpg";

        loadImage(0);
        //currently only loading original image, not mask
        //loadImage(1);
    }

    @Override
    public String toString() {
        return "Unknown image effect";
    }

    @Override
    public void init(){
        emptyIMG();
        IMG.drawImage(original, 0, 0);
        reset();
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = new OptionList();
        list.addOption("Load image", locationOrg, val -> {locationOrg = val; loadImage(0);});
        list.addOption("Load mask", locationMask, val -> {
            locationMask = val;
            loadImage(1);
        });
        list.addOption("Ignore img size", ignoreImageSize, val -> {
            ignoreImageSize = val;
            if(ignoreImageSize) {
                width = IMG.getWidth();
                height = IMG.getHeight();
            } else {
                width = Math.min(IMG.getWidth(), original.getWidth());
                height = Math.min(IMG.getHeight(), original.getHeight());
            }
        });
        return list;
    }
}
