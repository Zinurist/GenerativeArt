package algorithms;

import java.util.LinkedList;
import java.util.Random;
import image.Color;
import option.OptionList;

public class RandomSplitSquaresBugged extends Algorithm{

    private LinkedList<S> squares;
    private boolean type;

    public RandomSplitSquaresBugged(){
        super();
        type = true;
        reset();
    }

    @Override
    public void step() {
        //split
        LinkedList<S> newS = new LinkedList<S>();
        int width, height;
        S s1,s2,s3;
        for(S s:squares){
            //choose split point near middle
            //split into 4 squares
            //add 3 to newS, change current
            width = s.x2-s.y1;//<- Bug, should be s.x1
            height = s.y2-s.y1;
            if(width >10 && height >10){
                //s = top left
                s1 = new S(s.x1+width/2+1,s.y1,s.x2,s.y1+height/2-1);//top right
                s2 = new S(s.x1+width/2+1,s.y1+height/2+1,s.x2,s.y2);//bottom right
                s3 = new S(s.x1,s.y1+height/2+1,s.x1+width/2-1,s.y2);//bottom left
                if(type) {
                    s.x2 = width / 2 - 1;//<- Bug
                    s.y2 = height / 2 - 1;//<- Bug
                }else{
                    s.x2 = s.x1 + width / 2 - 1;
                    s.y2 = s.y1 + height / 2 - 1;
                }

                //...calculations
                newS.add(s1);
                newS.add(s2);
                newS.add(s3);
            }
        }
        squares.addAll(newS);

        //draw
        IMG.setColor(Color.WHITE);
        IMG.fillRect(0, 0, IMG.getWidth(), IMG.getHeight());
        IMG.setColor(Color.BLACK);
        for(S s : squares){
            IMG.fillRect(s.x1,s.y1,s.x2-s.x1,s.y2-s.y1);
        }
    }

    @Override
    public String toString() {
        return "Split Squares Bugged";
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("First type", type, val -> type = val);
        return list;
    }

    @Override
    public void reset(){
        squares = new LinkedList<S>();
        squares.add(new S(0,0,IMG.getWidth()-1, IMG.getHeight()-1));
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
