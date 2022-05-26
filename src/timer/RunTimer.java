package timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunTimer implements Runnable {
    @Override
    public void run() {
        JFrame frame = new JFrame("Timer");
        frame.setLocation(Constants.WINDOW_LOCATION_X, Constants.WINDOW_LOCATION_Y);
        frame.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        JPanel timePanel = new JPanel();
        frame.add(timePanel, BorderLayout.CENTER);
        JLabel time = new JLabel("00:00:00");
        time.setFont(new Font(null, Font.PLAIN, Constants.TIME_FONT_SIZE));
        Window timer = new Window(time);
        timePanel.add(time);

        JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.SOUTH);

        JButton chooseTime = new JButton("Choose Time");
        chooseTime.setFocusable(false);
        chooseTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        controlPanel.add(chooseTime);

        // Spacer
        controlPanel.add(Box.createRigidArea(new Dimension(Constants.SPACER_WIDTH, 0)));

        JButton cancel = new JButton("           ");
        cancel.setFocusable(false);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        controlPanel.add(cancel);

        // Spacer
        controlPanel.add(Box.createRigidArea(new Dimension(Constants.SPACER_WIDTH, 0)));

        JButton startPauseResume = new JButton("           ");
        startPauseResume.setFocusable(false);
        startPauseResume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        controlPanel.add(startPauseResume);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
