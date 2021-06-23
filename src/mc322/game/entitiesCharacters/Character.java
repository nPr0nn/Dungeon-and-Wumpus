package mc322.game.entitiesCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
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

      public Character(int i,int j,double elevation)
      {
            this.i = i;
            this.j = j;
            this.elevation = elevation;
            this.legSize = 0.7;
            this.solutionIndex = 0;
      }

      protected double legSize;
      protected String name;

      //public abstract void change_state(String state);
      public abstract void attack(int i,int j);
      public abstract void move(int i, int j, Room room);
      public abstract boolean move(char dir, Room room, double timing_keys_move);
      public abstract void die();
      public void hurt(int damage)
      {
    	  //System.out.println("hp antes: " + hp+ " damage: "+ damage + " armor "+ armor);
    	  hp -= (damage - ( damage * armor / 100 ));
    	  //System.out.println("hp: " + hp);
      }


      public String toString()
      {
            return this.name;
      }

      protected abstract boolean verifyMovement(int i, int j, Room room);

      protected String requestSolution(Room room, int iDest, int jDest,boolean ignoreHeroes){
            char map[][] = room.builCharMap();
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
                  boolean movingToPointer){

            if(i == this.i && j == this.j) return false;

            try{
                  if(solution != null && movingToPointer && solutionIndex < solution.length() ) {
                        //System.out.println(solutionIndex);
                        if(this.move(solution.charAt(solutionIndex), room, timing_keys_move))
                              solutionIndex += 1;
                        if(solutionIndex == solution.length()-1) {
                              solution = null;
                              return false;
                        }
                  }
                  if(!movingToPointer){
                        this.solution = requestSolution(room, i, j, ignoreHeroes);
                        this.solutionIndex = 0;
                  }
            }
            catch(ImpossibleOriginOrDestiny e){
                  System.out.println("This place is inaccessable");
                  return false;
            }
            catch(DoorSelected e){
                  System.out.println("Tentei entrar na porta");
                  if(i==0) followPointer(i+1, j, room, ignoreHeroes, timing_keys_move, movingToPointer);
                  if(j==0) followPointer(i, j+1, room, ignoreHeroes, timing_keys_move, movingToPointer);
                  if(i==14)followPointer(i-1, j, room, ignoreHeroes, timing_keys_move, movingToPointer);
                  if(j==14)followPointer(i, j-1, room, ignoreHeroes, timing_keys_move, movingToPointer);

                  if(LinearAlgebra.getModulo(i-this.i)+LinearAlgebra.getModulo(j-this.j) == 1){
                        if(i==14)this.move('W',room, timing_keys_move);
                        if(j==0) this.move('A',room, timing_keys_move);
                        if(i==0) this.move('S',room, timing_keys_move);
                        if(j==14)this.move('D',room, timing_keys_move);
                  }
                  return true;
            }
            return true;
      }

      public void followHero(Character charac, Room room, boolean ignoreHeroes, double timing_keys_move){
            int i = charac.getPos().getFirst();
            int j = charac.getPos().getSecond();

            char map[][] = room.builCharMap();

            if(".MN".indexOf(map[i][j]) != -1) {
                  if(map[i+1][j-1] == 'U'){ i += 1; j -= 1; }
                  if(map[i][j] == 'N') i++;
                  if(map[i][j] == 'M') j++;
            }
            else if(map[i][j] == 'U'){ i += 1; j -= 1; }

            if(i == this.i && j == this.j) return;

            String solution = requestSolution(room, i, j, ignoreHeroes);
            System.out.println("i: " + i +" , " + "j: " + j + "  char: " + map[i][j]);

            try{
                  if(solution != null && solution.length() > 0)
                        this.move(solution.charAt(0), room, timing_keys_move);
            }
            catch(ImpossibleOriginOrDestiny e){
                  System.out.println("This place is inaccessable");
                  return;
            }
      }

}
