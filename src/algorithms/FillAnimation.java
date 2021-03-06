package algorithms;

import java.util.*;
import image.Color;
import option.OptionList;

public class FillAnimation extends Algorithm{

    private static int[][] NEXT = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};

    private List<int[]> points;
    //npoints is only used when dfs is false
    private ArrayList<int[]> npoints;
    private boolean[][] checked;
    private Color color;
    private int startRGB;
    private boolean dfs = false;

    public FillAnimation(){
        super();
        color = Color.BLUE;
        reset();
    }

    @Override
    public String toString() {
        return "Fill";
    }

    @Override
    public void step() {
        int x,y;
        if(points.isEmpty()){
            //TODO get starting points somehow
            Random r = new Random();
            x = r.nextInt(IMG.getWidth());
            y = r.nextInt(IMG.getHeight());

            startRGB = IMG.getRGB(x,y);
            if(startRGB != color.getRGB()) {
                points.add(new int[]{x, y});
            }
            //didnt find a point
            if(points.isEmpty()){
                return;
            }
        }

        IMG.setColor(color);

        if (dfs){
            for(int i=0; i<100; i++) {
                if(points.isEmpty()) break;
                LinkedList<int[]> pointsLinked = (LinkedList<int[]>) points;
                int[] p = pointsLinked.removeFirst();
                IMG.drawLine(p[0], p[1], p[0], p[1]);

                for (int[] off : NEXT) {
                    x = p[0] + off[0];
                    y = p[1] + off[1];
                    if (inBounds(x, y, IMG.getWidth(), IMG.getHeight()) && IMG.getRGB(x, y) == startRGB && !checked[x][y]) {
                        pointsLinked.addFirst(new int[]{x, y});
                        checked[x][y] = true;
                    }
                }
            }

        }else{
            npoints.ensureCapacity(points.size());

            int num = points.size();

            for(int i=0; i<num; i++){
                int[] p = points.get(i);
                IMG.drawLine(p[0],p[1],p[0],p[1]);

                for(int[] off : NEXT){
                    x = p[0] + off[0];
                    y = p[1] + off[1];
                    if(inBounds(x, y, IMG.getWidth(), IMG.getHeight()) && IMG.getRGB(x,y) == startRGB && !checked[x][y]) {
                        npoints.add(new int[]{x, y});
                        checked[x][y] = true;
                    }
                }
            }

            ArrayList<int[]> tmp = (ArrayList<int[]>) points;
            points = npoints;
            npoints = tmp;

            npoints.clear();
        }

        if(points.isEmpty()){
            reset();
            stop();
        }
    }

    @Override
    public void reset() {
        if (dfs){
            points = new LinkedList<>();
        }else {
            points = new ArrayList<>(1);
            npoints = new ArrayList<>(4);
        }
        checked = new boolean[IMG.getWidth()][IMG.getHeight()];
        for(int x=0; x<checked.length; x++)
            for(int y=0; y<checked[x].length; y++)
                checked[x][y] = false;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption(color, val -> color = val);
        list.addOption("use dfs", dfs, val -> {dfs = val;reset();});
        return list;
    }
}
