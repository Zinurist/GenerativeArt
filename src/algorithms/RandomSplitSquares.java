package algorithms;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.util.Random;

public class RandomSplitSquares extends Algorithm{

    private LinkedList<S> squares;
    private Random r;
    private JCheckBox randomBox,topLeftBox;

    public RandomSplitSquares(){
        super();
        r=new Random();
        randomBox = new JCheckBox("random");
        randomBox.setSelected(true);
        topLeftBox = new JCheckBox("only top left");
        topLeftBox.setSelected(false);
    }

    @Override
    public void step() {

        Graphics g = IMG.createGraphics();
        g.setColor(Color.WHITE);
        if(!topLeftBox.isSelected()) {
            g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        }else{
            g.fillRect(0, 0, squares.get(0).x2-squares.get(0).x1, squares.get(0).y2-squares.get(0).y1);
        }
        g.setColor(Color.BLACK);


        LinkedList<S> newS = new LinkedList<S>();
        int width, height;
        S s1,s2,s3;
        for(S s:squares){
            //choose split point near middle
            //split into 4 squares
            //add 3 to newS, change current
            width = s.x2-s.x1;
            height = s.y2-s.y1;
            if(width >=8 && height >=8){//8/2=4 -> 4-1=3 -> 3-2=1 ->at least width 1
                //s = top left
                s1 = new S(s.x1+width/2+1,s.y1,s.x2,s.y1+height/2-1);//top right
                s2 = new S(s.x1+width/2+1,s.y1+height/2+1,s.x2,s.y2);//bottom right
                s3 = new S(s.x1,s.y1+height/2+1,s.x1+width/2-1,s.y2);//bottom left
                s.x2 = s.x1+width/2-1;
                s.y2 = s.y1+height/2-1;

                if(randomBox.isSelected()){
                    int rangeX = width>40? width/10:3;
                    int rangeY = height>40? height/10:3;
                    s.x2 -= r.nextInt(rangeX);
                    s.y2 -= r.nextInt(rangeY);
                    s1.x1 += r.nextInt(rangeX);
                    s1.y2 -= r.nextInt(rangeY);
                    s2.x1 += r.nextInt(rangeX);
                    s2.y1 += r.nextInt(rangeY);
                    s3.x2 -= r.nextInt(rangeX);
                    s3.y1 += r.nextInt(rangeY);
                }

                //...calculations
                if(!topLeftBox.isSelected()) {
                    newS.add(s1);
                    newS.add(s2);
                    newS.add(s3);
                }
                g.fillRect(s1.x1,s1.y1,s1.x2-s1.x1,s1.y2-s1.y1);
                g.fillRect(s2.x1,s2.y1,s2.x2-s2.x1,s2.y2-s2.y1);
                g.fillRect(s3.x1,s3.y1,s3.x2-s3.x1,s3.y2-s3.y1);
            }
            g.fillRect(s.x1, s.y1, s.x2 - s.x1, s.y2 - s.y1);
        }
        if(!topLeftBox.isSelected()) {
            squares.addAll(newS);
        }
    }

    @Override
    public String toString() {
        return "Split Squares";
    }

    @Override
    public List<Component> getOptionList(){
        List<Component> list = new LinkedList<Component>();
        list.add(randomBox);
        list.add(topLeftBox);
        return list;
    }

    @Override
    public void reset(){
        Graphics g = IMG.createGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());

        squares = new LinkedList<S>();
        squares.add(new S(0,0,IMG.getWidth()-1, IMG.getHeight()-1));
        r=new Random();
    }

    private class S{
        int x1,y1,x2,y2;
        S(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
    }
}
