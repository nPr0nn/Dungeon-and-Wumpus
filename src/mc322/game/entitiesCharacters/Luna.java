package mc322.game.entitiesCharacters;

import java.util.ArrayList;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;
import mc322.game.itens.Item;

public class Luna extends Heroes{

	public Luna(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Luna";
            this.state = "idle";

            this.updateDir = 3;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 6;
            this.nFramesMoving = 4;
            hpMax = 110;
            hp = hpMax;
            armor = 50;
            damage = 20;
	}
	

	public void attack(int i, int j, Room room) {
		if(LinearAlgebra.getModulo(i-this.i)>1 || LinearAlgebra.getModulo(j-this.j)>1)
			return;
		else
			room.atack(i,j,this.damage);
	}


	public void update(double dt) {
		if(hp<=0)
			this.die();
            this.updateFrame += this.velocityAnim*dt;
	}

	@Override
	public void renderer(Renderer r) {
        if(this.selected == 1) super.renderer(r);
		GameRenderer.drawCharacter(i,j,elevation,name,r, (int)updateFrame%nFrames,this.updateDir,this.state);
		GameRenderer.drawLife(0,0,0,this.hpMax,this.hp,r);
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

}
