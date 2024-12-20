// Copyright (c) 2024 Gustavo Muro
// Licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives
// 4.0 International License.
// To view a copy of this license, visit 
// http://creativecommons.org/licenses/by-nc-nd/4.0/
package com.numerical.logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PeriodicIncrementers {

    // Total incrementers
    public final int TOTAL_INCREMENTERS;
    // array of periodic incrementers
    private final PeriodicIncrementer[] incrementers;

    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    /* constructor */
    public PeriodicIncrementers(Counter counter, int TOTAL_INCREMENTERS) {
        this.TOTAL_INCREMENTERS = TOTAL_INCREMENTERS;
        incrementers = new PeriodicIncrementer[TOTAL_INCREMENTERS];
        for (int i = 0; i < TOTAL_INCREMENTERS; i++) {
            incrementers[i] = new PeriodicIncrementer(counter, scheduler);
        }
    }

    public void start() {
        for (int i = 0; i < TOTAL_INCREMENTERS; i++) {
            incrementers[i].start();
        }
    }

    public void stop() {
        for (int i = 0; i < TOTAL_INCREMENTERS; i++) {
            incrementers[i].stop();
        }
    }

    public PeriodicIncrementer getIncrementer(int index) {
        return incrementers[index];
    }
}
