package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Door extends Entity{
	
      private String color;
      private Room room;
      private boolean closed;
      public Door (int i, int j, String direction, int elevation, String color,Room room){
            this.name = "door";
            this.i=i;
            this.j=j;
            this.elevation = elevation + 1;
            this.color = color;
            this.room = room;
            this.closed = true;

            this.updateFrame = 0;
            this.initAnimation = true;
            this.velocityAnim = 8;
            this.nFrames = 5;

            if(direction == "east" || direction == "west") this.updateDir = 0; 
            else if(direction == "north" || direction == "south") this.updateDir = 1;
	}
	
	public void update(double dt){
		if(closed)
		{
			initAnimation = false;
			if(!room.getBlocked())
			{
				initAnimation = true;
				closed = false;
			}
				
		}
		if(this.initAnimation){
	          this.updateFrame += this.velocityAnim*dt;
	          if( (int)this.updateFrame > 0 && (int)this.updateFrame % nFrames == 0){
	                this.initAnimation = false;
	          }
        }
	}

	public void renderer(Renderer r) {
		int renderer_frame = 5;
            if(closed) renderer_frame = 0;
            if(this.initAnimation) renderer_frame = (int)updateFrame%nFrames;
		GameRenderer.drawTile(i,j,elevation,name,r,renderer_frame,updateDir,this.color);
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}

