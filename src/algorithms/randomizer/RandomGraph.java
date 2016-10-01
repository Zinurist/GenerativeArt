package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class RandomGraph extends Randomizer {


    private static int MIN_NODES = 1;
    private static int MAX_NODES = 5;//inclusive
    private static double MIN_DIS = 5.;//inclusive
    private static double CONE = Math.PI/1.5;//between 180° and 90°

    private boolean shrink;

    //vectors from old point to new point
    private LinkedList<int[]> points, temp;

    public RandomGraph(){
        super(false);
        points = new LinkedList();
        temp = new LinkedList();
        shrink = true;
        reset();
    }

    @Override
    public void step(Graphics g, int width, int height) {
        if(points.isEmpty()){
            stop();
            return;
        }


        int newNodes,x,y;
        double angle, distance, vecAngle;
        int maxDis = (IMG.getWidth()+IMG.getHeight())/2;

        g.setColor(Color.BLACK);
        for(int[] p : points){
            g.drawLine(p[0], p[1], p[2], p[3]);
            g.fillRect(p[0] - 2, p[1] - 2, 4, 4);

            newNodes = r.nextInt(MAX_NODES-MIN_NODES+1)+MIN_NODES;

            p[2] = p[0] - p[2];
            p[3] = p[1] - p[3];
            distance = Math.sqrt(p[2]*p[2] + p[3]*p[3]);

            for(int i=0; i<newNodes; i++){
                if(shrink) {
                    //(distance+MIN_DIS/2) upper limit it previous distant -> graph shrinks
                    distance = r.nextDouble() * (distance+MIN_DIS/2);
                }else{
                    distance = r.nextDouble()*(maxDis-MIN_DIS)+MIN_DIS;
                }
                angle = r.nextDouble()*CONE;
                //transform angle to current vector
                //angle 0° is perpendicular to current vector (p[2],p[3])
                //-> vector to angle "alpha"
                //0° is then at alpha - 90° (90° = PI/2)
                //example: vector points left -> 180° -> 0° is at 90° (pointing up)
                //example: vector points up (down on the drawing panel because y is flipped) -> 90° -> 0° is at 0°

                //in our case: 0° is right bound for the angle if the angle is in a 180° cone
                //else if the cone is smaller/bigger: not 0°, but 90° - cone/2
                //so: angle = angle + (alpha - 90°) + (90° - cone/2) = angle + alpha - cone/2

                vecAngle = Math.atan2(p[3],p[2]);

                //angle += vecAngle - Math.PI/2 + (Math.PI/2 - CONE/2);
                angle += vecAngle - CONE/2;

                x = (int)(p[0] + distance * Math.cos(angle) + 0.5);
                y = (int)(p[1] + distance * Math.sin(angle) + 0.5);

                if(distance > MIN_DIS && inBounds(x,y,IMG.getWidth(),IMG.getHeight())) {
                    temp.add(new int[]{x, y, p[0], p[1]});
                }
            }

        }

        points = temp;
        temp = new LinkedList();
    }

    @Override
    public String toString() {
        return "Random graph";
    }


    @Override
    public void reset() {
        points.clear();

        int midX = IMG.getWidth()/2;
        int midY = IMG.getHeight()/2;
        int newNodes = r.nextInt(MAX_NODES-MIN_NODES+1)+MIN_NODES + 5;
        for(int i=0; i<newNodes; i++) {
            int x = r.nextInt(IMG.getWidth() / 2) + IMG.getWidth() / 4;
            int y = r.nextInt(IMG.getHeight() / 2) + IMG.getHeight() / 4;
            points.add(new int[]{x, y, midX, midY});
        }

    }

    @Override
    public java.util.List<Component> getOptionList(){
        List<Component> list = super.getOptionList();

        JCheckBox cbShrink = new JCheckBox("shrink", shrink);
        cbShrink.addActionListener(l -> shrink = cbShrink.isSelected());
        list.add(cbShrink);

        return list;
    }

}
