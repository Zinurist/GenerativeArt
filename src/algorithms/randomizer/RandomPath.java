package algorithms.randomizer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RandomPath extends Randomizer {

    private int x, y, tries;
    private JCheckBox spaceBox;
    private Color col;


    public RandomPath(){
        super(false);
        spaceBox = new JCheckBox("spacing");
        spaceBox.setSelected(true);
        reset();
    }

    private boolean enoughSpace(int xtmp, int ytmp, int width, int height){
        for(int yi=ytmp-1; yi<=ytmp+1; yi++){
            for(int xi=xtmp-1; xi<=xtmp+1; xi++){

                if(yi!=y && xi!=x && inBounds(xi,yi,width,height) ){
                    if(IMG.getRGB(xi, yi) != Color.WHITE.getRGB())
                        return false;
                }
            }
        }

        return true;
    }

    @Override
    public void step(Graphics g, int width, int height) {
        g.setColor(col);

        tries = 0;

        int dir = r.nextInt(4);
        int dirFirst = dir;
        int xtmp, ytmp;

        while(true) {
            xtmp = x;
            ytmp = y;
            switch (dir) {
                case 0:
                    ytmp--;
                    break;
                case 1:
                    xtmp++;
                    break;
                case 2:
                    ytmp++;
                    break;
                case 3:
                    xtmp--;
                    break;
            }

            if(!inBounds(xtmp,ytmp,width,height) || IMG.getRGB(xtmp,ytmp) != Color.WHITE.getRGB() || (spaceBox.isSelected() && !enoughSpace(xtmp, ytmp, width, height)) ){
                dir++;
                if(dir > 3) dir = 0;
            }else{
                x = xtmp;
                y = ytmp;
                g.drawLine(x,y,x,y);
                return;
            }

            //all directions tested
            if(dirFirst == dir){
                do {
                    reset();
                    tries++;
                    if(tries > 15){
                        stop();
                        return;
                    }
                }while(IMG.getRGB(x,y) != Color.WHITE.getRGB());
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Random path";
    }


    @Override
    public void reset() {
        x = r.nextInt(IMG.getWidth());
        y = r.nextInt(IMG.getHeight());
        if(color){
            do{
                col = randomColor();
            }while(col.getRGB() == Color.WHITE.getRGB());
        }else{
            col = Color.BLACK;
        }
    }

    @Override
    public java.util.List<Component> getOptionList(){
        List<Component> list = super.getOptionList();
        list.add(spaceBox);
        return list;
    }

}
