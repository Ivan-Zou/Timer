package timer;

public class RunTimer implements Runnable {
    @Override
    public void run() {
        new ChooseTimeWindow();
    }
}
