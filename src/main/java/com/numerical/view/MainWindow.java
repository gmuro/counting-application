package com.numerical.view;

import javax.swing.*;
import java.awt.*;
import com.numerical.logic.Counter;
import com.numerical.util.MyTimer;

public class MainWindow extends JFrame {
    private JLabel label;
    private Counter counter;
    
    public MainWindow() {
        counter = new Counter();
        label = new JLabel("Count: 0", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);

        MyTimer timer = new MyTimer(this);
        timer.start();
    }
    
    public void updateLabel() {
        counter.increment();
        label.setText("Count: " + counter.getCount());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}

