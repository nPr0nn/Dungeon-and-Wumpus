package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;
import mc322.engine.sfx.AudioManager;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.KeyEvent;

import mc322.engine.AbstractGame;
import mc322.engine.Pair;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import mc322.engine.Input;

import mc322.game.entitiesTiles.*;
import mc322.game.exceptions.*;

public class GameManager implements AbstractGame{

      public Dungeon dungeon;
      private LifeBar lifebar;
      private Menu menu;
      private AudioManager audio;
      private Bag bag;
      private MovingControl mv;
      private Turn turn;

      private String STATE = ""; 

      private double timing_keys_move;
      private double timing_background_light;

      private Pair<Integer, Integer> mouseClickPoint;
      private boolean pause;

      public GameManager(){
            dungeon = new Dungeon(this);
            audio = new AudioManager();
            menu = new Menu(this);
            lifebar = new LifeBar();
            this.pause =false;
            this.timing_keys_move = 0;
            this.timing_background_light = 0;
            this.mv = new MovingControl(dungeon,false);
            this.mouseClickPoint = null;
            bag = new Bag();
            turn = new Turn(dungeon, bag);
      }

      public void reset(){
            //System.out.println("Reinicia");
            dungeon = new Dungeon(this);
            bag = new Bag();
            menu = new Menu(this);
            audio = new AudioManager();
      }

      public void togglePause()
      {
            this.pause = !this.pause;
      }

      public void pause() {
            this.pause = true;
      }

      public void unpause() {
            this.pause = false;
      }

      public void setState(String state){

            if(this.STATE.equals(state))
                  return;

            this.STATE = state;
            if(this.STATE.equals("Combat"))
            {
                  turn.start();
            }
            if(this.STATE.equals("Exploration"))
            {

                  turn.stop();
            }
            audio.stopMusic();
            if(state.equals("Combat"))
            	audio.playMusic(GameMapTokens.getPathMusic(state),true);
            else
            {
            	audio.playMusic(GameMapTokens.getPathMusic(dungeon.getCurrentRoom().toString()),true);
            }
      }

      public String getState()
      {
            return this.STATE;
      }

      public void update(GameContainer gc, double dt){
            if(timing_background_light > 3) timing_background_light = 0;
            if(!this.pause){
                  if(timing_keys_move > 0.12) {
                        timing_keys_move = 0;

                  }
                  turn.update(gc,dt,timing_keys_move);
                  mouseClickPoint = KeysManager.verifyMouseClick(gc,dungeon,bag,false);
                  KeysManager.keys_action(gc,dungeon, bag);

                  if(!this.STATE.equals("Combat")){
                        boolean keyPressed = KeysManager.keys_movement(gc,dungeon, timing_keys_move);
                        if(keyPressed) mouseClickPoint = null;
                        mv.update(dt,mouseClickPoint,timing_keys_move,dungeon.getCurrentRoom().getPlayer(),false,keyPressed);
                  }
                  try {
                        dungeon.update(dt);
                        lifebar.setLifes(dungeon.getCurrentRoom());
                  } catch (GameOver e) {
                        menu.setState("defeat") ;
                  } catch (Victory v)
                  {
                        menu.setState("victory") ;
                  }
                  timing_keys_move += dt;
            }
            menu.update(dt);
            bag.update(dt);
      
            timing_background_light += dt;
            KeysManager.keys_game_flow(gc,this, menu);
      }

      public void renderer(GameContainer gc, Renderer r){
            GameRenderer.drawBackground(r, dungeon, timing_background_light);
            dungeon.renderer(r);
            bag.renderer(r);
            lifebar.renderer(r);
            menu.renderer(r);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }

	public Bag getBag() {
		return bag;
	}

}
