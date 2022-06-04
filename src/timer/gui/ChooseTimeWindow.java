package timer.gui;

import timer.misc.Constants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class ChooseTimeWindow extends JFrame {
    public ChooseTimeWindow() {
        final Integer[] hourOptions = new Integer[24];
        final Integer[] minuteOptions = new Integer[60];
        final Integer[] secondOptions = new Integer[60];
        for (int i = 0; i < secondOptions.length; i++) {
            if (i < 24) {
                hourOptions[i] = i;
            }
            minuteOptions[i] = i;
            secondOptions[i] = i;
        }

        final JPanel timePanel = new JPanel();
        final JLabel hourLabel = new JLabel(" Hour(s): ");
        final JComboBox<Integer> hourList = new JComboBox<>(hourOptions);
        final JLabel minuteLabel = new JLabel(" Minute(s): ");
        final JComboBox<Integer> minuteList = new JComboBox<>(minuteOptions);
        final JLabel secondLabel = new JLabel(" Second(s): ");
        final JComboBox<Integer> secondList = new JComboBox<>(secondOptions);

        hourList.setFocusable(false);
        minuteList.setFocusable(false);
        secondList.setFocusable(false);

        timePanel.add(hourLabel);
        timePanel.add(hourList);
        timePanel.add(minuteLabel);
        timePanel.add(minuteList);
        timePanel.add(secondLabel);
        timePanel.add(secondList);

        add(timePanel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel();
        // OK Button
        final JButton ok = new JButton("OK");
        ok.setFocusable(false);
        ok.addActionListener(actionEvent -> {
            Integer selectedHours = (Integer) hourList.getSelectedItem();
            Integer selectedMinutes = (Integer) minuteList.getSelectedItem();
            Integer selectedSeconds = (Integer) secondList.getSelectedItem();

            int hours = selectedHours != null ? selectedHours : 0;
            int minutes = selectedMinutes != null ? selectedMinutes : 0;
            int seconds = selectedSeconds != null ? selectedSeconds : 0;

            new TimerWindow(hours, minutes, seconds);
            setVisible(false);
        });

        buttonPanel.add(ok);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setResizable(false);
        setTitle("Choose Time");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocation(Constants.POPUP_LOCATION_X, Constants.POPUP_LOCATION_Y);
        setPreferredSize(new Dimension(Constants.POPUP_WIDTH, Constants.POPUP_HEIGHT));
    }
}
