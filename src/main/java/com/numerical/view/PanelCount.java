
package com.numerical.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PanelCount extends JPanel {
    private JLabel label;

    public PanelCount(String texto) {
        label = new JLabel(texto);
        label.setHorizontalAlignment(JLabel.CENTER); // Ajusta el texto al centro
        add(label); // Agrega el label al panel

        // Configura el layout para que el label ocupe todo el ancho
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);

        // Establece la fuente del label para que se agrande para ocupar todo el alto
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Establece la fuente y el tamaño
        label.setVerticalAlignment(JLabel.CENTER); // Ajusta el texto al centro vertical
        label.setVerticalTextPosition(JLabel.CENTER); // Ajusta el texto al centro vertical

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Actualiza la fuente del label según el alto del panel
                label.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() * 0.8)));
            }
        });
    }

    public void setCount(long count) {
        label.setText(String.valueOf(count));
    }

}