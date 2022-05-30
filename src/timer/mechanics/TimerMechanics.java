package timer.mechanics;

import timer.classes.Sound;
import timer.classes.TimerImpl;
import timer.misc.Constants;
import timer.misc.Status;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerMechanics {
    private final JLabel time;
    private final JButton chooseTime, start, cancel;
    private final TimerImpl timerImpl;
    private boolean clickedStart, clickedPause;

    private Status status;

    public TimerMechanics(JLabel time, JButton chooseTime, JButton start, JButton cancel) {
        this.time = time;
        this.chooseTime = chooseTime;
        this.start = start;
        this.cancel = cancel;
        timerImpl = new TimerImpl();
        reset();

        Timer timer = new Timer(Constants.REFRESH_RATE, actionEvent -> {
            if (isCanceled()) {
                reset();
            } else if (isFinished()) {
                setButtonText();
                reset();
                Sound.play();
            } else if (isRunning()) {
                run();
            } else if (isPaused()) {
                pause();
            }
        });

        timer.start();
    }

    private void reset() {
        time.setText("00:00:00");
        timerImpl.reset();
        cancelStatus();
        clickedStart = false;
        clickedPause = false;
    }

    public boolean isCanceled() {
        return status == Status.CANCELED;
    }

    public boolean isFinished() {
        return timerImpl.finished();
    }

    public void setButtonText() {
        String chooseTimeStr = timeSelected() ? Constants.LONG_BLANK_STRING : "Choose Time";
        chooseTime.setText(chooseTimeStr);
        String startStr = timeSelected() ? "Start" : Constants.SHORT_BLANK_STRING;
        start.setText(startStr);
        cancel.setText(Constants.SHORT_BLANK_STRING);
    }

    public boolean isRunning() {
        return status == Status.RUNNING;
    }

    private void run() {
        if (!clickedStart) {
            timerImpl.start();
            clickedStart = true;
            clickedPause = false;
        }
        time.setText(timerImpl.getCurrentTime());
    }

    public boolean isPaused() {
        return status == Status.PAUSED;
    }

    private void pause() {
        if (!clickedPause) {
            timerImpl.pause();
            clickedStart = false;
            clickedPause = true;
        }
        time.setText(timerImpl.getPausedTime());
    }

    public void setTime(int hours, int minutes, int seconds) {
        timerImpl.setTotalTime(hours, minutes, seconds);
        time.setText(timerImpl.getPausedTime());
        status = timeSelected() ? Status.TIME_SELECTED : Status.CANCELED;
    }

    public boolean timeSelected() {
        return !timerImpl.finished();
    }

    public boolean isTimeSelected() {
        return status == Status.TIME_SELECTED;
    }

    public void cancelStatus() {
        status = Status.CANCELED;
    }

    public void runningStatus() {
        status = Status.RUNNING;
    }

    public void pausedStatue() {
        status = Status.PAUSED;
    }
}
