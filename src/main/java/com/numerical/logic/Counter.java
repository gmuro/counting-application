package com.numerical.logic;

public class Counter {
    private long count;
    
    // listener for the counter increment event
    private CounterListener counterListener;

    public Counter() {
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public void increment(long value) {
        count += value;
        if (counterListener != null) {
            counterListener.onIncrement(this);
        }
    }

    public long getCount() {
        return count;
    }

    public void setCounterListener(CounterListener counterListener) {
        this.counterListener = counterListener;
    }

}

