package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFilter extends ImageEffect {

    private static int[][] GAUSS = {{1,2,1},{2,4,2},{1,2,1}};
    private static int[][] GAUSS_BLUR = {{1,1,1},{1,1,1},{1,1,1}};
    private static int[][] SHARP = {{0,-1,0},{-1,5,-1},{0,-1,0}};
    private static int[][] LAPLACE = {{0,1,0},{1,-4,1},{0,1,0}};
    private static int[][] EDGES_45 = {{1,1,1},{1,-8,1},{1,1,1}};
    private static int[][] SOBEL_X = {{-1,-2,-1},{0,0,0},{1,2,1}};
    private static int[][] SOBEL_Y = {{-1,0,1},{-2,0,2},{-1,0,1}};
    private static int[][] RELIEF = {{-2,-1,0},{-1,1,1},{0,1,2}};
    private static int[][] ISLANDS = {{-1,1,-1,1,-1},{-1,1,-1,1,-1},{-1,1,1,1,-1},{-1,1,-1,1,-1},{-1,1,-1,1,-1}};
    private static int[][] STARS = {{-1,-1,-1,-1,-1},{-1,1,1,1,-1},{-1,1,1,1,-1},{-1,1,1,1,-1},{-1,-1,-1,-1,-1}};
    private static int[][] VERTHOR = {{-1,0,-1,0,-1},{0,1,1,1,0},{-1,1,1,1,-1},{0,1,1,1,0},{-1,0,-1,0,-1}};
    //TODO make filter customizable
    private static int[][] CUSTOM = {{-1,0,-1,0,-1},{0,1,1,1,0},{-1,1,1,1,-1},{0,1,1,1,0},{-1,0,-1,0,-1}};

    private BufferedImage tmp;
    private int[][] filter;
    private int factor;

    public ImageFilter(){
        super();
        setFilter(0);
        reset();
    }

    private void copyImg(){
        Graphics g = tmp.getGraphics();
        g.drawImage(IMG,0,0,null);
        g.dispose();
    }

    private void setFilter(int id){
        factor = 1;
        switch(id){
            case 0://gauss filter
                filter = GAUSS;
                factor = 16;
                break;
            case 1://gauss blur
                filter = GAUSS_BLUR;
                factor = 9;
                break;
            case 2://sharp filter
                filter = SHARP;
                break;
            case 3://laplace - edges
                filter = LAPLACE;
                break;
            case 4://edges 45°
                filter = EDGES_45;
                break;
            case 5://sobel x
                filter = SOBEL_X;
                break;
            case 6://sobel y
                filter = SOBEL_Y;
                break;
            case 7://relief
                filter = RELIEF;
                break;
            case 8://islands
                filter = ISLANDS;
                break;
            case 9://stars
                filter = STARS;
                break;
            case 10://vertical/horizontal thingy
                filter = VERTHOR;
                break;
            default://custom
                filter = CUSTOM;
                break;
        }
    }

    @Override
    public String toString() {
        return "Image filters";
    }

    @Override
    public void step(Graphics g) {
        int avgr, avgb, avgg;
        Color c;

        for(int y=0; y<height - filter.length; y++){
            for(int x=0; x<width - filter.length; x++){
                avgr = 0; avgg = 0; avgb = 0;
                for(int i = 0; i < filter.length; i++){
                    for (int j = 0; j < filter.length; j++) {
                        c = new Color(tmp.getRGB(x + j, y + i));
                        avgr += c.getRed() * filter[i][j];
                        avgg += c.getGreen() * filter[i][j];
                        avgb += c.getBlue() * filter[i][j];
                    }
                }
                avgr /= factor;
                avgg /= factor;
                avgb /= factor;

                if(avgr < 0) avgr = 0;
                else if(avgr > 255) avgr = 255;
                if(avgg < 0) avgg = 0;
                else if(avgg > 255) avgg = 255;
                if(avgb < 0) avgb = 0;
                else if(avgb > 255) avgb = 255;

                g.setColor(new Color(avgr,avgg,avgb));
                g.drawLine(x + filter.length / 2, y + filter.length / 2, x + filter.length / 2, y + filter.length / 2);
            }
        }

        copyImg();
    }

    @Override
    public void reset(){
        tmp = new BufferedImage(IMG.getWidth(), IMG.getHeight(), IMG.getType());
        copyImg();
    }

    @Override
    public java.util.List<Component> getOptionList() {
        java.util.List<Component> list = super.getOptionList();

        String[] filters = new String[]{"gauss","gauss blur","sharp","laplace","laplace 2","sobel x","sobel y","relief","islands","stars","vertical/hor.","custom"};
        JComboBox<String> cbFilter = new JComboBox<>(filters);
        cbFilter.addActionListener(l -> setFilter(cbFilter.getSelectedIndex()));
        list.add(cbFilter);

        return list;
    }

}
