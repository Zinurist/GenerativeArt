package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import image.Color;
import option.OptionList;

public class Ripples extends Algorithm {

    private Color c1, c2;
    private boolean negative;
    private List<int[]> circles;
    private int thresholdEnd = IMG.getWidth()/4;
    private int thresholdStart = IMG.getWidth()/8;

    private static int MAX_CIRCLES = 5;

    public Ripples(){
        c1 = Color.BLACK;
        c2 = Color.WHITE;
        negative = false;
        reset();
    }

    @Override
    public String toString() {
        return "Ripples";
    }

    @Override
    public void step() {
        IMG.setColor(c1);
        IMG.fillRect(0,0,IMG.getWidth(),IMG.getHeight());

        int[] c;
        if(circles.size() < MAX_CIRCLES){
            Random r = new Random();
            if(r.nextDouble() > 0.995){
                c =  new int[3];
                c[0] = r.nextInt(IMG.getWidth());
                c[1] = r.nextInt(IMG.getHeight());
                c[2] = 0;
                circles.add(c);
            }
        }

        for(int i=0; i<circles.size(); i++){
            c = circles.get(i);
            c[2]++;
            if(c[2] > thresholdEnd){
                circles.remove(i);
                i--;
            }else if(c[2] > thresholdStart){
                //fade out by changing color
                int original = c2.getBlue();
                int vec = c1.getBlue() - original;
                double progress = c[2]-thresholdStart;
                double diff = thresholdEnd-thresholdStart;
                vec = (int) (vec*(progress/diff));
                vec = original+vec;
                IMG.setColor(new Color(vec,vec,vec));
            }else{
                IMG.setColor(c2);
            }
            IMG.drawOval(c[0]-c[2],c[1]-c[2],c[2]*2,c[2]*2);
        }
    }

    @Override
    public void reset() {
        circles = new ArrayList<>(MAX_CIRCLES);
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("negative", negative, val -> {
            negative = val;
            if(negative){
                c1 = Color.WHITE;
                c2 = Color.BLACK;
            }else{
                c1 = Color.BLACK;
                c2 = Color.WHITE;
            }
        });
        return list;
    }
}
