package com.numerical.logic;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A periodic incrementer for the Counter class.
 */
public class PeriodicIncrementer {
    private final Counter counter;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> future;
    private long increment;
    private long period;

    /**
     * Creates a new PeriodicIncrementer instance.
     *
     * @param counter the Counter instance to increment
     * @param scheduler the ScheduledExecutorService to use for scheduling
     */
    public PeriodicIncrementer(Counter counter, ScheduledExecutorService scheduler) {
        this.counter = counter;
        this.scheduler = scheduler;
    }

    /**
     * Starts the periodic increment of the Counter instance.
     *
     */
    public void start() {
        stop();
        if (period > 0 && increment != 0) {
            future = scheduler.scheduleAtFixedRate(() -> counter.increment(increment), 0, period, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Stops the periodic increment.
     */
    public void stop() {
        if (future != null) {
            future.cancel(true);
            future = null;
        }
    }

    // getters and setters
    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        if (this.period != period) {
            this.period = period;
            start();
        }
    }

}