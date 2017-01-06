package algorithms;

import gui.MainFrame;

import java.awt.*;
import java.util.Random;
import image.Color;
import option.OptionList;

public class Search extends Algorithm{

    private int num;
    private int width, height;

    private int[][] circles;

    public Search() {
        /*
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        num = (width + height)/200;
        circles = new int[num][3];

        reset();
    }

    @Override
    public String toString() {
        return "Search";
    }


    @Override
    public void step() {
        Point w = MainFrame.MF.getPanelLocation();
        emptyIMG(Color.BLACK);
        IMG.setColor(Color.WHITE);
        for (int i = 0; i < num; i++) {
            IMG.fillOval(circles[i][0] - circles[i][2] - w.x, circles[i][1] - circles[i][2] - w.y, circles[i][2] * 2, circles[i][2] * 2);
        }
    }

    @Override
    public void reset() {
        if(num != circles.length) circles = new int[num][3];

        Random r = new Random();
        for(int i = 0; i<num; i++){
            circles[i][2] = r.nextInt(100) + 10;
            circles[i][0] = r.nextInt(width);
            circles[i][1] = r.nextInt(height);
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = new OptionList();
        return list;
    }
}
