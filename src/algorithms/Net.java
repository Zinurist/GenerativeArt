package algorithms;

import javax.swing.*;
import java.awt.*;

public class Net extends Algorithm{

    //these 3 settings are set in the reset-method, since they require a reset (changing them dynamically might result in out of bounds errors)
    private JSpinner spWidth, spHeight, spDis;
    //these are need in the reset function, since the fixed array needs to be set correctly there
    private JToggleButton btnTL, btnTR, btnBL, btnBR;

    //settings, all in the options frame
    private int width,height;
    private int distance;
    private double restingDistance;
    private double hWind, vWind;
    private int linkUpdates;

    //points[y][x][0/1/2/3/4] = x/y/xOld/yOld
    private double[][][] points;
    private boolean[][] fixed;

    public Net() {
        width = 20;
        height = 20;
        distance = 20;
        restingDistance = distance;
        hWind = 1.;
        vWind = 1.;
        linkUpdates = 5;

        points = new double[height][width][4];
        fixed = new boolean[height][width];


        spWidth = new JSpinner(new SpinnerNumberModel(width,2,500,1));
        spHeight = new JSpinner(new SpinnerNumberModel(height,2,500,1));
        spDis = new JSpinner(new SpinnerNumberModel(distance,2,100,1));
        btnTL = new JToggleButton("top left",true);
        btnTL.addChangeListener(l->fixed[0][0] = btnTL.isSelected());
        btnTR = new JToggleButton("top right",true);
        btnTR.addChangeListener(l->fixed[0][width-1] = btnTR.isSelected());
        btnBL = new JToggleButton("bottom left",true);
        btnBL.addChangeListener(l->fixed[height-1][0] = btnBL.isSelected());
        btnBR = new JToggleButton("bottom right",true);
        btnBR.addChangeListener(l->fixed[height-1][width-1] = btnBR.isSelected());

        reset();
    }

    @Override
    public String toString() {
        return "Net";
    }

    private void updatePositions(){
        double vx, vy;
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                if(!fixed[y][x]) {
                    vx = points[y][x][2] - points[y][x][0];
                    vy = points[y][x][3] - points[y][x][1];

                    vx = points[y][x][0] + vx + hWind;
                    vy = points[y][x][1] + vy + vWind;

                    points[y][x][2] = points[y][x][0];
                    points[y][x][3] = points[y][x][1];
                    points[y][x][0] = vx;
                    points[y][x][1] = vy;
                }
            }
        }
    }

    private void applyLinkConstraints(int x1, int y1, int x2, int y2){
        double diffX, diffY, diff;
        diffX = points[y1][x1][0] - points[y2][x2][0];
        diffY = points[y1][x1][1] - points[y2][x2][1];
        diff = Math.sqrt(diffX*diffX + diffY*diffY);
        if(diff > restingDistance) {
            diff = (restingDistance - diff) / diff;
            diffX = diffX * diff;
            diffY = diffY * diff;
            if (!(fixed[y1][x1] || fixed[y2][x2])) {
                points[y1][x1][0] += diffX * 0.5;
                points[y1][x1][1] += diffY * 0.5;
                points[y2][x2][0] -= diffX * 0.5;
                points[y2][x2][1] -= diffY * 0.5;
            } else if (fixed[y1][x1] && !fixed[y2][x2]) {
                points[y2][x2][0] -= diffX;
                points[y2][x2][1] -= diffY;
            } else if (fixed[y2][x2] && !fixed[y1][x1]) {
                points[y1][x1][0] += diffX;
                points[y1][x1][1] += diffY;
            }//else nothing to do
        }
    }


    private void physics(){
        updatePositions();

        for(int times = 0; times < linkUpdates; times++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (x + 1 < width) applyLinkConstraints(x, y, x + 1, y);
                    if (y + 1 < height) applyLinkConstraints(x, y, x, y + 1);
                }
            }
        }
    }

    @Override
    public void step(Graphics g) {
        physics();

        //draw net
        emptyIMG();
        g.setColor(Color.BLACK);
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                g.fillRect((int) (points[y][x][0] + .5 - 2.), (int) (points[y][x][1] + .5 - 2.), 4, 4);
            }
        }
    }

    @Override
    public void reset() {
        //is the user changed the width/height settings, then points array needs to be reallocated
        if(width != (int)spWidth.getValue() || height != (int)spHeight.getValue()){
            width = (int)spWidth.getValue();
            height = (int)spHeight.getValue();
            points = new double[height][width][4];
            fixed = new boolean[height][width];
        }
        distance = (int)spDis.getValue();

        double midX = IMG.getWidth()/2 - distance * (width-1.)/2. ;
        double midY = IMG.getHeight()/2 - distance * (height-1.)/2. ;

        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                fixed[y][x] = false;
                points[y][x][0] = midX + x*distance;
                points[y][x][2] = midX + x*distance;
                points[y][x][1] = midY + y*distance;
                points[y][x][3] = midY + y*distance;
            }
        }

        fixed[0][0] = btnTL.isSelected();
        fixed[0][width-1] = btnTR.isSelected();
        fixed[height-1][0] = btnBL.isSelected();
        fixed[height-1][width-1] = btnBR.isSelected();

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new java.util.LinkedList<>();

        JLabel lblHWind = new JLabel("H Wind: "+hWind);
        JLabel lblVWind = new JLabel("V Wind: "+vWind);
        JLabel lblResDis = new JLabel("Resting dis: "+restingDistance);
        JLabel lblUpdates = new JLabel("Link updates: " + linkUpdates);

        JSlider slHWind = new JSlider(-1000, 1000, (int)(hWind*100 + 0.5));
        slHWind.addChangeListener(l-> {
            hWind = slHWind.getValue() / 100.0;
            lblHWind.setText("H Wind: " + hWind);
        });
        JSlider slVWind = new JSlider(-1000, 1000, (int)(vWind*100 + 0.5));
        slVWind.addChangeListener(l-> {
            vWind = slVWind.getValue() / 100.0;
            lblVWind.setText("V Wind: " + vWind);
        });
        JSlider slResDis = new JSlider(10, 1000, (int)(restingDistance*10 + 0.5));
        slResDis.addChangeListener(l-> {
            restingDistance = slResDis.getValue() / 10.0;
            lblResDis.setText("Resting dis: " + restingDistance);
        });
        JSlider slUpdates = new JSlider(1, 100, linkUpdates);
        slUpdates.addChangeListener(l-> {
            linkUpdates = slUpdates.getValue();
            lblUpdates.setText("Link updates: " + linkUpdates);
        });

        JLabel lblWidth = new JLabel("Width: ");
        JLabel lblHeight = new JLabel("Height: ");
        JLabel lblDis = new JLabel("Distance: ");

        list.add(lblHWind);
        list.add(slHWind);
        list.add(lblVWind);
        list.add(slVWind);
        list.add(lblResDis);
        list.add(slResDis);
        list.add(lblUpdates);
        list.add(slUpdates);
        list.add(btnTL);
        list.add(btnTR);
        list.add(btnBL);
        list.add(btnBR);
        list.add(new JLabel("Applies after reset:"));
        list.add(new JLabel(""));
        list.add(lblWidth);
        list.add(spWidth);
        list.add(lblHeight);
        list.add(spHeight);
        list.add(lblDis);
        list.add(spDis);
        return list;
    }
}
