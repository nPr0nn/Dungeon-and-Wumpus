package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Pillar extends Entity{
	
	private String color;
      public Pillar (int i, int j, String direction, int elevation, String color){
            this.name = "pillar";
		this.i=i;
		this.j=j;
            this.elevation = elevation;
            this.color = color;

            this.initAnimation = false;
            this.velocityAnim = 8;
            this.nFrames = 6;

		if(direction == "north-south") this.updateDir = 1;
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
		GameRenderer.drawTile(i,j,elevation,name,r, 0, 0,this.color);
	}

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}


