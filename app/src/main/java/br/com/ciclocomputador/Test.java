package br.com.ciclocomputador;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.start();
        while(timer.getElapsedTime() <= TimeUnit.MINUTES.toNanos(1)){
            System.out.print("\r" + timer);
        }
        timer.pause();
        Thread.sleep(20000);
        timer.start();
        while(timer.getElapsedTime() <= TimeUnit.MINUTES.toMillis(2)){
            System.out.print("\r" + timer);
        }
    }

}

class Timer {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public Timer() {
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public void pause() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
        startTime = 0;
        elapsedTime = 0;
    }

    public void reset() {
        startTime = System.nanoTime();
        elapsedTime = 0;
    }

    public long getElapsedTime() {
        if (isRunning) {
            return elapsedTime + (System.nanoTime() - startTime);
        } else {
            return elapsedTime;
        }
    }

    @Override
    public String toString() {
        long totalNanoseconds = getElapsedTime();
        long milliseconds = (totalNanoseconds / 1_000_000) % 1000;
        long totalSeconds = totalNanoseconds / 1_000_000_000;
        long seconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minutes = totalMinutes % 60;
        long hours = totalMinutes / 60;

        return String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds / 10);
    }
}
