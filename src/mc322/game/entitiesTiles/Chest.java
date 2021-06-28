package mc322.game.entitiesTiles;

import java.util.ArrayList;
import java.util.Random;

import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.sfx.AudioManager;
import mc322.game.Entity;
import mc322.game.GameMapTokens;
import mc322.game.GameRenderer;
import mc322.game.itens.HealthPotion;
import mc322.game.itens.Item;
import mc322.game.itens.ResistancePotion;
import mc322.game.itens.StrengthPotion;

public class Chest extends Entity{

      private boolean opened;
      private ArrayList<Item> itens;
      public Chest (int i, int j, String direction, int elevation){
            this.name = "chest";
            this.i=i;
            this.j=j;
            this.elevation = elevation;
            this.opened = false;
            this.initAnimation = false;
            this.velocityAnim = 8;
            this.nFrames = 6;
            this.itens = new ArrayList<Item>();
            if(direction == "west-east") this.updateDir = 1;
            this.updateFrame = 0;
            Random rand = new Random();
            int num = rand.nextInt(60);
            if(num%2==0)//2
            {
                  insertItem(new HealthPotion());
            }
            if(num%5==0)//5
            {
                  insertItem(new StrengthPotion());
            }
            if(num%6==0)//6
            {
                  insertItem(new ResistancePotion());
            }

      }

      public void update(double dt){
            if(this.initAnimation){
                  this.updateFrame += this.velocityAnim*dt;
                  if( (int)this.updateFrame > 0 && (int)this.updateFrame % nFrames == 0){
                        this.initAnimation = false;
                        this.opened = true;
                  }
            }

      }

      public void renderer(Renderer r) {
            if(opened)
                  GameRenderer.drawItem(i,j,elevation,name,r, 6, updateDir); 
            else
                  GameRenderer.drawItem(i,j,elevation,name,r, (int)updateFrame%nFrames, updateDir);
      }

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

      public Pair<Integer,Integer> getPos()
      {
            return Pair.of(this.i,this.j);
      }

      public void insertItem(Item item)
      {
            itens.add(item);
      }

      public ArrayList<Item> getItens() {
    	  	AudioManager audio = new AudioManager();
    	  	audio.playMusic(GameMapTokens.getPathSound("Discover"),false);
            ArrayList<Item> removedItens = itens;
            itens = null;
            return removedItens;
      }

}

