package timer.classes;

import timer.misc.Constants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.io.File;

public class Sound {
    public static void play() {
        try {
            Clip clip = AudioSystem.getClip();
            File audioFile = new File(Constants.AUDIO_FILE);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "STOP");
                clip.stop();
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
