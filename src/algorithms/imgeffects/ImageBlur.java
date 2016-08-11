package algorithms.imgeffects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBlur extends ImageEffect {

    private static int[][] MASK = {{1,2,1},{2,4,2},{1,2,1}};

    private BufferedImage tmp;

    public ImageBlur(){
        super();
        tmp = original;
    }

    @Override
    public String toString() {
        return "Image blur";
    }

    @Override
    public void step(Graphics g) {
        int avgr, avgb, avgg, count;
        Color c;

        //blur using gauss mask
        for(int y=-MASK.length/2; y<height; y++){
            for(int x=-MASK.length/2; x<width; x++){
                avgr = 0; avgg = 0; avgb = 0; count = 0;
                for(int i=0; i<MASK.length; i++){
                    if(!(y+i >= height || y+i < 0)) {
                        for (int j = 0; j < MASK.length; j++) {
                            if (!(x + j >= width || x + j < 0)) {
                                c = new Color(tmp.getRGB(x + j, y + i));
                                avgr += c.getRed() * MASK[i][j];
                                avgg += c.getGreen() * MASK[i][j];
                                avgb += c.getBlue() * MASK[i][j];
                                count += MASK[i][j];
                            }
                        }
                    }
                }
                avgr /= count;
                avgg /= count;
                avgb /= count;

                g.setColor(new Color(avgr,avgg,avgb));
                g.drawLine(x + MASK.length / 2, y + MASK.length / 2, x + MASK.length / 2, y + MASK.length / 2);
            }
        }

        tmp = IMG;
    }

    @Override
    public void reset(){
        super.reset();
        tmp = original;
    }


}
