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
				if(name.equals("wumpus_door"))
				{
					if(room.hasAllKeys()){
                                    open(false);
                                    room.openWumpusDoor();
                              }

				}
				else
				open(true);
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
		if(name.equals("wumpus_door"))
			renderer_frame = 8;
            if(closed) renderer_frame = 0;
            if(this.initAnimation) renderer_frame = (int)updateFrame%nFrames;
            if(name.equals("wumpus_door"))
            	GameRenderer.drawTile(i-1,j,elevation,name,r,renderer_frame,updateDir,this.color);
            else
                  GameRenderer.drawTile(i,j,elevation,name,r,renderer_frame,updateDir,this.color);
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

      private void open(boolean hasKey)
      {
            initAnimation = true;
            closed = false;
      }

      public boolean getClosed()
      {
            return closed;
      }

      public void setWumpusDoor() {
            name = "wumpus_door";
            this.color = "Black";
            updateDir = 0;
            this.velocityAnim = 3;
            this.nFrames = 9;

      }

}

