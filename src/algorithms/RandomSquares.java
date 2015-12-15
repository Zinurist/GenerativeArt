package algorithms;

import algorithms.randomizer.Randomizer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import java.util.Random;

public class RandomSquares extends Algorithm{

    private List<S> squares;
    private Random r;

    public RandomSquares(){
        super();
        r=new Random();
    }

    @Override
    public void step() {
        //TODO split

        //draw
        Graphics g = IMG.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        g.setColor(Color.BLACK);
        for(S s : squares){

        }
    }

    @Override
    public String toString() {
        return "Squares";
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
