package algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FillAnimation extends Algorithm{

    private static int[][] NEXT = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

    private LinkedList<int[]> points;
    private JColorChooser color;
    private int startRGB;

    public FillAnimation(){
        super();

        color = new JColorChooser(Color.BLUE);
    }

    @Override
    public String toString() {
        return "Fill";
    }

    @Override
    public void step(Graphics g) {
        int x,y;
        if(points.isEmpty()){
            //TODO get points somehow
            Random r = new Random();
            x = r.nextInt(IMG.getWidth());
            y = r.nextInt(IMG.getHeight());

            startRGB = IMG.getRGB(x,y);
            points.add(new int[]{x,y});
        }

        g.setColor(color.getColor());

        LinkedList<int[]> newp = new LinkedList<>();

        for(int[] p : points){
            g.drawLine(p[0],p[1],p[0],p[1]);

            for(int[] off : NEXT){
                x = p[0] + off[0];
                y = p[1] + off[1];
                if(inBounds(x, y, IMG.getWidth(), IMG.getHeight()) && IMG.getRGB(x,y) == startRGB)
                    newp.add(new int[]{x,y});
            }
        }

        points = newp;
        if(points.isEmpty()){
            stop();
        }
    }

    @Override
    public void reset() {
        points = new LinkedList<>();
    }

    @Override
    public List<Component> getOptionList(){
        List<Component> list = new LinkedList<>();
        list.add(color);
        return list;
    }
}
