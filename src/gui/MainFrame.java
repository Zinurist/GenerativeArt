package gui;

import algorithms.Algorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main frame of the application.
 */
public class MainFrame implements Runnable{

    public static MainFrame MF;

    private boolean paused = true;
    private boolean running = false;
    private int waitTime= 25;//in ms
    private int recordCounter = 0;

    //GUI elements
    private JFrame frame;
    private OptionsFrame options;

    private JButton btnStart, btnStop, btnStep, btnOptions, btnSave, btnResize, btnReset;
    private JToggleButton btnRecord;
    private JComboBox<Algorithm> algorithms;
    private JSlider speedSlider;
    private JLabel speedLabel, statusLabel;
    private JPanel contentPane, controlPanel;
    private DrawPanel drawPanel;

    public MainFrame(){
        frame = new JFrame("Generative Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        options = new OptionsFrame();

        btnStep = new JButton("Step");
        btnStep.addActionListener(e -> {
            getSelectedAlgorithm().step();
            drawPanel.repaint();
        });

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> startAnimation());

        btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> stopAnimation());

        btnOptions = new JButton("Options");
        btnOptions.addActionListener(e -> {
            options.setAlgorithm(getSelectedAlgorithm());
            options.setVisible(true);
        });

        btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            //TODO get path
            saveImage("tmp.png");
        });

        btnRecord = new JToggleButton("Record");

        btnResize = new JButton("Resize");
        btnResize.addActionListener(e -> {
            //TODO get width/height
            int width = 1920;
            int height = 1080;
            resetImage(width, height);
        });

        btnReset = new JButton("Reset/Init");
        btnReset.addActionListener(e -> {
            resetImage(-1,-1);
            drawPanel.reset();
        } );

        speedLabel = new JLabel("Speed: "+waitTime+" ms");

        speedSlider = new JSlider(0, 1000, waitTime);
        speedSlider.addChangeListener(e -> {
            waitTime = speedSlider.getValue();
            speedLabel.setText("Speed: " + waitTime + " ms");
        });

        statusLabel = new JLabel("");

        algorithms = new JComboBox<>(Algorithm.getAlgorithmsList());

        drawPanel = new DrawPanel();
        //drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //drawPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        controlPanel = new JPanel(new GridLayout(2,0,5,5));
        controlPanel.add(algorithms);
        //TODO divide algorithms into types?
        controlPanel.add(btnReset);
        controlPanel.add(btnStep);
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);
        controlPanel.add(btnOptions);
        controlPanel.add(btnSave);
        controlPanel.add(btnRecord);
        controlPanel.add(btnResize);
        controlPanel.add(statusLabel);
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

        resetImage(1000, 1000);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    public Point getMouseLocation(){
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point s = drawPanel.getLocationOnScreen();
        p.translate(-s.x,-s.y);
        return p;
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
        //algorithms.setEnabled(paused);
        btnStart.setEnabled(paused);
        btnStop.setEnabled(!paused);
        btnStep.setEnabled(paused);
        //btnOptions.setEnabled(paused);
        btnSave.setEnabled(paused);
        //btnResize.setEnabled(paused);
        btnReset.setEnabled(paused);
    }

    public void startAnimation(){
        paused = false;
        setButtonsEnabled();
    }

    public void stopAnimation(){
        paused = true;
        setButtonsEnabled();
    }

    private void saveImage(String path){
        try {
            File f =  new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            ImageIO.write(Algorithm.IMG, "png", f);
        } catch (Exception e1) {
            statusLabel.setText("Error: "+e1.getMessage());
        }
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

            if(btnRecord.isSelected()){
                //TODO convert to mp4 or whatever
                saveImage("recordings/img"+String.format("%08d", recordCounter)+".png");
                recordCounter++;
            }

            try {
                if(waitTime > 0)
                    Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
