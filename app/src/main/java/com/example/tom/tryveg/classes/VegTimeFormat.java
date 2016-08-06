package com.example.tom.tryveg.classes;

/**
 * Created by tom on 29-Jul-16.
 */
public class VegTimeFormat {
    public int time;
    public TimeUnit timeUnit;

    public VegTimeFormat(TimeUnit timeUnit, int time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }
}
