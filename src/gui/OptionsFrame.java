package gui;

import algorithms.Algorithm;

import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

/**
 * Opens when clicking 'Options' in the main frame. Shows different option fields set by the algorithm.
 */
public class OptionsFrame extends JFrame {

    private JPanel contentPane;

    public OptionsFrame(){
        setTitle("Options");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initContentPane(new LinkedList<Component>());
        setPreferredSize(new Dimension(300,100));
    }

    public void setAlgorithm(Algorithm algo){
        initContentPane(algo.getOptionList());
    }

    private void initContentPane(List<Component> optionList){
        contentPane = new JPanel(new GridLayout(0,2,5,5));
        contentPane.setBackground(new Color(16643811));

        for(Component c : optionList){
            contentPane.add(c);
        }

        setContentPane(contentPane);
        pack();
    }

}
