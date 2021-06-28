package mc322.game;

import java.util.ArrayList;

import mc322.engine.BasicObject;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.sfx.AudioManager;
import mc322.game.itens.HealthPotion;
import mc322.game.itens.Item;
import mc322.game.itens.Key;
import mc322.game.itens.ResistancePotion;
import mc322.game.itens.StrengthPotion;

public class Bag implements BasicObject{

      private ArrayList<Pair<Item,Integer>> itens;
      private boolean openedWumpusDoor;
      int total_number_keys;

      public Bag()
      {
            itens = new ArrayList<Pair<Item,Integer>>();
            total_number_keys = 0;
            openedWumpusDoor = false;
      }
	
	public void addItem(Item item)
	{
		for(int i =0;i<itens.size();i++)
		{
			if(item.getClass().equals(itens.get(i).getFirst().getClass())){
                        if(!(item instanceof Key)) return;
                        else{
                              if(total_number_keys > 15) return;
                              else total_number_keys++;
                        }
			}
			
		}
		itens.add(Pair.of(item,1));
	}
	
	public Pair<Item,Integer> seePocket(int position)
	{
		return itens.get(position);
	}
	
	public Item getItemAtPocket(int position)
	{
		Pair<Item,Integer> removed = itens.get(position);
		itens.remove(position);
		if(removed.getSecond() >1) itens.add(Pair.of(removed.getFirst(),removed.getSecond()-1));
		return removed.getFirst();
		
	}
	
	public String toString()
	{
		String ans = "";
		for(int i =0;i<itens.size();i++)
		{
			ans+=itens.get(i).getFirst()+ " ("+itens.get(i).getSecond()+") ";
			if(i!=itens.size()-1)
				ans+=", ";
		}
		return ans;
	}

	public void update(double dt) {
		for(int i = 0;i<itens.size();i++)
		{
			itens.get(i).getFirst().update(dt);
		}
	}
	
	public void click(int i,int j,Dungeon dg){
		int i1 = 10*2;
		int j1 = 940;
		int width = 32*2;
		int hight = 52*2;

		if(LinearAlgebra.insideRec(i,j,i1,j1,i1+hight,j1+width)){
			drinkPosion("Resistance",dg);
		}
		j1-=width;

		if(LinearAlgebra.insideRec(i,j,i1,j1,i1+hight,j1+width)){
			drinkPosion("Strength",dg);
		}
		j1-=width;
		
            if(LinearAlgebra.insideRec(i,j,i1,j1,i1+hight,j1+width)){
			drinkPosion("Life",dg);
		}
		
	}

	private void drinkPosion(String type,Dungeon dg)
	{
		AudioManager audio = new AudioManager();
		if(type.equals("Resistance"))
		{
			for(int i =0;i<itens.size();i++)
			{
				if(seePocket(i).getFirst() instanceof ResistancePotion)
				{
					getItemAtPocket(i);
					dg.getCurrentRoom().getPlayer().incrementDef(5);
				  	audio.playMusic(GameMapTokens.getPathSound("potion"),false);
				}
			}
		}
		else if(type.equals("Strength"))
		{
			for(int i =0;i<itens.size();i++)
			{
				if(seePocket(i).getFirst() instanceof StrengthPotion)
				{
					getItemAtPocket(i);
					dg.getCurrentRoom().getPlayer().incrementStrength(5);
				  	audio.playMusic(GameMapTokens.getPathSound("potion"),false);
				}
			}
		}
		else if(type.equals("Life"))
		{
			for(int i =0;i<itens.size();i++)
			{
				if(seePocket(i).getFirst() instanceof HealthPotion)
				{
	                  getItemAtPocket(i);
	                  dg.getCurrentRoom().getPlayer().incrementHP(10);
	          	  	  audio.playMusic(GameMapTokens.getPathSound("potion"),false);
                }
            }
        }
            else
                  System.err.println("name of potion invalid!");
      }

      public void renderer(Renderer r) {

            for(int i = 0;i<itens.size();i++){
                  if(itens.get(i).getFirst() instanceof Key){
                        if(!openedWumpusDoor)
                              itens.get(i).getFirst().renderer(r);
                  }
                  else itens.get(i).getFirst().renderer(r);
            }
      }

	public boolean hasAllKeys()
	{
		int numberOfKeys =6;
		for(int i =0;i<itens.size();i++)
		{
			if(itens.get(i).getFirst() instanceof Key)
			{
					numberOfKeys--;
			}

		}
		if(numberOfKeys == 0){
			return true;
		}
		return false;
	}

      public void openWumpusDoor(){
            this.openedWumpusDoor = true;
      }
	
	
}
