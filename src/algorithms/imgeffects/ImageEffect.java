package algorithms.imgeffects;

import algorithms.Algorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class provides a framework for algorithms which operate on images given by the user. These algorithms mainly consist of image processing algorithms.
 */
public abstract class ImageEffect extends Algorithm {



    /**
     * The original image to work on. Most image effects don't change this image. This image isn't drawn in the GUI, IMG of the Algorithm class is still used for that.
     * Per default the image "test.jpg" is loaded. (might be changed)
     */
    protected static BufferedImage original = new BufferedImage(IMG.getWidth(),IMG.getHeight(),BufferedImage.TYPE_INT_ARGB);
    /**
     * A mask that can be used by image effects. This mask might not be loaded from a file.
     * At the start, this image is empty. It needs to be loaded by the user first.
     */
    protected static BufferedImage mask = new BufferedImage(IMG.getWidth(),IMG.getHeight(),BufferedImage.TYPE_INT_ARGB);
    /**
     * Minimum width/height of original and IMG. Can be used to safely access pixels of both the IMG and original without going out of bounds.
     */
    protected static int width, height;

    //GUI elements for the options frame
    private static TextField locationOrg, locationMask;
    private static JButton btnLoadOrg, btnLoadMask;
    private static JLabel lbl;
    private static JCheckBox box;

    /**
     * Loads an image from the path given in the location text fields. Type 0 means loading original, any other type means loading the mask.
     * @param type if 0 the original is loaded, else the mask
     */
    protected static void loadImage(int type){
        try {
            if(type == 0){
                original = ImageIO.read(new File(locationOrg.getText()));
                lbl.setText("Loaded image!");
                width = Math.min(IMG.getWidth(), original.getWidth());
                height = Math.min(IMG.getHeight(), original.getHeight());
            }else{
                mask = ImageIO.read(new File(locationMask.getText()));
                if(mask.getWidth() != width || mask.getHeight() != height){
                    lbl.setText("Warning: Mask size wrong!");
                }else{
                    lbl.setText("Loaded mask!");
                }
            }
        } catch (IOException e) {
            lbl.setText("Error: "+e.getMessage());
            width = IMG.getWidth();
            height = IMG.getHeight();
        }
    }

    //This part initializes all option-elements for image effects. It also loads the original image.
    static {
        Graphics g = mask.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());

        locationOrg = new TextField("test.jpg");
        btnLoadOrg = new JButton("Load image");
        btnLoadOrg.addActionListener(e -> loadImage(0));
        locationMask = new TextField("mask.jpg");
        btnLoadMask = new JButton("Load mask");
        btnLoadMask.addActionListener(e -> loadImage(1));

        lbl = new JLabel("");
        box = new JCheckBox("Ignore img size");
        box.addActionListener(e -> {
            if (box.isSelected()) {
                width = IMG.getWidth();
                height = IMG.getHeight();
            } else {
                width = Math.min(IMG.getWidth(), original.getWidth());
                height = Math.min(IMG.getHeight(), original.getHeight());
            }
        });

        loadImage(0);
        //currently only loading original image, not mask
        //loadImage(1);
    }

    @Override
    public String toString() {
        return "Unknown image effect";
    }

    @Override
    public void reset() {
        Graphics g = IMG.getGraphics();
        g.drawImage(original, 0, 0, null);
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new LinkedList<Component>();
        list.add(locationOrg);
        list.add(btnLoadOrg);
        list.add(locationMask);
        list.add(btnLoadMask);
        list.add(lbl);
        list.add(box);
        return list;
    }
}
