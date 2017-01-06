package algorithms;


import gui.MainFrame;
import java.awt.*;
import image.Color;
import option.OptionList;

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
    public void step() {
        emptyIMG();
        IMG.setColor(Color.BLACK);

        int x1 = IMG.getWidth()/2 - distanceEyes - radEye;
        int y1 = IMG.getHeight()/2;
        int x2 = IMG.getWidth()/2 + distanceEyes + radEye;
        int y2 = IMG.getHeight()/2;
        IMG.drawOval(x1 - radEye - radPupil, y1 - radEye - radPupil, (radEye + radPupil)*2, (radEye + radPupil)*2);
        IMG.drawOval(x2 - radEye - radPupil, y2 - radEye - radPupil, (radEye + radPupil)*2, (radEye + radPupil)*2);

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
        IMG.fillOval(x1 - radPupil, y1 - radPupil, radPupil*2, radPupil*2);
        IMG.fillOval(x2 - radPupil, y2 - radPupil, radPupil*2, radPupil*2);


    }

    @Override
    public void reset() {

    }

    @Override
    public OptionList getOptionList(){
        OptionList list = new OptionList();
        list.addOption("rad eye", radEye, 4, 300, val -> radEye = val);
        list.addOption("rad pupil", radPupil, 4, 300, val -> radPupil = val);
        list.addOption("mouse z", mouseZ, 4, 3000, val -> mouseZ = val);
        list.addOption("distance", distanceEyes, 4, 300, val -> distanceEyes = val);
        return list;
    }
}
