
package com.numerical.view;

import javax.swing.*;

import com.numerical.logic.Counter;
import com.numerical.logic.CounterListener;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PanelCount extends JPanel {
    private JLabel label = new JLabel("");
    private Counter counter;
    private int prevLength = 0;

    public PanelCount(Counter counter) {
        setLayout(new BorderLayout());
        this.counter = counter;
        counter.setCounterListener(counterListener);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize();
            }
        });
        setCount(counter.getCount());
    }

    private boolean refreshPending = false;

    private CounterListener counterListener = new CounterListener() {
        
        @Override
        public void onChange(Counter counter) {

            if (refreshPending == false) {
                refreshPending = true;
                SwingUtilities.invokeLater(() -> {
                    setCount(counter.getCount());
                    // limit refresh rate
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    refreshPending = false;
                });
            }
        }
    };
    

    private void setCount(long count) {
        String text = String.format("%,d", count);
        if (text.length() > prevLength) {
            prevLength = text.length();
            updateFontSize();
        }

        label.setText(String.format("%,d", count));
    }

    private void updateFontSize() {
        String fontName = "Arial";
        Font font = new Font(fontName, Font.BOLD, 12);
        int fontSize = 12;
        int width = getWidth() * 8 / 10;
        int height = getHeight() * 8 / 10;
        while (true) {
            font = new Font(fontName, Font.BOLD, fontSize);
            label.setFont(font);
            Dimension textSize = label.getPreferredSize();
            if (textSize.width < width && textSize.height < height) {
                fontSize++;
            } else {
                break;
            }
        }
        font = new Font(fontName, Font.BOLD, fontSize + 1);
        label.setFont(font);
    }
}