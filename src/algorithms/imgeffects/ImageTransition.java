package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;

public class ImageTransition extends ImageEffect {

    private double offset;
    private JComboBox<String> type;

    public ImageTransition(){
        super();

        type = new JComboBox<>(new String[]{"linear", "cubic"});
    }


    @Override
    public String toString() {
        return "Image transition";
    }


    @Override
    public void step() {
        if(offset > 1.0){
            reset();
            return;
        }
        offset += 0.05;

        Graphics g = IMG.getGraphics();

        Color co, cm;
        double offset2 = (3*offset*offset - 2*offset*offset*offset);

        for(int y=0; y < height; y++){
            for(int x=0; x < width; x++){
                co = new Color(original.getRGB(x, y));
                cm = new Color(mask.getRGB(x, y));

                //!!! round by adding +0.5 and casting to int: (int)(x+0.5)
                if(type.getSelectedIndex() == 0) {
                    //linear, 2 conditions: f(0) = x0 = color original, f(1) = x1 = color mask
                    //x0 + (x1-x0)*x = f(x)
                    //co.getBlue() + (int)((cm.getBlue() - co.getBlue())*offset + 0.5)
                    g.setColor(new Color(co.getRed() + (int) ((cm.getRed() - co.getRed()) * offset + 0.5), co.getGreen() + (int) ((cm.getGreen() - co.getGreen()) * offset + 0.5), co.getBlue() + (int) ((cm.getBlue() - co.getBlue()) * offset + 0.5)));
                    g.drawLine(x, y, x, y);
                }else if(type.getSelectedIndex() == 1){
                    //cubic, 2 extra conditions: f'(0) = f'(1) = 0
                    //f(x) = ax^3 + bx^2 + cx + d, f'(x) = 3ax^2 + 2bx + c
                    //f'(0) = c = 0    |    f'(1) = 3a + 2b = 0
                    //f(0) = d = x0    |    f(1) = a + b + c + d = a + b + x0 = x1
                    //solving: a = -(2/3)b, a+b+x0 = -(2/3)b+b+x0 = (1/3)b+x0 = x1 -> b = 3*(x1-x0)
                    //-> a = -(2/3) * 3 * (x1-x0) = -2*(x1-x0)
                    //->f(x) = -2*(x1-x0)x^3 + 3*(x1-x0)x^2 + x0  =  x0 + (x1-x0) * (3*x^2 - 2*x^3) = x0 + (x1-x0) * offset2

                    //with colors:
                    //co.getBlue() + (int)( (cm.getBlue()-co.getBlue()) * offset2 +0.5)
                    g.setColor(new Color( co.getRed() + (int)( (cm.getRed()-co.getRed()) * offset2 +0.5), co.getGreen() + (int)( (cm.getGreen()-co.getGreen()) * offset2 +0.5), co.getBlue() + (int)( (cm.getBlue()-co.getBlue()) * offset2 +0.5)  ));
                    g.drawLine(x, y, x, y);
                }else{
                    System.out.println("Shouldn't happen!");
                }

            }
        }

    }


    @Override
    public void reset() {
        super.reset();
        offset = 0;
    }


    @Override
    public java.util.List<Component> getOptionList(){
        java.util.List<Component> list = super.getOptionList();
        list.add(new JLabel("Interpolation:"));
        list.add(type);
        return list;
    }

}
