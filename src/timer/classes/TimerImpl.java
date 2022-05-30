package timer.classes;

import timer.misc.Constants;

public class TimerImpl {
    private int startTime, totalTime;
    private boolean finished;

    public TimerImpl() {
        reset();
    }

    public void reset() {
        startTime = 0;
        totalTime = 0;
        finished = false;
    }

    public void setTotalTime(int hours, int minutes, int seconds) {
        totalTime = (Constants.SECONDS_PER_HOUR * hours) +
                (Constants.SECONDS_PER_MINUTE * minutes) +
                (seconds);
    }

    public void start() {
        startTime = nanoToUnit(System.nanoTime());
    }

    private static int nanoToUnit(long nano) {
        return (int) (nano / Math.pow(10, 9));
    }

    public void pause() {
        int pausedTime = nanoToUnit(System.nanoTime());
        totalTime -= pausedTime - startTime;
    }

    public boolean finished() {
        return finished || totalTime <= 0;
    }

    public String getCurrentTime() {
        return formatTime(getCurrentCountdown());
    }

    private int getCurrentCountdown() {
        int countdown = totalTime - (nanoToUnit(System.nanoTime()) - startTime);
        finished = countdown <= 0;
        return countdown;
    }

    public String getPausedTime() {
        return formatTime(totalTime);
    }

    private String formatTime(int time) {
        StringBuilder timeString = new StringBuilder();
        int seconds = getSeconds(time);
        int minutes = getMinutes(time);
        int hours = getHours(time);

        timeString.append(hours < 10 ? "0" + hours : hours);
        timeString.append(":");
        timeString.append(minutes < 10 ? "0" + minutes : minutes);
        timeString.append(":");
        timeString.append(seconds < 10 ? "0" + seconds : seconds);

        return timeString.toString();
    }

    public static int getSeconds(int timeInSeconds) {
        return  (timeInSeconds -
                (getMinutes(timeInSeconds) * Constants.SECONDS_PER_MINUTE) -
                (getHours(timeInSeconds) * Constants.SECONDS_PER_HOUR));
    }

    public static int getMinutes(int timeInSeconds) {
        return ((timeInSeconds / Constants.SECONDS_PER_MINUTE) -
                getHours(timeInSeconds) * Constants.MINUTES_PER_HOUR);
    }

    public static int getHours(int timeInSeconds) {
        return (timeInSeconds / Constants.SECONDS_PER_HOUR);
    }
}
