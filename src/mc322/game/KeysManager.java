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
                  dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move);
                  return false;
            }
            if(gc.getInput().isKey('A') || gc.getInput().isKey(37)){
                  dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move);
                  return false;
            }
            if(gc.getInput().isKey('S') || gc.getInput().isKey(40)){
                  dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move);
                  return false;
            }
            if(gc.getInput().isKey('D') || gc.getInput().isKey(39)){
                  dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom(), timing_keys_move);
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon, timing_keys_move);
                  return false;
            }
            GameRenderer.change_animation_state("idle", dungeon);
            return true;
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
      }

      public static Pair<Integer, Pair<Integer, Integer>> verifyMouseClick(GameContainer gc, Dungeon dungeon){
            int clicked = 0;

            if(gc.getInput().wasClicked()){
                  clicked = 1;
                  Pair<Integer, Integer> posClick = gc.getInput().getClick();
                  //System.out.println("x: "+posClick.getFirst()+" y: "+posClick.getSecond());
                  posClick = LinearAlgebra.toCartesianas(posClick);

                  int i = posClick.getSecond() - 475+32;
                  int j = posClick.getFirst() + 332+32;
                  i /= 32;
                  j /= 32;

                  if( i > 14 || i < 0 || j < 0 || j > 14 ) return null;

                  char map[][] = dungeon.getCurrentRoom().builCharMap();
                  
                  if(".MN".indexOf(map[i][j]) != 1) {
                        if(map[i+1][j-1] == 'U'){
                              System.out.print("palanque: ");
                              i += 1;
                              j -= 1;
                        }
                        else System.out.print("chao: ");
                  }
                  else if(map[i][j] == 'U'){
                        System.out.print("palanque: ");
                        i += 1;
                        j -= 1;

                  }

                  System.out.println("i: " + i + ", j: " + j);
                  return Pair.of(clicked, Pair.of(i, j));
            }

            return Pair.of(clicked, null);
      }

      public static boolean mouse_action(GameContainer gc, Dungeon dungeon, double timing_keys_move, 
                  boolean movingToPointer, Pair<Integer, Integer> p){

            Room cRoom = dungeon.getCurrentRoom();
            Heroes player = cRoom.getPlayer();
            boolean mov = player.followPointer(p.getFirst(),p.getSecond(),cRoom,true,timing_keys_move,movingToPointer);

            GameBrain.walk(dungeon, timing_keys_move);
            return mov;
      }


}
