package net.ducruetl.UHCAndScenarios.util;

public class Chronometer {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
    }

    public void reset() {
        elapsedTime = 0;
        isRunning = false;
    }

    public long getElapsedMillis() {
        long total = elapsedTime;
        if (isRunning) {
            total += System.nanoTime() - startTime;
        }
        return total / 1_000_000;
    }

    public long getMinutes() {
        return getElapsedMillis() / 60000;
    }

    public long getSeconds() {
        return (getElapsedMillis() / 1000) % 60;
    }
}
