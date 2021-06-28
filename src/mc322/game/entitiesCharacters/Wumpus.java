package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;
import mc322.game.exceptions.*;

public class Wumpus extends Enemys{

      public Wumpus(int i, int j,double elevation)
      {
            super(i,j,elevation);
            this.name = "Wumpus";
            this.state = "idle";
            this.typeOfattack = "spell6";

            this.updateDir = 0;
            this.updateFrame = 0;

            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 4;
            this.nFramesMoving = 4;
            this.nFramesAttacking = 11;

            hpMax = 250;
            hp = hpMax;
            armor = 80;
            range = 8;
            damage = 50;
      }


      public void attack(int i,int j, Room room) {
            if(LinearAlgebra.getModulo(i-this.i)>range || LinearAlgebra.getModulo(j-this.j)>range)
                  return;
            else{
                  room.attack(i,j,this.damage, this.typeOfattack);
                  this.change_state("attacking");

            }

      }


      public void update(double dt){
            this.updateFrame += this.velocityAnim*dt;
            super.update(dt);
      }

      public void die() {
            dead = true;
      }

      @Override
      public void renderer(Renderer r) {
            int rendered_frame = (int)updateFrame%nFrames;
            if(rendered_frame == nFrames-1 && state == "attacking"){
                  change_state("idle");
            }
            GameRenderer.drawEnemy(i,j,elevation,name,r, (int)updateFrame%nFrames,this.updateDir,this.state);
            GameRenderer.drawLifeWumpus(i,j,elevation,this.hpMax,this.hp,r);
            super.renderer(r);
      }

      public boolean isWumpusDead(){
            return dead;
      }


      public void toggleAnimation() {

      }


      public void brain() {

      }

}
