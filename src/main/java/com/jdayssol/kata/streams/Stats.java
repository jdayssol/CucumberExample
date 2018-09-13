package com.jdayssol.kata.streams;

public class Stats {

    private long count;
    private long sum;
    private int min;
    private int max;
    private double avg;

    public Stats(final long count,
                 final long sum,
                 final int min,
                 final int max,
                 final double avg) {
        this.count = count;
        this.sum = sum;
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public final long getCount() {
        return count;
    }

    public final long getSum() {
        return sum;
    }

    public final int getMin() {
        return min;
    }

    public final int getMax() {
        return max;
    }

    public final double getAverage() {
        return avg;
    }

}
