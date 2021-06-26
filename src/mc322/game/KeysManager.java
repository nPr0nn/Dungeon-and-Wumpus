package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import java.util.Random;

public abstract class KeysManager {

      protected int iPointer;
      protected int jPointer;

      public static boolean keys_movement(GameContainer gc, Dungeon dungeon, double timing_keys_move){
    	  
            if(gc.getInput().isKey('W') || gc.getInput().isKey(38)){
//            	if(dungeon.getState().equals("Combat"))
//          		  return false;
                  dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move,false);
                  return true;
            }
            if(gc.getInput().isKey('A') || gc.getInput().isKey(37)){
//            	if(dungeon.getState().equals("Combat"))
//          		  return false;
                  dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move,false);
                  return true;
            }
            if(gc.getInput().isKey('S') || gc.getInput().isKey(40)){
//            	if(dungeon.getState().equals("Combat"))
//          		  return false;
                  dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move,false);
                  return true;
            }
            if(gc.getInput().isKey('D') || gc.getInput().isKey(39)){
//            	if(dungeon.getState().equals("Combat"))
//          		  return false;
                  dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move,false);
                  return true;
            }
            GameRenderer.change_animation_state("idle", dungeon);
            return false;
      }


      public static void keys_action(GameContainer gc, Dungeon dungeon, Bag bag){

            if(gc.getInput().isKeyDown('E')){
                  Heroes player = dungeon.getCurrentRoom().getPlayer();
                  player.toggleSelect();
                  if(player instanceof Luna || player == null){
                        player = dungeon.getCurrentRoom().getMilo();
                  }
                  else if(player instanceof Milo){
                        player = dungeon.getCurrentRoom().getRaju();
                  }
                  else if(player instanceof Raju){
                        player = dungeon.getCurrentRoom().getZe();
                  }
                  else if(player instanceof Ze){
                        player = dungeon.getCurrentRoom().getLuna();
                  }
                  else System.out.println("Player error while changing character");
                  dungeon.getCurrentRoom().setPlayer(player);
                  player.toggleSelect();
            }

            if(gc.getInput().isKeyDown('Q')){
                  Heroes player = dungeon.getCurrentRoom().getPlayer();
                  player.toggleSelect();
                  if(player instanceof Luna || player == null){
                        player = dungeon.getCurrentRoom().getZe();
                  }
                  else if(player instanceof Milo){
                        player = dungeon.getCurrentRoom().getLuna();
                  }
                  else if(player instanceof Raju){
                        player = dungeon.getCurrentRoom().getMilo();
                  }
                  else if(player instanceof Ze){
                        player = dungeon.getCurrentRoom().getRaju();
                  }
                  else System.out.println("Player error while changing character");
                  dungeon.getCurrentRoom().setPlayer(player);
                  player.toggleSelect();
            }

            if(gc.getInput().isKeyDown('T')){
                  dungeon.toggleFollow();
            }

            if(gc.getInput().isKeyDown('O')){
                  dungeon.getCurrentRoom().open();
            }

            if(gc.getInput().isKeyDown(' ')){
                  dungeon.getCurrentRoom().getPlayer().use(dungeon.getCurrentRoom(),bag);
            }

            if(gc.getInput().isKeyDown('I')){
                  System.out.println("Bag: "+bag);
                  dungeon.getCurrentRoom().getPlayer().printStatus();
            }
            if(gc.getInput().isKeyDown('G')){
            	dungeon.getCurrentRoom().getPlayer().hurt(20);
          }
      }

      public static void keys_game_flow(GameContainer gc, GameManager game, Menu menu){
            if(gc.getInput().isKeyDown('P')){
                  game.togglePause();
            }
            if(gc.getInput().isKeyDown('M')){
                  if(menu.getState() != "map")
                        menu.setState("map");
                  else
                        menu.setState("game");
            }
            
            
            if(gc.getInput().isKeyDown('K')){
            	System.out.println(game.dungeon.getCurrentRoom().getPlayer().getPos());
          }
      }

      public static Pair<Integer, Integer> verifyMouseClick(GameContainer gc, Dungeon dungeon,Bag bag,boolean seeEnemy){

            if(gc.getInput().wasClicked()){
                  Pair<Integer, Integer> posClick = gc.getInput().getClick();
                  
                  
                  //System.out.println("x: "+posClick.getFirst()+" y: "+posClick.getSecond());
                  posClick = LinearAlgebra.toCartesianas(posClick);

                  int i = posClick.getSecond() - 475+32;
                  int j = posClick.getFirst() + 332+32;
                  i /= 32;
                  j /= 32;

                  if( i > 14 || i < 0 || j < 0 || j > 14 )
            	  {
                	  posClick = LinearAlgebra.toIsometrica(posClick);
                	  if(bag!=null)
                		  bag.click(posClick.getSecond(),posClick.getFirst(),dungeon);
                	  return null;
            	  }

                  char map[][] = dungeon.getCurrentRoom().builCharMap(seeEnemy);
                  
                  if(".MN".indexOf(map[i][j]) != 1) {
                	  if(j>0 && i<14)
                        if(map[i+1][j-1] == 'U'){
                              //System.out.print("palanque: ");
                              i += 1;
                              j -= 1;
                        }
                        //else System.out.print("chao: ");
                  }
                  else if(map[i][j] == 'U'){
                        //System.out.print("palanque: ");
                        i += 1;
                        j -= 1;

                  }

                  //System.out.println("i: " + i + ", j: " + j);
                  return Pair.of(i, j);
            }

            return null;
      }
}
