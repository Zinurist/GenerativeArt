package gui;

import algorithms.Algorithm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JButton btnStart, btnStop, btnStep, btnOptions, btnSave, btnResize;
    private JList<Algorithm> algorithms;
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
                algorithms.getSelectedValue().step(drawPanel.getImage());
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
                options.setAlgorithm(algorithms.getSelectedValue());
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
                //TODO
            }
        });

        speedLabel = new JLabel("Speed: "+waitTime+" ms");

        speedSlider = new JSlider(10, 1000, waitTime);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                waitTime = speedSlider.getValue();
                speedLabel.setText("Speed: "+waitTime + " ms");
            }
        });

        algorithms = createAlgorithmsList();

        drawPanel = new DrawPanel(500,500);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        controlPanel = new JPanel(new GridLayout(2,0,5,5));
        controlPanel.add(algorithms);
        controlPanel.add(btnStep);
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);
        controlPanel.add(btnOptions);
        controlPanel.add(btnSave);
        controlPanel.add(btnResize);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.setPreferredSize(new Dimension(500, 50));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));

        contentPane = new JPanel(new BorderLayout(5,5));
        contentPane.add(drawPanel,BorderLayout.CENTER);
        contentPane.add(controlPanel,BorderLayout.NORTH);
        contentPane.add(new JPanel(),BorderLayout.EAST);
        contentPane.add(new JPanel(),BorderLayout.WEST);
        contentPane.add(new JPanel(),BorderLayout.SOUTH);

        setButtonsEnabled();
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Used to set all buttons en/disabled accordingly to the running-status of the thread/algorithm.
     */
    private void setButtonsEnabled(){
        btnStart.setEnabled(paused);
        btnStop.setEnabled(!paused);
        btnStep.setEnabled(paused);
        btnOptions.setEnabled(paused);
        btnSave.setEnabled(paused);
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

            algorithms.getSelectedValue().step(drawPanel.getImage());
            drawPanel.repaint();

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * Creates a JList of all algorithms.
     */
    public static JList<Algorithm> createAlgorithmsList(){
        return new JList<Algorithm>();
    }
}
