package algorithms;

import image.Color;
import option.OptionList;

import java.util.*;

public class BallAndWind extends Algorithm{

    private int tick;
    private double sizePerSection;
    private double[] accels;

    //as options:
    private int sections = 20;
    private int ballSize = 20;
    private double maxAccel = 1.0;//max absolute value of accelerations
    private double gravity = 0;
    private double startVY = 5.0; //settings this to 0 with 0 gravity would be silly
    private double timePerTick = 0.2;

    //ball info:
    private double ballX, ballY, startY;
    private double vx;
    private LinkedList<int[]> history;

    //from top to bottom:
    // first 50 px: no acceleration
    // from then on acceleration according to accels (divided in sections of same size)
    // last 50 px: no acceleration

    public BallAndWind(){
        super();
        accels = new double[sections];
        history = new LinkedList();
        reset();
    }

    @Override
    public String toString() {
        return "Ball and wind";
    }

    @Override
    public void step() {
        //--- simulation ---
        //lowY and highY are the lower and upper index of the acceleration area in which the ball currently is
        int lowY = (int)(ballY+0.5) - ballSize/2;
        lowY -= 50;
        int highY = lowY + ballSize;

        if(lowY < 0) lowY = 0;
        else lowY = (int)(lowY / sizePerSection + 0.5);

        if(highY >= IMG.getHeight() - 100) highY = accels.length - 1; // choose last section
        else if(highY < 0) highY = -1; //before first section -> ignore
        else highY = (int)(highY / sizePerSection + 0.5);

        double ax = 0.0;
        for(int i=lowY; i<=highY; i++)
            ax += accels[i];

        //update ball positions
        //vertical movement as function of t, horizontal movement using Euler
        ballY = 0.5*gravity*timePerTick*tick*timePerTick*tick + startVY*timePerTick*tick + startY;

        vx = vx + timePerTick*ax;
        ballX = ballX + timePerTick*vx;

        //values for coordinates
        int x = (int) (ballX + 0.5) - ballSize/2;
        int y = (int) (ballY + 0.5) - ballSize/2;
        while( x < 0) x += IMG.getWidth();
        x %= IMG.getWidth();

        //--- drawing ---
        emptyIMG();

        //drawing accels
        //int offsetY = 50 + lowY*sizePerSection;
        //for(int i=lowY; i<=highY; i++){
        double offsetY = 50;
        double offsetEnd = offsetY + sizePerSection;
        for(int i=0; i<accels.length; i++){
            //blue for positive acceleration (right), red for negative (left)
            //accels[i]/maxAccel brings it into the rang [0,1]
            //*0.5 brings it to [0,0.5], +0.25 to [0.25,0.75]
            //the other 2 colors are set by this value -> controls the brightness of the color blue (/red)
            //lower acceleration should be brighter -> 1.0 - (accel value)
            if(accels[i] > 0) IMG.setColor(new Color(1.0F - (float)(accels[i]/maxAccel * 0.5 +0.25),1.0F - (float)(accels[i]/maxAccel * 0.5 +0.25),1.0F));
            else IMG.setColor(new Color(1.0F, 1.0F + (float)(accels[i]/maxAccel * 0.5 -0.25),1.0F + (float)(accels[i]/maxAccel * 0.5 -0.25)));

            IMG.fillRect(0, (int) (offsetY + 0.5), IMG.getWidth(), (int) (offsetEnd + 0.5) - (int) (offsetY + 0.5));
            offsetY = offsetEnd;
            offsetEnd += sizePerSection;
        }

        //drawing ball
        IMG.setColor(Color.BLACK);
        IMG.fillOval(x, y, ballSize, ballSize);
        int[] pNew = new int[]{x + ballSize/2, y + ballSize/2};
        int[] pOld = pNew;
        for(int[] p : history){
            IMG.drawLine(p[0], p[1], pOld[0], pOld[1]);
            pOld = p;
        }
        history.addFirst(pNew);

        tick++;
        if(ballY - ballSize/2 > IMG.getHeight())
            reset();
    }

    @Override
    public void reset() {
        tick = 0;
        int areaOfEffect = IMG.getHeight() - 100;//50+50px at the top/bottom
        sizePerSection = areaOfEffect / (double)sections;

        if(accels.length != sections) accels = new double[sections];

        Random r = new Random();
        for(int i=0; i<accels.length; i++){
            accels[i] = r.nextDouble()*maxAccel*2  -maxAccel;
        }

        vx = 0;
        startY = ballSize/2+1;
        ballX = IMG.getWidth()/2;
        history.clear();
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        return list;
    }

}
