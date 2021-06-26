package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;
import mc322.game.Victory;

public class Wumpus extends Enemys{

	public Wumpus(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Wumpus";
            this.state = "idle";

            this.updateDir = 0;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 4;
            this.nFramesMoving = 4;
            hpMax = 200;
            hp = hpMax;
            armor = 10;
            range = 15;
            damage = 40;
	}
	

	public void attack(int i,int j, Room room) {
		if(LinearAlgebra.getModulo(i-this.i)>range || LinearAlgebra.getModulo(j-this.j)>range)
			return;
		else
			room.atack(i,j,this.damage);
	}


	public void update(double dt){
		if(hp<=0)
			this.die();
            this.updateFrame += this.velocityAnim*dt;
            
	}

 	public void die() throws Victory {
 		dead = true;
  		throw new Victory();
  		
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
