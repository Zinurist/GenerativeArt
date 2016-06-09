package algorithms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class ImageBlur extends Algorithm {

    private BufferedImage original;
    private int factor;
    private boolean up;

    private TextField location;
    private JButton btnLoad;
    private JLabel lbl;

    public ImageBlur(){
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
        return "Image blur";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int avgr, avgb, avgg, count;
        Color c;

        //blur using averaging algorithm
        for(int y=0; y<original.getHeight(); y+=factor){
            for(int x=0; x<original.getWidth(); x+=factor){
                avgr = 0; avgg = 0; avgb = 0; count = 0;
                for(int i=0; i<factor; i++){
                    if(y+i >= original.getHeight()) break;
                    for(int j=0; j<factor; j++){
                        if(x+j >= original.getWidth()) break;
                        c = new Color(original.getRGB(x+j, y+i));
                        avgr += c.getRed();
                        avgg += c.getGreen();
                        avgb += c.getBlue();
                        count++;
                    }
                }
                avgr /= count;
                avgg /= count;
                avgb /= count;

                g.setColor(new Color(avgr,avgg,avgb));
                g.fillRect(x, y, factor, factor);
            }
        }

        if(up){
            factor++;
            if(factor>=original.getWidth()/20) up = false;
        }else{
            factor--;
            if(factor<=1) up = true;
        }
    }

    @Override
    public void reset() {
        Graphics g = IMG.getGraphics();
        g.drawImage(original, 0, 0, null);
        factor = 1;
        up = true;
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
