package mc322.game.entitiesCharacters;

import java.util.ArrayList;

import mc322.engine.sfx.AudioManager;
import mc322.game.GameMapTokens;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.engine.LinearAlgebra;
import mc322.game.Room;
import mc322.game.Bag;
import mc322.game.itens.Item;

public abstract class Heroes extends Character{

      protected int selected = 0;

      public Heroes(int i, int j,double elevation) {
            super(i, j, elevation);
      }

      public void use(Room room,Bag bag){

            if(room.getChest()!=null){
                  if(Math.abs(room.getChest().getPos().getFirst() - this.i) + Math.abs(room.getChest().getPos().getSecond() - this.j) < 2 ) {
                        room.getChest().toggleAnimation();

                        ArrayList<Item> itens;
                        itens = room.getChest().getItens();
                        if(itens == null)
                              return;
                        for(int i = 0;i<itens.size();i++)
                        {
                              bag.addItem(itens.get(i));
                        }


                  }
            }
      }

      @Override
      public void renderer(Renderer r) {
            super.renderer(r);
            if(selected == 1)
                  GameRenderer.drawCharacter(i,j,elevation, "Pointer",r, (int)updateFrame%6, 0, "idle");
      }

      public void toggleSelect(){
            if(this.selected == 1) this.selected = 0;
            else if(this.selected == 0) this.selected = 1;
      }
      public void disselect()
      {
            this.selected = 0;
      }
      public void select(){
            AudioManager audio = new AudioManager();
            audio.playMusic(GameMapTokens.getPathSound("changeCharac"),false);
            this.selected = 1;
      }
      public boolean getSelected()
      {
            if(this.selected==1)
                  return true;
            return false;
      }


      public void incrementDef(int def)
      {
            this.armor+=def;
      }
      public void incrementHP(int life)
      {
            this.hpMax+=life;
            this.hp = hpMax;
      }
      public void incrementStrength(int str)
      {
            this.damage+=str;
      }

      public void printStatus() {
            System.out.println("HP max: "+ hpMax+" hp: "+ hp+" damage: "+damage+" armor: "+armor);

      }

}
