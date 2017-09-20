package algorithms;

import algorithms.Algorithm;
import image.Color;
import option.OptionList;

import java.util.Arrays;
import java.util.Random;

public class Cube extends Algorithm {

    private Color[] colors;
    private double[][] sides;
    private double[] direction;
    private int size;
    private boolean random;

    public Cube(){
        super();
        sides = new double[3][3];
        random = false;
        size = 200;
        colors = new Color[]{Color.RED,Color.BLUE,Color.BLACK,Color.GREEN,Color.YELLOW,Color.CYAN};
        reset();
    }

    @Override
    public void step() {
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

            double[] vec1 = sides[others[side][0]];
            double[] vec2 = sides[others[side][1]];

            double[][] corners = new double[][]{{-size*0.5,-size*0.5},{size*0.5,-size*0.5},{size*0.5,size*0.5},{-size*0.5,size*0.5}};
            int[] xs = new int[4];
            int[] ys = new int[4];
            for(int i=0; i<4; i++){
                double[] new_vec = add(scale(vec, size*0.5*s), add(scale(vec1, corners[i][0]), scale(vec2,corners[i][1])));
                xs[i] = (int)(( new_vec[0] + IMG.getWidth()/2 ) + 0.5 );
                ys[i] = (int)(( new_vec[1] + IMG.getHeight()/2 ) + 0.5 );
            }
            IMG.setColor(colors[side*2+front]);
            IMG.fillPolygon(xs,ys,4);

        }


        //rotate sides
        for (int side=0; side<3; side++){
            //System.out.println(Math.sqrt(sides[side][0]*sides[side][0] +sides[side][1]*sides[side][1] +sides[side][2] *sides[side][2] ));
            applyXRotate(sides[side], direction[0]);
            applyYRotate(sides[side], direction[1]);
            applyZRotate(sides[side], direction[2]);
        }

        if(random){
            Random r = new Random();
            if(r.nextFloat()<0.01){
                //change direction
                for(int i=0; i<3; i++) direction[i] = r.nextFloat()*0.05;
            }
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
        return "Cube";
    }

    @Override
    public void reset() {
        sides[0] = new double[]{1,0,0};
        sides[1] = new double[]{0,1,0};
        sides[2] = new double[]{0,0,1};
        direction = new double[]{0,0.05,0.01};
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("random", random, val -> random=val);
        list.addOption("size", size, 50,500, val -> size=val);
        return list;
    }

}
