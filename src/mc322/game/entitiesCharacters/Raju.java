package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Raju extends Heroes{


      public Raju(int i, int j,double elevation)
      {
            super(i,j,elevation);
            this.name = "Raju";
            this.state = "idle";
            this.typeOfattack = "hit1";

            this.updateDir = 3;
            this.updateFrame = 0;

            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 5;
            this.velocityAnimMoving = 1;

            this.nFrames = this.nFramesIdle = 6;
            this.nFramesMoving = 4;
            this.nFramesAttacking = 11;
            hpMax = 100;
            hp = hpMax;
            damage = 90;

      }


      public void attack(int i, int j, Room room) {
            if(LinearAlgebra.getModulo(i-this.i)!=0 && LinearAlgebra.getModulo(j-this.j)!=0)
                  return;
            else
                  room.attack(i,j,this.damage, this.typeOfattack);
                  this.change_state("attacking");

      }

      public void update(double dt) {
            this.updateFrame += this.velocityAnim*dt;
            super.update(dt);
      }

      @Override
      public void renderer(Renderer r) {
            int rendered_frame = (int)updateFrame%nFrames;
            if(rendered_frame == nFrames-1 && state == "attacking"){
                  change_state("idle");
            }
            GameRenderer.drawCharacter(i,j,elevation,name,r, rendered_frame,this.updateDir,this.state);
            super.renderer(r);
      }

      @Override
      public void toggleAnimation() {
            // TODO Auto-generated method stub

      }

}
