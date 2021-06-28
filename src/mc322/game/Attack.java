package mc322.game;

import mc322.engine.Renderer;
import mc322.engine.sfx.AudioManager;
import mc322.game.Entity;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Attack extends Entity{

      private String color;
      private Room room;
      private boolean closed;
      private boolean ended;

      public Attack (int i, int j, String attack, int elevation){
            this.name = attack;
            this.i=i;
            this.j=j;
            this.elevation = elevation;

            this.updateFrame = 0;
            this.initAnimation = true;
            this.velocityAnim = 8;

            if(name.indexOf("spell") != -1) this.nFrames = 11;
            else this.nFrames = 7;
            this.ended = false;
            AudioManager audio = new AudioManager();
            
    	  	audio.playMusic(GameMapTokens.getPathSound(attack),false);
      }

      public void update(double dt){
            if(this.initAnimation){
                  this.updateFrame += this.velocityAnim*dt;
                  if( (int)this.updateFrame > 0 && (int)this.updateFrame % nFrames == 0){
                        this.initAnimation = false;
                        this.ended = true;
                  }
            }
      }

      public boolean ended(){
            return this.ended;
      }

      public void renderer(Renderer r) {
            int renderer_frame = (int) this.updateFrame%nFrames;
            GameRenderer.drawAttack(i,j,elevation,name,r,renderer_frame,updateDir);
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

 }



