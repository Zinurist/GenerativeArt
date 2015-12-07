package algorithms;


import java.awt.image.BufferedImage;

public class EmptyAlgorithm extends Algorithm{

    protected EmptyAlgorithm() {
    }

    @Override
    public String toString() {
        return "Empty";
    }

    @Override
    public void step() {
        //nuffin
    }

    @Override
    public void reset() {
        //nuffin
    }
}
