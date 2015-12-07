package algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The base structure for a algorithm used as drawing program.
 */
public abstract class Algorithm {


    public abstract String toString();
    public abstract void step(BufferedImage img);

}
