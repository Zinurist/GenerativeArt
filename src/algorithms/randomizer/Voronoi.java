package algorithms.randomizer;

import java.util.ArrayList;
import image.Color;
import option.OptionList;

public class Voronoi extends Randomizer{

    //TODO make options out of these
    private boolean euclid = true;
    private boolean showPoints = false;
    private int num = 25;//has to be at least 1!!!

    //list of all points, first half is x-coordinate, second half is y-coordinate, so point 2 is at ( points.get(1), points.get(points.size()/2 + 1) )
    private ArrayList<Integer> points;
    //list of the colors of the regions
    private ArrayList<Color> colors;

    public Voronoi(){
        super();
        points = new ArrayList<>(2*num);
        colors = new ArrayList<>(num);
        reset();
    }

    @Override
    public void step(int width, int height) {

        int off = points.size()/2;

        //fill voronoi regions
        if(euclid){
            //used to find closest point
            double minDis, dis;
            int minInd;

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    minDis = Math.sqrt( (x-points.get(0))*(x-points.get(0)) + (y-points.get(off))*(y-points.get(off)) );
                    minInd = 0;

                    for(int i = 1; i < off; i++){
                        dis = Math.sqrt( (x-points.get(i))*(x-points.get(i)) + (y-points.get(off+i))*(y-points.get(off+i)) );
                        if(dis < minDis){
                            minDis = dis;
                            minInd = i;
                        }
                    }

                    IMG.setColor(colors.get(minInd));
                    IMG.drawLine(x,y,x,y);
                }
            }
        }else{
            //TODO maybe use algorithm like flood fill to draw the voronoi regions, probably faster
            //used to find closest point
            int minDis, dis;
            int minInd;

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    minDis = Math.abs(x-points.get(0)) + Math.abs(y-points.get(off));
                    minInd = 0;

                    for(int i = 1; i < off; i++){
                        dis = Math.abs(x-points.get(i)) + Math.abs(y-points.get(off+i));
                        if(dis < minDis){
                            minDis = dis;
                            minInd = i;
                        }
                    }

                    IMG.setColor(colors.get(minInd));
                    IMG.drawLine(x,y,x,y);
                }
            }
        }


        //draw points
        if(showPoints){
            IMG.setColor(Color.BLACK);
            for(int i = 0; i < off; i++){
                IMG.fillOval(points.get(i) - 4, points.get(off + i) - 4, 8, 7);//7 instead of 8, because it looks like shit otherwise
            }
        }

    }

    @Override
    public String toString() {
        return "Voronoi diagram";
    }

    @Override
    public void reset() {
        points.ensureCapacity(2*num);
        colors.ensureCapacity(num);

        for(int i=0; i<num; i++){
            points.add(i, r.nextInt(IMG.getWidth()));
            points.add(2*i+1, r.nextInt(IMG.getHeight()));
            colors.add(randomColor());
        }

    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("Euclid", euclid, val -> euclid = val);
        list.addOption("Show points", showPoints, val -> showPoints = val);
        list.addOption("#points", num, 1, 1000, 5, (OptionList.IntegerOptionListener)val -> num = val);
        return list;
    }

}
