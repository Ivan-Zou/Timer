package timer;

import timer.gui.TimerWindow;

public class RunTimer implements Runnable {
    @Override
    public void run() {
        new TimerWindow(0, 0, 0);
    }
}
