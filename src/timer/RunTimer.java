package timer;

import timer.gui.ChooseTimeWindow;

public class RunTimer implements Runnable {
    @Override
    public void run() {
        new ChooseTimeWindow();
    }
}
