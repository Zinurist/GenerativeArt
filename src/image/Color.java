package image;

/**
 * Color class.
 */
public class Color {

    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(255,255,255);
    public static final Color BLUE = new Color(java.awt.Color.BLUE.getRGB());
    public static final Color RED = new Color(java.awt.Color.RED.getRGB());

    java.awt.Color color;

    public Color(int r, int g, int b){
        color = new java.awt.Color(r,g,b);
    }
    public Color(int r, int g, int b, int a){
        color = new java.awt.Color(r,g,b,a);
    }
    public Color(int rgb){
        color = new java.awt.Color(rgb);
    }

    public Color(float r, float g, float b){
        color = new java.awt.Color(r,g,b);
    }

    public int getBlue(){
        return color.getBlue();
    }
    public int getGreen(){
        return color.getGreen();
    }
    public int getRed(){
        return color.getRed();
    }
    public int getAlpha(){
        return color.getAlpha();
    }
    public int getRGB(){
        return color.getRGB();
    }

}
