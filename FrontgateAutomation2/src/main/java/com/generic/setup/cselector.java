package com.generic.setup;


public class cselector {

    public final String primary;
    public final String fallback;

    public cselector(String primary, String fallback) {
        this.primary  = primary;
        this.fallback = fallback;
    }

    public cselector(String primary) {
        this.primary  = primary;
        this.fallback = primary;
    }

    @Override
    public String toString() {
        return primary;
    }
}
