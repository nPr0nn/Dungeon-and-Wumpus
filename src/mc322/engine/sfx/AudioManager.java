package mc322.engine.sfx;

import java.util.Random;


public class AudioManager{
      AudioPlayer musics[];
      int nMusics;

      public AudioManager() {
            this.nMusics = 0;
            this.musics = new AudioPlayer[5];
      }
      
      public void playMusic(String path, boolean loop){
            try{
                  AudioPlayer audioPlayer = new AudioPlayer(path, loop);
                  audioPlayer.play();
                  if(loop){
                        this.musics[this.nMusics]=audioPlayer;
                        this.nMusics+=1;
                  }
            } 
            catch (Exception ex){
                  System.out.println( "That was an error while playing that song" );
                  ex.printStackTrace();
            }  
      }
      public void playMusic(String path){
            this.playMusic(path, false);
      }

      public void stopMusic(){
            try {
                  this.nMusics--;
                  this.musics[this.nMusics].stop();
            }
            catch(Exception ex){
                  System.out.println( "Error while stoping the sound" );
                  ex.printStackTrace();
            }
      }

      public void pauseMusic(){
            try {
                  this.musics[this.nMusics-1].pause();
            }
            catch(Exception ex){
                  System.out.println( "Error while pausing the sound" );
                  ex.printStackTrace();
            }
    	  
      }
      
      public void resumeMusic(){
            try {
                  this.musics[this.nMusics-1].resumeAudio();
            }
            catch(Exception ex){
                  System.out.println( "Error while unpausing the sound" );
                  ex.printStackTrace();
            }
      }
}


