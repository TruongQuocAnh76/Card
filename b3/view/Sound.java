package b3.view;

import javax.sound.sampled.*;
import java.io.File;
import java.nio.channels.ScatteringByteChannel;

public class Sound {
  private Clip backgroundClip;
  private Clip easterEggClip;

  public Sound() {
    try {
      AudioInputStream audioStream =
          AudioSystem.getAudioInputStream(
              getClass().getClassLoader().getResourceAsStream("resource/background.wav"));
      backgroundClip = AudioSystem.getClip();
      backgroundClip.open(audioStream);

      AudioInputStream audioStream2 =
          AudioSystem.getAudioInputStream(
              getClass().getClassLoader().getResourceAsStream("resource/easterEgg.wav"));
      easterEggClip = AudioSystem.getClip();
      easterEggClip.open(audioStream2);
      easterEggClip.addLineListener(
          event -> {
            if (event.getType() == LineEvent.Type.STOP) {
              playBackgroundMusic();
            }
          });

      playBackgroundMusic();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void playBackgroundMusic() {
    backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  public void playEasterEgg() {
    backgroundClip.stop();
    easterEggClip.start();
  }
  public void stop() {
    easterEggClip.stop();
    backgroundClip.stop();
  }
}