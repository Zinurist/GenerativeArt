package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * �mage Interface class.
 */
public class Image {

    private BufferedImage img;

    private Graphics g;

    public Image(int width, int height){
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = img.getGraphics();
        g.setColor(java.awt.Color.BLACK);
        untranslate();
    }

    public Image(String file) throws IOException {
        img = ImageIO.read(new File(file));
    }

    public BufferedImage getData(){
        return img;
    }

    public int getWidth(){
        return img.getWidth();
    }

    public int getHeight(){
        return img.getHeight();
    }

    public int getType(){
        return img.getType();
    }

    public void setColor(Color c){
        g.setColor(c.color);
    }

    public int getRGB(int x, int y){
        return img.getRGB(x,y);
    }

    public void setXORMode(boolean xor){
        if(xor){
            g.setXORMode(java.awt.Color.WHITE);
        }else{
            g.setXORMode(java.awt.Color.BLACK);
        }
    }

    /**
     * Calls to fill/draw will be translated by this offset. If you implement this method, untranslate should be implemented as well.
     * @param xOffset offset in x direction
     * @param yOffset offset in y direction
     */
    public void translate(int xOffset, int yOffset){
        g.translate(xOffset, yOffset);
    }

    /**
     * Sets offsets to zero.
     */
    public void untranslate(){
        java.awt.Color c = g.getColor();
        g = img.createGraphics();
        g.setColor(c);
    }

    public void fillRect(int x, int y, int width, int height){
        g.fillRect(x, y, width, height);
    }

    public void fillOval(int x, int y, int width, int height){
        g.fillOval(x, y, width, height);
    }

    public void fillPolygon(int[] x, int[] y, int num){
        g.fillPolygon(x, y, num);
    }

    public void drawRect(int x, int y, int width, int height){
        g.drawRect(x, y, width, height);
    }

    public void drawOval(int x, int y, int width, int height){
        g.drawOval(x, y, width, height);
    }

    public void drawPolygon(int[] x, int[] y, int num){
        g.drawPolygon(x, y, num);
    }

    public void drawLine(int x1, int y1, int x2, int y2){
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawString(String s, int x, int y){
        g.drawString(s, x, y);
    }

    public void drawImage(Image i, int x, int y){
        g.drawImage(i.getData(), x, y, null);
    }

    public void draw(int x, int y){
        drawLine(x,y,x,y);
    }

    public void drawCircle(int x, int y, int r){
        drawOval(x,y,r,r);
    }

    public void fillCircle(int x, int y, int r){
        fillOval(x,y,r,r);
    }

}
