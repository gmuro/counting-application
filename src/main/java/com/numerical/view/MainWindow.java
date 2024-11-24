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
    
    private PanelCount panelCount;
    private PanelConf panelConf;

    public MainWindow() {
        counter = new Counter();
        incrementers = new PeriodicIncrementers(counter, INCREMENTS.length);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelCount = new PanelCount(counter);
        panelConf = new PanelConf(incrementers, INCREMENTS, counter);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelCount, gbc);

        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(panelConf, gbc);

        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}

