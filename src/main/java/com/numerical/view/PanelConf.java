package com.numerical.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.numerical.logic.PeriodicIncrementers;

public class PanelConf extends JPanel{
    private PeriodicIncrementers incrementers;
    private PanelIncrementer panelsConfIncrementer[];
    private final long INCREMENTS[];
    private JCheckBox autoAccelCheckBox;
    private Thread autoAccelThread;
    private boolean autoAccel = false;

    // auto accel thread
    private Runnable autoAccelRunnable = () -> {
        
        while (autoAccel) {
            
            System.out.println("Auto Accel");

            for (int i = 0; i < panelsConfIncrementer.length; i++) {
                if (panelsConfIncrementer[i].incrementSpeed()) {
                    break;
                }
            }

            // randon number betwen 0 and 20
            int rand = (int) (Math.random() * 20);
            int delay = 10 + rand;
            System.out.println("wait " + delay + " seconds");

            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
            }
        }
    };

    private void setEventsListener() {
        autoAccelCheckBox.addActionListener(e -> {
            if (autoAccelCheckBox.isSelected()) {
                System.out.println("Auto Acceleration");

                // disable all panelsConfIncrementer
                for (int i = 0; i < panelsConfIncrementer.length; i++) {
                    panelsConfIncrementer[i].setEnabled(false);
                }

                // run autoAccelRunnable
                autoAccel = true;
                autoAccelThread = new Thread(autoAccelRunnable);
                autoAccelThread.start();

            } else {
                System.out.println("Manual Acceleration");

                // enable all panelsConfIncrementer
                for (int i = 0; i < panelsConfIncrementer.length; i++) {
                    panelsConfIncrementer[i].setEnabled(true);
                }

                // stop autoAccelRunnable
                autoAccel = false;
                autoAccelThread.interrupt();
            }
        });
    }


    // constructor
    public PanelConf(PeriodicIncrementers incrementers, long INCREMENTS[]) {
        this.INCREMENTS = INCREMENTS;
        this.incrementers = incrementers;
        this.panelsConfIncrementer = new PanelIncrementer[INCREMENTS.length];

        GridBagConstraints gbc = new GridBagConstraints();
        
        String title = "Configuration";
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(title));

        this.setPreferredSize(new Dimension(800, 200));
        this.setMinimumSize(new Dimension(800, 200));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        this.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = INCREMENTS.length;
        gbc.weightx = 0;
        gbc.weighty = 0;

        autoAccelCheckBox = new JCheckBox("Auto Acceleration");
        autoAccelCheckBox.setSelected(false);
        this.add(autoAccelCheckBox, gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;

        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < INCREMENTS.length ; i++) {
            panelsConfIncrementer[i] = new PanelIncrementer(INCREMENTS[i], incrementers.getIncrementer(i));
            this.add(panelsConfIncrementer[i], gbc);
            gbc.gridx++;
        }

        setEventsListener();
    }

}
