package mc322.engine.sfx;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
      Long currentFrame;
      Clip clip;
      String status;
      AudioInputStream audioInputStream;
      String filePath;

      public AudioPlayer(String caminho, boolean loop) throws UnsupportedAudioFileException, 
             IOException, LineUnavailableException {
                   filePath=caminho;
                   audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                   clip = AudioSystem.getClip();
                   clip.open(audioInputStream);
                   if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
      }

      public void play() {
            clip.start();
            status = "play";
      }
      
      public void stop() throws UnsupportedAudioFileException,
             IOException, LineUnavailableException {
                   currentFrame = 0L;
                   clip.stop();
                   clip.close();
      }
      
      public void pause() {
          if (status.equals("pausado")) {
                System.out.println("Esse audio ja esta pausado");
                return;
          }
          this.currentFrame = this.clip.getMicrosecondPosition();
          clip.stop();
          status = "pausado";
      }

      public void resetAudioStream() throws UnsupportedAudioFileException,
             IOException,LineUnavailableException {
                   audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                   clip.open(audioInputStream);
                   clip.loop(Clip.LOOP_CONTINUOUSLY);
      }

      public void resumeAudio() throws UnsupportedAudioFileException,
             IOException, LineUnavailableException {
                   if (status.equals("play")) {
                         System.out.println("Esse audio esta sendo tocado");
                         return;
                   }
                   clip.close();
                   resetAudioStream();
                   clip.setMicrosecondPosition(currentFrame);
                   this.play();
      }
}

