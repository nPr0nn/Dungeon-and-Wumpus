package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Torch extends Entity{
	
      private String color;
      public Torch (int i, int j, String direction, int elevation, String color){
            this.name = "torch";
            this.i=i;
            this.j=j;
            this.elevation = elevation;
            this.color = color;

            this.updateFrame = 0;
            this.initAnimation = true;
            this.velocityAnim = 8;
            this.nFrames = 3;
	}
	
	public void update(double dt){
            this.updateFrame += this.velocityAnim*dt;
	}

	public void renderer(Renderer r) {
            int renderer_frame = (int) this.updateFrame%nFrames;
		GameRenderer.drawTile(i,j,elevation,name,r,renderer_frame,updateDir,this.color);
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}


