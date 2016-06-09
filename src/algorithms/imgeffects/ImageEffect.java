package algorithms.imgeffects;

import algorithms.Algorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public abstract class ImageEffect extends Algorithm {

    protected BufferedImage original;

    private TextField location;
    private JButton btnLoad;
    private JLabel lbl;

    public ImageEffect(){
        original = new BufferedImage(IMG.getWidth(),IMG.getHeight(),BufferedImage.TYPE_INT_ARGB);
        location = new TextField("test.png");
        btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> loadImage());
        lbl = new JLabel("");

        loadImage();
    }

    private void loadImage(){
        try {
            original = ImageIO.read(new File(location.getText()));
            lbl.setText("Loaded image!");
        } catch (IOException e) {
            lbl.setText("Error: "+e.getMessage());
        }
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
        list.add(location);
        list.add(btnLoad);
        list.add(lbl);
        return list;
    }
}
