package mc322.game.itens;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class HealthPotion extends Item{
      
      public HealthPotion(){
            this.name = "potion";
            this.i = 23;
            this.j = -10;
            this.elevation = elevation;

            this.updateFrame = 0;
            this.updateDir = 0; 

            this.initAnimation = true;
            this.velocityAnim = 8;
            this.nFrames = 4;
      }

      @Override
      public void update(double dt) {
		this.updateFrame += this.velocityAnim*dt;
      }	
	
	@Override
	public void renderer(Renderer r) {
            int renderer_frame = (int) this.updateFrame%nFrames;
		GameRenderer.drawBag(this.i, this.j, 0, name, r, renderer_frame, updateDir);
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		return "health potion";
	}

}
