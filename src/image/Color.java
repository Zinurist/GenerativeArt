package image;

/**
 * Color class.
 */
public class Color {

    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(255,255,255);
    public static final Color BLUE = new Color(java.awt.Color.BLUE.getRGB());
    public static final Color RED = new Color(java.awt.Color.RED.getRGB());
    public static final Color GREEN = new Color(java.awt.Color.GREEN.getRGB());
    public static final Color CYAN = new Color(java.awt.Color.CYAN.getRGB());
    public static final Color YELLOW = new Color(java.awt.Color.YELLOW.getRGB());
    public static final Color MAGENTA = new Color(java.awt.Color.MAGENTA.getRGB());

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

    public Color(){
        this(0,0,0);
    }

    //y: 0,1  cb/cr: -0.5,0.5
    public void fromYCbCr(float y, float cb, float cr){
        float kb = 0.114F;
        float kr = 0.299F;
        float kg = 1-kr-kb;

        float b = 2*cb*(1-kb) + y;
        float r = 2*cr*(1-kr) + y;
        float g = (y - kb*b - kr*r)/kg;
        System.out.println(y+" "+cr+" "+cb);
        System.out.println(r+" "+g+" "+b);
        color = new java.awt.Color(r,g,b);
    }

    public void fromHSB(float h, float s, float b){
        color = new java.awt.Color(java.awt.Color.HSBtoRGB(h,s,b));
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
