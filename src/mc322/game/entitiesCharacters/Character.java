package mc322.game.entitiesCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.engine.UnexpectedError;
import mc322.game.GameBrain;
import mc322.game.ImpossibleOriginOrDestiny;
import mc322.game.DoorSelected;
import mc322.game.Entity;
import mc322.game.Room;

public abstract class Character extends Entity{

      protected int solutionIndex;
      protected String solution;
      protected int hp;
      protected int hpMax;
      protected int armor;
      protected int damage;
      protected double legSize;
      protected String name;
      protected boolean dead;

      public Character(int i,int j,double elevation)
      {
            this.i = i;
            this.j = j;
            this.elevation = elevation;
            this.legSize = 0.7;
            this.solutionIndex = 0;
      }
      
      //public abstract void change_state(String state);
      public abstract void attack(int i,int j, Room room);

  	public void die() {
  		//System.out.println(this+ " morreu");
  		dead = true;
  	}
      public void hurt(int damage)
      {
    	  //System.out.println("hp antes: " + hp+ " damage: "+ damage + " armor "+ armor);
    	  //System.out.println(this+" say \"ai\"");
    	  hp = LinearAlgebra.clamp(hp-(damage - ( damage * armor / 100 )),0,hpMax);
    	  //System.out.println("hp: " + hp);
      }


      public String toString()
      {
            return this.name;
      }

      protected String requestSolution(Room room, int iDest, int jDest,boolean ignoreHeroes,boolean enemy) throws ImpossibleOriginOrDestiny, UnexpectedError{
            char map[][] = room.builCharMap(enemy);
            String solution = GameBrain.solveMaze(map,this.i,this.j,iDest,jDest);
            return solution;
      }

      public void change_state(String state){
            this.state = state;
            if(state == "moving"){
                  this.nFrames = this.nFramesMoving;
                  this.velocityAnim = this.velocityAnimMoving;
            }
            if(state == "idle"){
                  this.nFrames = this.nFramesIdle;
                  this.velocityAnim = this.velocityAnimIdle;
            }
      }

      public boolean followPointer(int i, int j, Room room, boolean ignoreHeroes, double timing_keys_move, 
                  boolean movingToPointer,boolean enemy){ // false: para de mover; true se ainda movendo;
//    	  System.out.println("estou em "+this.i+" "+this.j+" vou para "+i+" "+j);
            if(i == this.j && j == this.i) // o j dado eh o i atual e vice versa, ha uma inversao
            	{
            	//System.err.println("quero me mover pra onde eu ja estou");
            	return false;
            	}
            
            try{
            	
//            	System.err.println("solution: "+ solution+"  movingToPointer: "+movingToPointer+" solutionIndex : "+solutionIndex);
//            	if(solution!=null)
//            		System.err.println(" solution.length() "+solution.length());
//            	System.out.println("solucao "+ solution + " moving: "+movingToPointer);
                  if(solution != null && movingToPointer) {
//                	  System.out.println("resolvendo solucao");
                        if(this.move(solution.charAt(solutionIndex), room, timing_keys_move))
                              solutionIndex += 1;
                        if(solutionIndex == solution.length()) {
                              solution = null;
                              return false;
                        }
                  }
//                  System.out.println("to tentando1");
                  if(!movingToPointer){
//                	  System.out.println("pedindo nova solucao");
                        this.solution = requestSolution(room, i, j, ignoreHeroes,enemy);
                        this.solutionIndex = 0;
                  }
//                  System.out.println("to tentando2");
            }
            catch(ImpossibleOriginOrDestiny e){
//                  System.out.println("This place is inaccessable");
                  return false;
            }
            catch(DoorSelected e){
//                  System.out.println("Tentei entrar na porta");
                  if(i==0) followPointer(i+1, j, room, ignoreHeroes, timing_keys_move, movingToPointer,enemy);
                  if(j==0) followPointer(i, j+1, room, ignoreHeroes, timing_keys_move, movingToPointer,enemy);
                  if(i==14)followPointer(i-1, j, room, ignoreHeroes, timing_keys_move, movingToPointer,enemy);
                  if(j==14)followPointer(i, j-1, room, ignoreHeroes, timing_keys_move, movingToPointer,enemy);

                  if(LinearAlgebra.getModulo(i-this.i)+LinearAlgebra.getModulo(j-this.j) == 1){
                        if(i==14)this.move('W',room, timing_keys_move);
                        if(j==0) this.move('A',room, timing_keys_move);
                        if(i==0) this.move('S',room, timing_keys_move);
                        if(j==14)this.move('D',room, timing_keys_move);
                  }
                  solution = null;
                  return false;
            } catch (UnexpectedError e) {
				e.printStackTrace();
			}
            return true;
      }

      public void followHero(Character charac, Room room, boolean ignoreHeroes, double timing_keys_move){
            int j = charac.getPos().getFirst();
            int i = charac.getPos().getSecond();

            char map[][] = room.builCharMap(false);

            if(".MN".indexOf(map[i][j]) != -1) {
                  if(map[i+1][j-1] == 'U'){ i += 1; j -= 1; }
                  if(map[i][j] == 'N') i++;
                  if(map[i][j] == 'M') j++;
            }
            else if(map[i][j] == 'U'){ i += 1; j -= 1; }

            if(i == this.i && j == this.j) return;

            String solution;
			try {
				solution = requestSolution(room, i, j, ignoreHeroes,false);
			} catch (ImpossibleOriginOrDestiny e) {
				//System.out.println("This place is inaccessable");
                return;
			} catch (UnexpectedError e) {
				//e.printStackTrace();
				return;
			}
            //System.out.println("i: " + i +" , " + "j: " + j + "  char: " + map[i][j]);

            if(solution != null && solution.length() > 0)
			        this.move(solution.charAt(0), room, timing_keys_move);
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

    public boolean move(char dir,Room room, double timing_keys_move){ //true: se moveu| false se n moveu
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

    protected boolean verifyMovement(int i, int j, Room room) {
          if(room == null){
                System.out.println("erro: sala e nula");
                return false;
          }
          if(room.isAccessible(i,j,this.elevation,this.legSize,this.updateDir,this)) return true;
          return false;
    }
    
    public boolean getDead()
    {
    	
    	return dead;
    }

}
