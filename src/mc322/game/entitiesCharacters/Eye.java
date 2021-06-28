package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;
import mc322.game.exceptions.*;

public class Eye extends Enemys{

	public Eye(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Eye";
            this.state = "idle";
            this.typeOfattack = "spell3";

            this.updateDir = 0;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 4;
            this.nFramesMoving = 4;
            this.nFramesAttacking = 11;

            hpMax = 100;
            hp = hpMax;
            armor = 5;
            range = 5;
            damage = 30;
	}
	

	public void attack(int i,int j, Room room) {
		if(LinearAlgebra.getModulo(i-this.i)>range || LinearAlgebra.getModulo(j-this.j)>range)
			return;
		else
			room.attack(i,j,this.damage,this.typeOfattack);
                  this.change_state("attacking");

	}


	public void update(double dt) throws Victory {
            this.updateFrame += this.velocityAnim*dt;
            super.update(dt);
            //if(hp<=0) this.die();
	}

      @Override
      public void renderer(Renderer r) {
            int rendered_frame = (int)updateFrame%nFrames;
            if(rendered_frame == nFrames-1 && state == "attacking"){
                  change_state("idle");
            }
            GameRenderer.drawEnemy(i,j,elevation,name,r, (int)updateFrame%nFrames,this.updateDir,this.state);
            GameRenderer.drawLifeEnemy(i,j,elevation,this.hpMax,this.hp,r);
            GameRenderer.drawCharacter(i,j,elevation,name,r, rendered_frame,this.updateDir,this.state);
            super.renderer(r);
      }




	public void toggleAnimation() {
		
	}


	public void brain() {
		
	}

}
