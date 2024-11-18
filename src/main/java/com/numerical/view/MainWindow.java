package com.numerical.view;

import javax.swing.*;
import java.awt.*;
import com.numerical.logic.Counter;
import com.numerical.logic.PeriodicIncrementers;

public class MainWindow extends JFrame {
    private Counter counter;
    private PeriodicIncrementers incrementers;
    // increments array values
    private long INCREMENTS[] = {
        1,          10,         100,            1000, 
        10000,      100000,     1000000,        10000000, 
        100000000,  1000000000, 10000000000l};
    private int TOTAL_INCREMENTERS = INCREMENTS.length;
    
    private PanelIncrementer panelsConfIncrementer[] = new PanelIncrementer[TOTAL_INCREMENTERS];
    private PanelCount panelCount;

    public MainWindow() {
        counter = new Counter();
        incrementers = new PeriodicIncrementers(counter, TOTAL_INCREMENTERS);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelCount = new PanelCount(counter);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelCount, gbc);
        
        JPanel confPanel = new JPanel();
        confPanel.setPreferredSize(new Dimension(800, 200));
        confPanel.setMinimumSize(new Dimension(800, 200));
        confPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        confPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < TOTAL_INCREMENTERS; i++) {
            panelsConfIncrementer[i] = new PanelIncrementer(INCREMENTS[i], incrementers.getIncrementer(i));
            confPanel.add(panelsConfIncrementer[i], gbc);
            gbc.gridx++;
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(confPanel, gbc);
        
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}

