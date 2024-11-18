
package com.numerical.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PanelCount extends JPanel {
    private JLabel label;

    public PanelCount(String texto) {
        setLayout(new BorderLayout());
        label = new JLabel(texto);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize();
            }
        });
        updateFontSize();
    }

    private int prevLength = 0;

    public void setCount(long count) {
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