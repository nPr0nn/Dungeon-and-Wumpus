package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Door extends Entity{
	
	private String color;
      public Door (int i, int j, String direction, int elevation, String color){
            this.name = "door";
		this.i=i;
		this.j=j;
            this.elevation = elevation + 1;
            this.color = color;

            this.initAnimation = false;
            this.velocityAnim = 8;
            this.nFrames = 5;

            if(direction == "east" || direction == "west") this.updateDir = 0; 
            else if(direction == "north" || direction == "south") this.updateDir = 1;
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
		GameRenderer.drawTile(i,j,elevation,name,r, (int)updateFrame%nFrames, updateDir,this.color);
	}

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}

