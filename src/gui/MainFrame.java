package gui;

import algorithms.Algorithm;
import algorithms.EmptyAlgorithm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Main frame of the application.
 */
public class MainFrame implements Runnable{

    private boolean paused = true;
    private boolean running = false;
    private int waitTime= 100;//in ms

    //GUI elements
    private JFrame frame;
    private OptionsFrame options;

    private JButton btnStart, btnStop, btnStep, btnOptions, btnSave, btnResize, btnReset;
    private JComboBox<Algorithm> algorithms;
    private JSlider speedSlider;
    private JLabel speedLabel;
    private JPanel contentPane, controlPanel;
    private DrawPanel drawPanel;

    public MainFrame(){

        frame = new JFrame("Generative Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        options = new OptionsFrame();

        btnStep = new JButton("Step");
        btnStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedAlgorithm().step();
                drawPanel.repaint();
            }
        });

        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = false;
                setButtonsEnabled();
            }
        });

        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = true;
                setButtonsEnabled();
            }
        });

        btnOptions = new JButton("Options");
        btnOptions.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                options.setAlgorithm(getSelectedAlgorithm());
                options.setVisible(true);
            }
        });

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        btnResize = new JButton("Resize");
        btnResize.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO get width/height
                int width=500;
                int height=500;
                resetImage(width,height);
            }
        });

        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetImage(-1,-1);
            }
        });

        speedLabel = new JLabel("Speed: "+waitTime+" ms");

        speedSlider = new JSlider(10, 1000, waitTime);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                waitTime = speedSlider.getValue();
                speedLabel.setText("Speed: " + waitTime + " ms");
            }
        });

        algorithms = new JComboBox<Algorithm>(Algorithm.createAlgorithmsList());

        drawPanel = new DrawPanel();
        //drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //drawPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        controlPanel = new JPanel(new GridLayout(2,0,5,5));
        controlPanel.add(algorithms);
        controlPanel.add(btnStep);
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);
        controlPanel.add(btnOptions);
        controlPanel.add(btnSave);
        controlPanel.add(btnResize);
        controlPanel.add(btnReset);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.setPreferredSize(new Dimension(500, 50));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        contentPane = new JPanel(new BorderLayout(5,5));
        contentPane.add(drawPanel,BorderLayout.CENTER);
        contentPane.add(controlPanel, BorderLayout.NORTH);


        controlPanel.setBackground(new Color(16643811));
        contentPane.setBackground(new Color(16643811));

        setButtonsEnabled();

        resetImage(500, 500);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Resizes drawPanel and image. Image will be emptied and the *current* algorithm will be reset.
     * @param width new width
     * @param height new height
     */
    public void resetImage(int width, int height){
        if(width>0 && height>0) {
            drawPanel.setPreferredSize(new Dimension(width, height));

            Algorithm.IMG = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
        getSelectedAlgorithm().init();

        drawPanel.repaint();
    }

    /**
     * Used to set all buttons en/disabled accordingly to the running-status of the thread/algorithm.
     */
    private void setButtonsEnabled(){
        algorithms.setEnabled(paused);
        btnStart.setEnabled(paused);
        btnStop.setEnabled(!paused);
        btnStep.setEnabled(paused);
        btnOptions.setEnabled(paused);
        btnSave.setEnabled(paused);
        btnResize.setEnabled(paused);
        btnReset.setEnabled(paused);
    }

    private Algorithm getSelectedAlgorithm(){
        return (Algorithm) algorithms.getSelectedItem();
    }

    @Override
    public void run() {
        paused = true;
        running = true;

        while(running){
            while(paused){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            getSelectedAlgorithm().step();
            drawPanel.repaint();

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
