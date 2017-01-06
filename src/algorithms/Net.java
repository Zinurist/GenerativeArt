package algorithms;

import image.Color;
import option.OptionList;

public class Net extends Algorithm{

    //settings, all in the options frame
    private int width,height,tmpWidth,tmpHeight;
    private int distance,tmpDistance;
    private double restingDistance;
    private double hWind, vWind;
    private int linkUpdates;
    private boolean drawPoints, drawLinks;

    //points[y][x][0/1/2/3/4] = x/y/xOld/yOld
    private double[][][] points;
    private boolean[][] fixed;

    public Net() {
        width = tmpWidth = 20;
        height = tmpHeight = 20;
        distance = tmpDistance = 20;
        restingDistance = distance;
        hWind = 1.;
        vWind = 1.;
        linkUpdates = 5;
        drawPoints = true;
        drawLinks = true;

        points = new double[height][width][4];
        fixed = new boolean[height][width];

        fixed[0][0] = true;
        fixed[0][width-1] = true;
        fixed[height-1][0] = true;
        fixed[height-1][width-1] = true;

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
                    vx = points[y][x][0] - points[y][x][2];
                    vy = points[y][x][1] - points[y][x][3];

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
    public void step() {
        physics();

        //draw net
        emptyIMG();
        IMG.setColor(Color.BLACK);
        if(drawPoints) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    IMG.fillRect((int) (points[y][x][0] + .5 - 2.), (int) (points[y][x][1] + .5 - 2.), 4, 4);
                }
            }
        }

        if(drawLinks){
            int px, py;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    px = (int)(points[y][x][0] + 0.5);
                    py = (int)(points[y][x][1] + 0.5);
                    if (x + 1 < width) IMG.drawLine(px, py, (int)(points[y][x+1][0] + 0.5), (int)(points[y][x+1][1] + 0.5));
                    if (y + 1 < height) IMG.drawLine(px, py, (int)(points[y+1][x][0] + 0.5), (int)(points[y+1][x][1] + 0.5));
                }
            }
        }
    }

    @Override
    public void reset() {
        boolean tl,tr,bl,br;
        tl = fixed[0][0];
        tr = fixed[0][width-1];
        bl = fixed[height-1][0];
        br = fixed[height-1][width-1];

        //is the user changed the width/height settings, then points array needs to be reallocated
        if(width != tmpWidth || height != tmpHeight){
            width = tmpWidth;
            height = tmpHeight;
            points = new double[height][width][4];
            fixed = new boolean[height][width];
        }
        distance = tmpDistance;

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

        fixed[0][0] = tl;
        fixed[0][width-1] = tr;
        fixed[height-1][0] = bl;
        fixed[height-1][width-1] = br;

    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();

        list.addOption("H Wind", hWind, -10.0, 10.0, 1.0, val -> hWind = val);
        list.addOption("V Wind", vWind, -10.0, 10.0, 1.0, val -> vWind = val);
        list.addOption("Resting dis", restingDistance, 1., 100., 1., val -> restingDistance = val);
        list.addOption("Link updates", linkUpdates, 1, 100, val -> linkUpdates = val);

        list.addOption("top left", fixed[0][0], val -> fixed[0][0] = val);
        list.addOption("top right", fixed[0][width - 1], val -> fixed[0][width - 1] = val);
        list.addOption("bottom left", fixed[height - 1][0], val -> fixed[height - 1][0] = val);
        list.addOption("bottom right", fixed[height - 1][width - 1], val -> fixed[height - 1][width - 1] = val);

        list.addInfo("Applies after reset:");
        list.addInfo("");

        list.addOption("Width", width, 2, 500, 1, (OptionList.IntegerOptionListener)val -> tmpWidth = val);
        list.addOption("Height", height, 2, 500, 1, (OptionList.IntegerOptionListener)val -> tmpHeight = val);
        list.addOption("Distance", distance, 2, 100, 1, (OptionList.IntegerOptionListener)val -> tmpDistance = val);

        list.addOption("draw points", drawPoints, val -> drawPoints = val);
        list.addOption("draw links", drawLinks, val -> drawLinks = val);

        return list;
    }
}
