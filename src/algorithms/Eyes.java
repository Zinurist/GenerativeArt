package algorithms;


import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Eyes extends Algorithm{

    private int radEye, radPupil;
    private int mouseZ;
    private int distanceEyes;

    public Eyes() {
        radEye = 60;
        radPupil = 20;
        mouseZ = 300;
        distanceEyes = 50;
    }

    @Override
    public String toString() {
        return "Eyes";
    }

    @Override
    public void step(Graphics g) {
        emptyIMG();
        g.setColor(Color.BLACK);

        int x1 = IMG.getWidth()/2 - distanceEyes - radEye;
        int y1 = IMG.getHeight()/2;
        int x2 = IMG.getWidth()/2 + distanceEyes + radEye;
        int y2 = IMG.getHeight()/2;
        g.drawOval(x1 - radEye - radPupil, y1 - radEye - radPupil, (radEye + radPupil)*2, (radEye + radPupil)*2);
        g.drawOval(x2 - radEye - radPupil, y2 - radEye - radPupil, (radEye + radPupil)*2, (radEye + radPupil)*2);

        Point m = MainFrame.MF.getMouseLocation();

        //vectors pointing at the mouse location
        //z part is always mouseZ, since the eyes are at z=0
        int vx1 = m.x - x1;
        int vy1 = m.y - y1;
        int vx2 = m.x - x2;
        int vy2 = m.y - y2;

        //scale this vector by radEye -> they now point at the pupil
        //->project on the screen by removing z-component

        //scaling factors: radEye / |v|
        double vLength1 = Math.sqrt(vx1*vx1 + vy1*vy1 + mouseZ*mouseZ);
        vLength1 = radEye/vLength1;
        double vLength2 = Math.sqrt(vx2*vx2 + vy2*vy2 + mouseZ*mouseZ);
        vLength2 = radEye/vLength2;

        //apply scaled vector, ignoring z component
        x1 += (int)(vx1 * vLength1 + 0.5);
        y1 += (int)(vy1 * vLength1 + 0.5);
        x2 += (int)(vx2 * vLength2 + 0.5);
        y2 += (int)(vy2 * vLength2 + 0.5);

        //draw pupils
        g.fillOval(x1 - radPupil, y1 - radPupil, radPupil*2, radPupil*2);
        g.fillOval(x2 - radPupil, y2 - radPupil, radPupil*2, radPupil*2);


    }

    @Override
    public void reset() {

    }

    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = new java.util.LinkedList<>();

        JLabel lblEye = new JLabel("rad eye: " + radEye);
        JLabel lblPupil = new JLabel("rad pupil: " + radPupil);
        JLabel lblZ = new JLabel("mouse z: " + mouseZ);
        JLabel lblDis = new JLabel("distance: " + distanceEyes);

        JSlider slEye = new JSlider(4,300,radEye);
        slEye.addChangeListener( l -> {radEye = slEye.getValue(); lblEye.setText("rad eye: " + radEye);} );
        JSlider slPupil = new JSlider(4,300,radPupil);
        slPupil.addChangeListener( l -> {radPupil = slPupil.getValue(); lblPupil.setText("rad pupil: " + radPupil);} );
        JSlider slZ = new JSlider(4,3000,mouseZ);
        slZ.addChangeListener( l -> {mouseZ = slZ.getValue(); lblZ.setText("mouse z: " + mouseZ);} );
        JSlider slDis = new JSlider(4,300,distanceEyes);
        slDis.addChangeListener( l -> {distanceEyes = slDis.getValue(); lblDis.setText("distance: " + distanceEyes);} );

        list.add(lblEye);
        list.add(slEye);
        list.add(lblPupil);
        list.add(slPupil);
        list.add(lblDis);
        list.add(slDis);
        list.add(lblZ);
        list.add(slZ);
        return list;
    }
}
