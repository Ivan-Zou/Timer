import timer.RunTimer;

import javax.swing.SwingUtilities;

public class OpenTimer {
    public static void main(String[] args) {
        Runnable timer = new RunTimer();
        SwingUtilities.invokeLater(timer);
    }
}
