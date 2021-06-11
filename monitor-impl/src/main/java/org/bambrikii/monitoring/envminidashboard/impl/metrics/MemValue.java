package org.bambrikii.monitoring.envminidashboard.impl.metrics;

public class MemValue {
    private final long freeMemory;
    private final long maxMemory;
    private final long totalMemory;

    public MemValue(
            long freeMemory,
            long maxMemory,
            long totalMemory
    ) {
        this.freeMemory = freeMemory;
        this.maxMemory = maxMemory;
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    @Override
    public String toString() {
        return "MemValue{" + "freeMemory=" + freeMemory + ", maxMemory=" + maxMemory + ", totalMemory=" + totalMemory + '}';
    }
}
