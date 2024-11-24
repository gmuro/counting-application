// Copyright (c) 2024 Gustavo Muro
// Licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives
// 4.0 International License.
// To view a copy of this license, visit 
// http://creativecommons.org/licenses/by-nc-nd/4.0/
package com.numerical.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.numerical.logic.Counter;
import com.numerical.logic.PeriodicIncrementers;

public class PanelConf extends JPanel{
    private PeriodicIncrementers incrementers;
    private PanelIncrementer panelsConfIncrementer[];
    private final long INCREMENTS[];
    private JCheckBox autoAccelCheckBox;
    private JButton zerButton;
    private JButton accelToZeroButton;
    private Thread autoAccelThread;
    private boolean autoAccel = false;
    private Counter counter;

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

        zerButton.addActionListener(e -> {

            int ret = JOptionPane.showConfirmDialog(this, "Are you sure?", "Count to zero", JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.YES_OPTION) {
                counter.setCount(0);
            } 
        });

        accelToZeroButton.addActionListener(e -> {

            int ret = JOptionPane.showConfirmDialog(this, "Are you sure?", "Accelerate to zero", JOptionPane.YES_NO_OPTION);

            if (ret == JOptionPane.YES_OPTION) {
                for (int i = 0; i < panelsConfIncrementer.length; i++) {
                    panelsConfIncrementer[i].accelToZero();
                }
            }
        });
    }


    // constructor
    public PanelConf(PeriodicIncrementers incrementers, long INCREMENTS[], Counter counter) {
        this.counter = counter;
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
        
        /* auto accel checkbox  */
        autoAccelCheckBox = new JCheckBox("Auto Acceleration");
        autoAccelCheckBox.setSelected(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        this.add(autoAccelCheckBox, gbc);

        /* set zero buttom */
        zerButton = new JButton("Zero");
        gbc.gridx++;
        this.add(zerButton, gbc);

        /* accel to zero button */
        accelToZeroButton = new JButton("Accel To Zero");
        gbc.gridx++;
        this.add(accelToZeroButton, gbc);
        
        /* panel for increments */
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc.gridy++;
        gbc.gridwidth = gbc.gridx+1;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(panel, gbc);


        /* panelsConfIncrementer */
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < INCREMENTS.length ; i++) {
            panelsConfIncrementer[i] = new PanelIncrementer(INCREMENTS[i], incrementers.getIncrementer(i));
            panel.add(panelsConfIncrementer[i], gbc);
            gbc.gridx++;
        }

        setEventsListener();
    }

}
