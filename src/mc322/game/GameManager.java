package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;
import mc322.engine.sfx.AudioManager;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.KeyEvent;

import mc322.engine.AbstractGame;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import mc322.engine.Input;

import mc322.game.entitiesTiles.*;

public class GameManager implements AbstractGame{
      private Dungeon dungeon;

      private AudioManager audio;
      private String STATE = "exploration"; 

      public GameManager(){
            dungeon = new Dungeon();
            audio = new AudioManager();
            //audio.playMusic(GameMapTokens.getPathSound("Enviroment"),true);
      }

      @Override
      public void update(GameContainer gc, double dt){
            //if(gc.getInput().isKey(KeyEvent.VK_A)) System.out.println("A");
//            if(gc.getInput().isKeyDown(65))
//            	System.out.println("Apertou a");
    	  	KeysManager.keys(gc,dungeon);
            dungeon.update(dt);
      }

      public void renderer(GameContainer gc, Renderer r){
//            int xCurrent = gc.getInput().getMouseX()  - image.getTileWidth()/2;
//            int yCurrent =  gc.getInput().getMouseY() - image.getTileHeight()/2;

            dungeon.renderer(r);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }

}
