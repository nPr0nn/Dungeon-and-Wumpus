package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;

public class SafeZone extends Entity{

	public SafeZone(int i, int j){
		this.i = i;
		this.j = j;
	}
	
	public void update(double dt) {
		
	}

	public void renderer(Renderer r) {
	
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}
