package mc322.game.entitiesCharacters;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class Eye extends Enemys{

	public Eye(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Eye";
            this.state = "idle";

            this.updateDir = 0;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 4;
            this.nFramesMoving = 4;
            hpMax = 100;
            hp = hpMax;
            armor = 5;
	}
	

	public void attack(int i, int j) {
		
	}

	public void die() {
		
	}

	public void update(double dt) {
            this.updateFrame += this.velocityAnim*dt;
	}

	public void renderer(Renderer r) {
		GameRenderer.drawEnemy(i,j,elevation,name,r, (int)updateFrame%nFrames,this.updateDir,this.state);
		GameRenderer.drawLifeEnemy(i,j,elevation,this.hpMax,this.hp,r);
		
	}
	public void toggleAnimation() {
		
	}


	public void brain() {
		
	}

}
