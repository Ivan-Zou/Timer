package timer.gui;

import timer.RunTimer;
import timer.mechanics.TimerMechanics;
import timer.misc.Constants;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;


public class TimerWindow extends JFrame {
    public TimerWindow(int hours, int minutes, int seconds) {
        setTitle("Timer");
        setLocation(Constants.WINDOW_LOCATION_X, Constants.WINDOW_LOCATION_Y);
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        JPanel timePanel = new JPanel();
        add(timePanel, BorderLayout.CENTER);

        JLabel time = new JLabel("00:00:00");
        time.setFont(new Font(null, Font.PLAIN, Constants.TIME_FONT_SIZE));
        timePanel.add(time);

        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        JButton chooseTime = new JButton();
        JButton startPauseResume = new JButton();
        JButton cancel = new JButton();

        TimerMechanics timer = new TimerMechanics(time, chooseTime, startPauseResume, cancel);
        timer.setTime(hours, minutes, seconds);
        timer.setButtonText();

        // Choose Time Button
        chooseTime.setFocusable(false);
        chooseTime.addActionListener(actionEvent -> {
            if (timer.isCanceled()) {
                setVisible(false);
                SwingUtilities.invokeLater(new RunTimer());
            }
        });

        // Cancel Button
        cancel.setFocusable(false);
        cancel.addActionListener(actionEvent -> {
            if (timer.isRunning() || timer.isPaused()) {
                chooseTime.setText("Choose Time");
                startPauseResume.setText(Constants.SHORT_BLANK_STRING);
                cancel.setText(Constants.SHORT_BLANK_STRING);
                timer.cancelStatus();
            }
        });

        // Start/Pause/Resume Button
        startPauseResume.setFocusable(false);
        startPauseResume.addActionListener(actionEvent -> {
            if (timer.isTimeSelected() || timer.isPaused()) {
                cancel.setText("Cancel");
                startPauseResume.setText("Pause");
                timer.runningStatus();
            } else if (timer.isRunning()) {
                cancel.setText("Cancel");
                startPauseResume.setText("Resume");
                timer.pausedStatue();
            }
        });

        controlPanel.add(chooseTime);
        controlPanel.add(Box.createRigidArea(new Dimension(Constants.SPACER_WIDTH, 0)));
        controlPanel.add(cancel);
        controlPanel.add(Box.createRigidArea(new Dimension(Constants.SPACER_WIDTH, 0)));
        controlPanel.add(startPauseResume);


        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
