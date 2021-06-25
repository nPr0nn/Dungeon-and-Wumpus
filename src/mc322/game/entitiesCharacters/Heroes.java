package mc322.game.entitiesCharacters;

import java.util.ArrayList;

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

      public void use(Room room,Bag bag)
      {
            int iDir[] = {0,-1,0,1};
            int jDir[] = {1,0,-1,0};

            if(room.getChest()!=null){
                  if(room.getChest().getPos().getFirst() == this.i + iDir[this.updateDir]  && room.getChest().getPos().getSecond() == jDir[this.updateDir]+this.j)
                  {
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
            GameRenderer.drawCharacter(i,j,elevation, "Pointer",r, (int)updateFrame%nFrames, 0, "idle");
      }

      public void toggleSelect(){
            if(this.selected == 1) this.selected = 0;
            else if(this.selected == 0) this.selected = 1;
      }



	public void incrementDef(int def)
	{
		this.armor+=def;
	}
	public void incrementHP(int life)
	{
		this.hpMax+=life;
	}
	public void incrementStrength(int str)
	{
		this.damage+=str;
	}

	public void printStatus() {
		System.out.println("HP max: "+ hpMax+" hp: "+ hp+" damage: "+damage+" armor: "+armor);
		
	}
	
}
