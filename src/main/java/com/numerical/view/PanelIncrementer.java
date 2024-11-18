package com.numerical.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.numerical.logic.PeriodicIncrementer;

import java.awt.GridLayout;

public class PanelIncrementer extends JPanel {
    private JSlider slider;
    private JLabel labelPeriod;
    static final int TOTAL_POSITIONS = 5;
    private PeriodicIncrementer incrementer;

    public PanelIncrementer(long increment, PeriodicIncrementer incrementer) {
        this.incrementer = incrementer;

        this.incrementer.setIncrement(increment);

        slider = new JSlider(0, TOTAL_POSITIONS-1, 0);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        
        labelPeriod = new JLabel("Diabled");

        // set border panel
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("+" + increment));

        // set vertical slider
        slider.setOrientation(JSlider.VERTICAL);
        slider.setPaintTicks(true);

        // add listener to slider
        slider.addChangeListener(e -> {
            int position = slider.getValue();
            switch (position) {
                case 0:
                    labelPeriod.setText("Disabled");
                    incrementer.stop();
                    break;
                case 1:
                    labelPeriod.setText("x1");
                    incrementer.setPeriod(1000);
                    break;
                case 2:
                    labelPeriod.setText("x10");
                    incrementer.setPeriod(100);

                    break;
                case 3:
                    labelPeriod.setText("x100");
                    incrementer.setPeriod(10);
                    break;
                case 4:
                    labelPeriod.setText("x1000");
                    incrementer.setPeriod(1);
                    break;
            }
            if (position != 0)
                incrementer.start();
        });

        this.setLayout(new GridLayout(2, 1));

        this.add(slider);
        this.add(labelPeriod);

        this.setVisible(true);
    }
}
