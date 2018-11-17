
import java.lang.System;
import java.util.concurrent.TimeUnit;

public class Timer {
    private long startTime;
    private long endTime;

    public Timer(){
        startTime = 0;
        endTime = 0;
    }

    public void startTime(){
        startTime = System.nanoTime();
    }
    public void endTime() {
        endTime = System.nanoTime();
    }
    public String getTime(){
        endTime();
        long totalTime = endTime - startTime;

        return String.format("%02d:%02d",
                TimeUnit.NANOSECONDS.toMinutes(totalTime) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(totalTime)),
                TimeUnit.NANOSECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(totalTime)));
    }

}
