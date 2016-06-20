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

    protected static BufferedImage original, mask;
    protected static int width, height;

    private TextField locationOrg, locationMask;
    private JButton btnLoadOrg, btnLoadMask;
    private JLabel lbl;
    private JCheckBox box;

    public ImageEffect(){
        original = new BufferedImage(IMG.getWidth(),IMG.getHeight(),BufferedImage.TYPE_INT_ARGB);
        mask = new BufferedImage(IMG.getWidth(),IMG.getHeight(),BufferedImage.TYPE_INT_ARGB);
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
        //don't load mask per default
        //loadImage(1);
    }

    protected void loadImage(int type){
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

    @Override
    public String toString() {
        return "Unknown image effect";
    }

    @Override
    public void reset() {
        emptyIMG();
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
