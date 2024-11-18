package com.numerical.util;

import javax.swing.*;
import com.numerical.view.MainWindow;

public class MyTimer {
    private MainWindow window;

    public MyTimer(MainWindow window) {
        this.window = window;
    }

    public void start() {
        Timer timer = new Timer(1000, e -> window.updateLabel());
        timer.start();
    }
}

