package gui;

import java.util.function.Function;
import image.Image;
import image.Color;

/**
 * This class provides functions to plot a given function into a image.
 */
public class Plotter {

    //equidistant

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero){
        plot(img, func, x_zero, y_zero, 0-x_zero, img.getWidth()-x_zero);
    }

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero, double x_start, double x_stop){
        plot(img, func, x_zero, y_zero, x_start, x_stop, 1);
    }

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero, double x_start, double x_stop, double x_step){
        plot(img, func, x_zero, y_zero, x_start, x_stop, x_step, Color.BLACK);
    }

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero, double x_start, double x_stop, double x_step, Color c){
        img.setColor(c);
        int x, xOld, y, yOld;
        xOld = (int) Math.round(x_start);
        yOld = (int) Math.round(func.apply((double) xOld));
        for(x_start+=x_step; x_start < x_stop; x_start+=x_step){
            x = (int) Math.round(x_start);
            y = (int) Math.round(func.apply((double) x));
            img.drawLine(x_zero+xOld,y_zero-yOld,x_zero+x,y_zero-y);
            xOld = x;
            yOld = y;
        }
    }

    //using variable points

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero, double[] xs){
        plot(img, func, x_zero, y_zero, xs, Color.BLACK);
    }

    public static void plot(Image img, Function<Double, Double> func, int x_zero, int y_zero, double[] xs, Color c){
        if(xs.length<2) return;
        img.setColor(c);
        int x, xOld, y, yOld;
        xOld = (int) Math.round(xs[0]);
        yOld = (int) Math.round(func.apply((double) xOld));
        for(int i=1; i<xs.length; i++){
            x = (int) Math.round(xs[i]);
            y = (int) Math.round(func.apply((double) x));
            img.drawLine(x_zero+xOld,-(y_zero+yOld),x_zero+x,-(y_zero+y));
            xOld = x;
            yOld = y;
        }
    }
}
