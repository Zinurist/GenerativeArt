package algorithms.imgeffects;

import algorithms.Algorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ColorSwap extends ImageEffect {

    public ColorSwap(){
        super();
    }

    @Override
    public String toString() {
        return "Color swap";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<IMG.getHeight(); y++){
            for(int x=0; x<IMG.getWidth(); x++){
                c = new Color(IMG.getRGB(x, y));
                g.setColor(new Color(c.getBlue(),c.getRed(),c.getGreen()));
                g.drawLine(x, y, x, y);
            }
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        return list;
    }
}
