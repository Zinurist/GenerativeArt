package algorithms.imgeffects;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Rectify extends ImageEffect {

    @Override
    public String toString() {
        return "Rectify image";
    }

    @Override
    public void step() {
        Graphics g = IMG.getGraphics();
        int w, h = 20;
        boolean dw, dh;

        Random r = new Random();
        List<int[]> rects = new LinkedList();
        List<int[]> npoints = new LinkedList<>();
        List<int[]> npoints2;

        npoints.add(new int[]{0,0,width,height});

        while(!npoints.isEmpty()){
            npoints2 = new LinkedList<>();

            for(int[] p : npoints){
                if(p[2] <=10 ) w = 10;
                else w = r.nextInt(p[2]-10)+10;
                if(p[3] <=10 ) h = 10;
                else h = r.nextInt(p[3]-10)+10;
                dw = true;
                if(w >= width-p[0]){
                    w = width-p[0];
                    dw = false;
                }else if(p[2]-w <= 10){
                    w = p[2];
                    dw = false;
                }

                dh = true;
                if(h >= height-p[1]){
                    h = height-p[1];
                    dh = false;
                }else if(p[3]-h <= 10){
                    h = p[3];
                    dh = false;
                }

                if(dw){
                    npoints2.add(new int[]{p[0]+w, p[1], p[2]-w, h});
                }
                if(dh){
                    npoints2.add(new int[]{p[0], p[1]+h, w, p[3]-h});
                }
                if(dh && dw){
                    npoints2.add(new int[]{p[0] + w, p[1] + h, p[2], p[3]});
                }

                rects.add(new int[]{p[0], p[1], w, h});
            }

            npoints = npoints2;
        }

        g.setColor(Color.BLACK);
        for(int[] re : rects){
            g.drawRect(re[0],re[1],re[2],re[3]);
        }

    }

}
