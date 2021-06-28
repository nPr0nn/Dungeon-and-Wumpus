package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.GameBrain;
import mc322.game.Room;

public abstract class Enemys extends Character{

	
      public Enemys(int i, int j,double elevation) {
            super(i, j, elevation);
      }

      public void move(int i, int j,Room room) {
            if(!((LinearAlgebra.getModulo(i-this.i)==1 && this.j==j)||(LinearAlgebra.getModulo(j-this.j)==1 && this.i==i)))
                  return;
            if(verifyMovement(i,j,room)==false)
                  return;

            int lastI = this.i;
            int lastJ = this.j;
            this.i = i;
            this.j = j;
            room.move(lastI,lastJ,i,j,this);
            this.change_state("idle");
      }

      public void hurt(int damage, String typeOfattack){
            if(damage < 0 && typeOfattack == "heal"){
                  super.hurt(-damage, "spell5");
            }
            else super.hurt(damage, typeOfattack);
      }

      public boolean move(char dir,Room room, double timing_keys_move){
            if(timing_keys_move != 0) return false;
            int tI=0;
            int tJ=0;
            int newDir = updateDir;

            switch(dir){
                  case 'A':
                        tI = i;
                        tJ = j-1;
                        newDir = 2;
                        break;
                  case 'S':
                        tI = i-1;
                        tJ = j;
                        newDir = 1;
                        break;
                  case 'D':
                        tI = i;
                        tJ = j+1;
                        newDir = 0;
                        break;
                  case 'W':
                        tI = i+1;
                        tJ = j;
                        newDir = 3;
                        break;
            }
            this.updateDir = newDir;

            move(tI,tJ,room);
            return true;
      }

      public abstract void brain();
      
      protected boolean verifyMovement(int i, int j, Room room) {
            if(room == null){
                  System.out.println("erro: sala e nula");
                  return false;
            }
            if(room.isAccessible(i,j,this.elevation,this.legSize,this.updateDir,this)) return true;
            return false;
      }

	public Pair<Integer, Integer> choseTarget(Room room) {
		char map[][] = room.buildMapEnemyTarget();
		
		return GameBrain.chooseCloserHero(map,this.j,this.i,range);
		
		
	}
}
