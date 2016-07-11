package gui;

import algorithms.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Draws the actual image, generated by the algorithm.
 */
public class DrawPanel extends JPanel {

    private int zoom;

    private int xOffset, yOffset, xOld, yOld;
    private boolean changed;

    public DrawPanel(){
        reset();

        addMouseWheelListener(e -> {
            zoom += e.getWheelRotation();
            if (zoom < 1) zoom = 1;
            changed = true;
            repaint();
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xOffset -= xOld - e.getX();
                yOffset -= yOld - e.getY();
                xOld = e.getX();
                yOld = e.getY();
                changed = true;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("rr");
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R){
                    reset();
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void reset(){
        zoom = 1;
        xOffset = 0;
        yOffset = 0;
        changed = true;
    }

    @Override
    public void paintComponent(Graphics g){
        if(changed){
            g.clearRect(0,0,getWidth(),getHeight());
            changed = false;
        }
        if(zoom - 1.0 < 1E-15) {
            g.drawImage(Algorithm.IMG, xOffset, yOffset, null);
        }else{
            int height = Math.min(Algorithm.IMG.getHeight(), this.getHeight());
            int width = Math.min(Algorithm.IMG.getWidth(), this.getWidth());

            for(int y = 0; y<height; y++){
                for(int x = 0; x<width; x++){
                    g.setColor(new Color(Algorithm.IMG.getRGB(x,y)));
                    g.fillRect((x + xOffset) * zoom, (y + yOffset) * zoom, zoom, zoom);

                }
            }
        }
    }

}
