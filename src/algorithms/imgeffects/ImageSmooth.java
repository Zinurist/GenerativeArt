package algorithms.imgeffects;

import java.awt.*;

public class ImageSmooth extends ImageEffect {

    private static int[][] MASK = {{1,2,1},{2,4,2},{1,2,1}};

    @Override
    public String toString() {
        return "Image smooth";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int avgr, avgb, avgg, count;
        Color c;

        //blur using averaging algorithm
        for(int y=-1; y<height; y++){
            for(int x=-1; x<width; x++){
                avgr = 0; avgg = 0; avgb = 0; count = 0;
                for(int i=0; i<3; i++){
                    if(!(y+i >= height || y+i < 0)) {
                        for (int j = 0; j < 3; j++) {
                            if (!(x + j >= width || x + j < 0)) {
                                c = new Color(original.getRGB(x + j, y + i));
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
                g.fillRect(x+1, y+1, x+1, y+1);
            }
        }
    }


}
