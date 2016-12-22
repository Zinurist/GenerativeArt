package algorithms.imgeffects;

import javax.swing.*;
import java.awt.*;
import image.Color;

public class Cartoon extends ImageFilter{


    private int limit, rad;

    public Cartoon(){
        super();
        filterNum = 4;
        limit = 15;
        rad = 1;
    }

    @Override
    public String toString() {
        return "Cartoon";
    }

    @Override
    public void step() {
        copyImg();
        setFilter(filterNum);

        int avgr, avgb, avgg;
        Color c;

        for(int y=0; y<height - filter.length; y++){
            for(int x=0; x<width - filter.length; x++){
                avgr = 0; avgg = 0; avgb = 0;
                for(int i = 0; i < filter.length; i++){
                    for (int j = 0; j < filter.length; j++) {
                        c = new Color(TMP.getRGB(x + j, y + i));
                        avgr += c.getRed() * filter[i][j];
                        avgg += c.getGreen() * filter[i][j];
                        avgb += c.getBlue() * filter[i][j];
                    }
                }
                avgr /= factor;
                avgg /= factor;
                avgb /= factor;

                if(avgr < 0) avgr = 0;
                else if(avgr > 255) avgr = 255;
                if(avgg < 0) avgg = 0;
                else if(avgg > 255) avgg = 255;
                if(avgb < 0) avgb = 0;
                else if(avgb > 255) avgb = 255;

                if(avgr >= limit || avgg >= limit || avgb >= limit) {
                    IMG.setColor(Color.BLACK);
                    if(rad<=1) {
                        IMG.drawLine(x + filter.length / 2, y + filter.length / 2, x + filter.length / 2, y + filter.length / 2);
                    }else{
                        IMG.fillOval(x + filter.length / 2 - rad, y + filter.length / 2 - rad, rad*2, rad*2);
                    }
                }else{
                    IMG.setColor(new Color(TMP.getRGB(x + filter.length / 2, y + filter.length / 2)));
                    IMG.drawLine(x + filter.length / 2, y + filter.length / 2, x + filter.length / 2, y + filter.length / 2);
                }
            }
        }

    }


    @Override
    public java.util.List<Component> getOptionList() {
        java.util.List<Component> list = super.getOptionList();
        ((JComboBox)list.get(list.size()-1)).setSelectedIndex(filterNum);

        JLabel lblLimit = new JLabel("limit: "+limit);
        JLabel lblRad = new JLabel("radius: "+rad);

        JSlider slLimit = new JSlider(0,255,limit);
        slLimit.addChangeListener(l->{limit = slLimit.getValue(); lblLimit.setText("limit: "+limit);});
        JSlider slRad = new JSlider(1,10,rad);
        slRad.addChangeListener(l->{rad = slRad.getValue(); lblRad.setText("radius: "+rad);});

        list.add(lblLimit);
        list.add(slLimit);
        list.add(lblRad);
        list.add(slRad);
        return list;
    }
}
