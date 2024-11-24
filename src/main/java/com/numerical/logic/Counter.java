// Copyright (c) 2024 Gustavo Muro
// Licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives
// 4.0 International License.
// To view a copy of this license, visit 
// http://creativecommons.org/licenses/by-nc-nd/4.0/
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
            counterListener.onChange(this);
        }
    }

    public long getCount() {
        return count;
    }

    public void setCounterListener(CounterListener counterListener) {
        this.counterListener = counterListener;
    }

    public void setCount(int i) {
        this.count = i;
        if (counterListener != null) {
            counterListener.onChange(this);
        }
    }

}

