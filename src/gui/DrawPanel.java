package gui;

import algorithms.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Draws the actual image, generated by the algorithm.
 * All of the controls with keyboard and mouse are implemented here:
 * mouse wheel  - changes zoom
 * mouse drag   - moves image
 * Ctrl + R     - resets the image position and zoom
 */
public class DrawPanel extends JPanel {

    /**
     * The zoom level. Zoom can be adjusted with the mouse wheel.
     */
    private int zoom;

    /**
     * Variables used to position the image and remember the old mouse position.
     */
    private int xOffset, yOffset, xOld, yOld;
    /**
     * This is used to redraw only when necessary.
     */
    private boolean changed;

    /**
     * Creates a Draw Panel. All important KeyListeners are set here.
     */
    public DrawPanel(){
        reset();

        addMouseWheelListener(e -> {
            zoom -= e.getWheelRotation();
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
                requestFocus();
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
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //click in the panel is needed (for focus)
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
                    reset();
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setFocusable(true);
    }

    /**
     * Resets zoom and offsets.
     */
    public void reset(){
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

        g.translate(xOffset, yOffset);
        if(zoom == 1) {
            g.drawImage(Algorithm.IMG, 0, 0, null);
        }else{
            g.drawImage(Algorithm.IMG, 0, 0, Algorithm.IMG.getWidth()*zoom, Algorithm.IMG.getHeight()*zoom, null);
        }
    }

}
