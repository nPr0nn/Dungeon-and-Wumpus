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

public class GameManager implements AbstractGame{
	  public Dungeon dungeon;
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
	        turn = new Turn(dungeon);
	        this.pause =false;
	        this.timing_keys_move = 0;
	        this.timing_background_light = 0;
	        this.mv = new MovingControl(this,dungeon);
	        this.mouseClickPoint = null;
	        bag = new Bag();
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
  	
  	public void setState(String state)
  	{
  		
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
  		audio.playMusic(GameMapTokens.getPathSound(state),true);
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
                  turn.update(gc,dt);
                  if(!this.STATE.equals("Combat"))
                  {
	                  KeysManager.keys_action(gc,dungeon, bag);
	                  mouseClickPoint = KeysManager.verifyMouseClick(gc,dungeon,bag);
	                  if(KeysManager.keys_movement(gc,dungeon, timing_keys_move)) mouseClickPoint = null;
	                  mv.update(gc,dt,mouseClickPoint,timing_keys_move,dungeon.getCurrentRoom().getPlayer());
                  }
                  dungeon.update(dt);
                  timing_keys_move += dt;
            }
            menu.update(dt);
            timing_background_light += dt;
            KeysManager.keys_game_flow(gc,this, menu);
      }

      public void renderer(GameContainer gc, Renderer r){
            GameRenderer.drawBackground(r, dungeon, timing_background_light);
            dungeon.renderer(r);
            bag.renderer(r);
            menu.renderer(r);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }

}
