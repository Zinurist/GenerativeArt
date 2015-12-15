package algorithms;

import algorithms.randomizer.Randomizer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.util.Random;

public class RandomSplitSquares extends Algorithm{

    private LinkedList<S> squares;
    private Random r;
    private JCheckBox randomBox;

    public RandomSplitSquares(){
        super();
        r=new Random();
        randomBox = new JCheckBox("random");
        randomBox.setSelected(true);
    }

    @Override
    public void step() {
        //TODO split
        LinkedList<S> newS = new LinkedList<S>();
        int width, height;
        S s1,s2,s3;
        for(S s:squares){
            //choose split point near middle
            //split into 4 squares
            //add 3 to newS, change current
            width = s.x2-s.x1;
            height = s.y2-s.y1;
            if(width >4 && height >4){
                //s = top left
                s1 = new S(s.x1+width/2+1,s.y1,s.x2,s.y1+height/2-1);//top right
                s2 = new S(s.x1+width/2+1,s.y1+height/2+1,s.x2,s.y2);//bottom right
                s3 = new S(s.x1,s.y1+height/2+1,s.x1+width/2-1,s.y2);//bottom left
                s.x2 = s.x1+width/2-1;
                s.y2 = s.y1+height/2-1;

                if(randomBox.isSelected()){
                    int rangeX = width>40? width/8:3;
                    int rangeY = height>40? height/8:3;
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
                newS.add(s1);
                newS.add(s2);
                newS.add(s3);
            }
        }
        squares.addAll(newS);

        //draw
        Graphics g = IMG.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        g.setColor(Color.BLACK);
        for(S s : squares){
            g.fillRect(s.x1,s.y1,s.x2-s.x1,s.y2-s.y1);
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
        return list;
    }

    @Override
    public void reset(){
        Graphics g = IMG.createGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());

        squares=new LinkedList<S>();
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
