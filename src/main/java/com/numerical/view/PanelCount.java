
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

    public void setCount(long count) {
        label.setText(String.format("%,d", count));
        updateFontSize();
    }

    private void updateFontSize() {
        String fontName = "Arial";
        Font font = new Font(fontName, Font.BOLD, 12);
        int fontSize = 12;
        while (true) {
            font = new Font(fontName, Font.BOLD, fontSize);
            label.setFont(font);
            Dimension textSize = label.getPreferredSize();
            if (textSize.width < getWidth()*0.9 && textSize.height < getHeight()*0.9) {
                fontSize++;
            } else {
                break;
            }
        }
        font = new Font(fontName, Font.BOLD, fontSize + 1);
        label.setFont(font);
    }
}