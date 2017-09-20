package algorithms.randomizer;

import image.Color;
import option.OptionList;

import java.util.Arrays;

public class PixelCube extends Randomizer{

    private Color[][][][] pixelData;
    private double[][] sides;
    private int sizeOption;
    private int size;
    private boolean random;

    public PixelCube(){
        super(false);
        sides = new double[3][3];
        random = false;
        sizeOption = 200;
        reset();
    }

    @Override
    public void step(int width, int height) {
        emptyIMG();
        //render

        //get other two vectors per side
        int[][] others = new int[][]{{1,2},{2,0},{0,1}};

        //check front sides:
        for (int side=0; side<3; side++){
            double[] vec = sides[side];
            int front = 0;
            double s = -1;
            if(vec[2] > 0){
                front = 1;
                s = 1;
            }
            Color[][] pixelMatrix = pixelData[side][front];

            double[] vec1 = sides[others[side][0]];
            double[] vec2 = sides[others[side][1]];

            for(int x = 0; x < size; x++){
                for(int y = 0; y < size;y++){
                    double xs = (x-size*0.5);
                    double ys = (y-size*0.5);
                    double[] new_vec = add(scale(vec, size*0.5*s), add(scale(vec1, xs), scale(vec2,ys)));
                    double new_x = new_vec[0];
                    double new_y = new_vec[1];

                    IMG.setColor(pixelMatrix[x][y]);

                    IMG.draw((int)( ( new_x + width/2 ) + 0.5 ), (int)( ( new_y + height/2 ) + 0.5 ));
                    //IMG.fillCircle((int)( ( new_x + width/2 ) + 0.5 ), (int)( ( new_y + height/2 ) + 0.5 ), 10);
                }
            }

        }


        //rotate sides
        for (int side=0; side<3; side++){
            //System.out.println(Math.sqrt(sides[side][0]*sides[side][0] +sides[side][1]*sides[side][1] +sides[side][2] *sides[side][2] ));
            applyXRotate(sides[side], 0);
            applyYRotate(sides[side], 0.05);
            applyZRotate(sides[side], 0.01);
        }
    }

    private static double[] add(double[] vec1, double[] vec2){
        return new double[]{vec1[0]+vec2[0],vec1[1]+vec2[1],vec1[2]+vec2[2]};
    }

    private static double[] scale(double[] vec, double scale){
        double[] vec2 = new double[3];
        for(int i=0; i<3; i++) vec2[i] = vec[i]*scale;
        return vec2;
    }

    private static void applyXRotate(double[] vec, double theta){
        double y = Math.cos(theta)*vec[1] - Math.sin(theta)*vec[2];
        double z = Math.cos(theta)*vec[2] + Math.sin(theta)*vec[1];
        vec[1] = y; vec[2] = z;
    }
    private static void applyYRotate(double[] vec, double theta){
        double x = Math.cos(theta)*vec[0] + Math.sin(theta)*vec[2];
        double z = Math.cos(theta)*vec[2] - Math.sin(theta)*vec[0];
        vec[0] = x; vec[2] = z;
    }
    private static void applyZRotate(double[] vec, double theta){
        double x = Math.cos(theta)*vec[0] - Math.sin(theta)*vec[1];
        double y = Math.cos(theta)*vec[1] + Math.sin(theta)*vec[0];
        vec[0] = x; vec[1] = y;
    }

    @Override
    public String toString() {
        return "Pixel cube";
    }

    @Override
    public void reset() {
        size = sizeOption;
        pixelData = new Color[3][2][size][size];
        sides[0] = new double[]{1,0,0};
        sides[1] = new double[]{0,1,0};
        sides[2] = new double[]{0,0,1};

        for(int a=0; a<3; a++){
            for(int b=0; b<2; b++){
                for(int c=0; c<size; c++){
                    for(int d=0; d<size; d++){
                        if(random) pixelData[a][b][c][d] = randomColor();
                        else {
                            if (a == 0) pixelData[a][b][c][d] = Color.BLUE;
                            else if (a == 1) pixelData[a][b][c][d] = Color.BLACK;
                            else if (a == 2) pixelData[a][b][c][d] = Color.RED;
                        }
                    }
                }
            }
        }
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("random", random, val -> random=val);
        list.addOption("size", sizeOption, 50,500, val -> sizeOption=val);
        return list;
    }

}
