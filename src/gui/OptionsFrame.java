package gui;

import algorithms.Algorithm;

import javax.swing.*;

/**
 * Opens when clicking 'Options' in the main frame. Shows different option fields set by the algorithm.
 */
public class OptionsFrame extends JFrame {

    private Algorithm algo;

    public OptionsFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setAlgorithm(Algorithm algo){
        this.algo = algo;
    }

}
