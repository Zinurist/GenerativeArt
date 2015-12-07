package algorithms;


import java.awt.image.BufferedImage;

public class EmptyAlgorithm extends Algorithm{

    public EmptyAlgorithm(){

    }

    @Override
    public String toString() {
        return "Empty";
    }

    @Override
    public void step(BufferedImage img) {
        //nuffin
    }
}
