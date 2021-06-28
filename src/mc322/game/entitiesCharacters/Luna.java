package mc322.game.entitiesCharacters;

import java.util.ArrayList;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;
import mc322.game.itens.Item;
import mc322.game.Attack;

public class Luna extends Heroes{

      public Luna(int i, int j,double elevation){
            super(i,j,elevation);
            this.name = "Luna";
            this.state = "idle";
            this.typeOfattack = "hit2";

            this.updateDir = 3;
            this.updateFrame = 0;

            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 6;
            this.nFramesMoving = 4;
            this.nFramesAttacking = 11;

            range = 2;
            hpMax = 110;
            hp = hpMax;
            armor = 50;
            damage = 40;
      }


      public void attack(int i, int j, Room room) {
            if(LinearAlgebra.getModulo(i-this.i)>range || LinearAlgebra.getModulo(j-this.j)>range)
                  return;
            else{
                  room.attack(i,j,this.damage, this.typeOfattack);
                  this.change_state("attacking");
            }
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
