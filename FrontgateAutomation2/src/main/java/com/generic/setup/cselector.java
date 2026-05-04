package com.generic.setup;

/**
 * Selector container matching the pattern from the reference project.
 * Holds a primary selector string and an optional fallback.
 * Format: "css,<value>"  |  "id,<value>"  |  "name,<value>"
 */
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
