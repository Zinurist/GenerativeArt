package algorithms;


import java.awt.*;

public class EmptyAlgorithm extends Algorithm{

    protected EmptyAlgorithm() {
    }

    @Override
    public String toString() {
        return "Empty";
    }

    @Override
    public void step(Graphics g) {
        init();
    }

    @Override
    public void reset() {
        //nuffin
    }
}
