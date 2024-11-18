package com.numerical.logic;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public void increment(int value) {
        count += value;
    }

    public int getCount() {
        return count;
    }
}

