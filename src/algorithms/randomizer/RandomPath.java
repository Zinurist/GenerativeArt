package algorithms.randomizer;

import image.Color;
import option.OptionList;

public class RandomPath extends Randomizer {

    private int x, y, tries;
    private boolean space;
    private Color col;


    public RandomPath(){
        super(false);
        space = true;
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
    public void step(int width, int height) {
        IMG.setColor(col);

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

            if(!inBounds(xtmp,ytmp,width,height) || IMG.getRGB(xtmp,ytmp) != Color.WHITE.getRGB() || (space && !enoughSpace(xtmp, ytmp, width, height)) ){
                dir++;
                if(dir > 3) dir = 0;
            }else{
                x = xtmp;
                y = ytmp;
                IMG.drawLine(x,y,x,y);
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
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("spacing", space, val -> space = val);
        return list;
    }

}
