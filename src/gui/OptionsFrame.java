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
    private int rows;

    public OptionsFrame(){
        setTitle("Options");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initContentPane(new LinkedList<>());
        setMinimumSize(new Dimension(300,100));
    }

    /**
     * Needs to be called before opening the options frame. This sets the content of the options frame according to the given algorithm.
     * @param algo the current algorithm
     */
    public void setAlgorithm(Algorithm algo){
        initContentPane(algo.getOptionList().getList());
        setSize(new Dimension(300, 50 * rows));
    }

    /**
     * Sets the content of the options frame to the given components.
     * @param optionList a list of components as options
     */
    private void initContentPane(List<Component> optionList){
        contentPane = new JPanel(new GridLayout(0,2,5,5));
        if(MainFrame.SOLCOLOR) contentPane.setBackground(new Color(16643811));

        rows = 0;
        for(Component c : optionList){
            if(MainFrame.SOLCOLOR) c.setBackground(new Color(16643811));
            contentPane.add(c);
            rows++;
        }
        rows = (rows+1)/2;

        setContentPane(contentPane);
        pack();
    }

}
