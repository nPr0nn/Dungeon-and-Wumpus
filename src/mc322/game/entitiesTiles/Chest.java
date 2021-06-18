package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Chest extends Entity{

      public Chest (int i, int j, String direction, int elevation){
            this.name = "chest";
            this.i=i;
            this.j=j;
            this.elevation = elevation;

            this.initAnimation = false;
            this.velocityAnim = 8;
            this.nFrames = 6;

            if(direction == "west-east") this.updateDir = 1;
            this.updateFrame = 0;
	}
	
      public void update(double dt){
            if(this.initAnimation){
                  this.updateFrame += this.velocityAnim*dt;
                  if( (int)this.updateFrame > 0 && (int)this.updateFrame % nFrames == 0){
                        this.initAnimation = false;
                  }
            }
	}

      public void renderer(Renderer r) {
            GameRenderer.drawItem(i,j,elevation,name,r, (int)updateFrame%nFrames, updateDir);
	}

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}
