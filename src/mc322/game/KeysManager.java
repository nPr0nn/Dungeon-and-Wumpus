package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heros;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import java.util.Random;

public abstract class KeysManager {

	public static void keys(GameContainer gc, Dungeon dungeon)
	{
		
		if(gc.getInput().isKeyDown('A'))
		{
			dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		
		if(gc.getInput().isKeyDown('D'))
		{
			dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown('S'))
		{
			dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown('W'))
		{
			dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown(38))
		{
			dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown(37))
		{
			dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown(40))
		{
			dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown(39))
		{
			dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom());
			walk(dungeon);
		}
		if(gc.getInput().isKeyDown('E'))
		{
			Heros player = dungeon.getCurrentRoom().getPlayer();
			
			if(player instanceof Luna || player == null)
			{
				player = dungeon.getCurrentRoom().getMilo();
			}
			else if(player instanceof Milo)
			{
				player = dungeon.getCurrentRoom().getRaju();
			}
			else if(player instanceof Raju)
			{
				player = dungeon.getCurrentRoom().getZe();
			}
			else if(player instanceof Ze)
			{
				player = dungeon.getCurrentRoom().getLuna();
			}
			else
				System.out.println("Player error while changing character");
			dungeon.getCurrentRoom().setPlayer(player);
			
		}
		if(gc.getInput().isKeyDown('Q'))
		{
			Heros player = dungeon.getCurrentRoom().getPlayer();
			
			if(player instanceof Luna || player == null)
			{
				player = dungeon.getCurrentRoom().getZe();
			}
			else if(player instanceof Milo)
			{
				player = dungeon.getCurrentRoom().getLuna();
			}
			else if(player instanceof Raju)
			{
				player = dungeon.getCurrentRoom().getMilo();
			}
			else if(player instanceof Ze)
			{
				player = dungeon.getCurrentRoom().getRaju();
			}
			else
				System.out.println("Player error while changing character");
			dungeon.getCurrentRoom().setPlayer(player);
		}
		
		if(gc.getInput().isKeyDown('T'))
		{
			dungeon.toggleFollow();
		}
		
		
		
		if(gc.getInput().wasClicked())
		{
			Pair<Integer,Integer> posClick = gc.getInput().getClick();
			System.out.println("Clicked at " + posClick.getFirst() + ", "+posClick.getSecond());
			posClick = LinearAlgebra.toIsometrica(posClick);
			posClick = LinearAlgebra.toCartesianas(posClick);
			System.out.println("Clicked at " + posClick.getFirst() + ", "+posClick.getSecond());
			
		}
		
	}
	
	//TODO: mover para uma classe melhor (a funcao é estatica)
	private static void walk(Dungeon dungeon) 
	{
		if(!dungeon.getFollow())
			return;
		Random rand = new Random();
		
		if(dungeon.getCurrentRoom().getMilo() != dungeon.getCurrentRoom().getPlayer())
		{
			if(rand.nextInt(9)<7)
				dungeon.getCurrentRoom().getMilo().follow(dungeon.getCurrentRoom().getLuna(),dungeon.getCurrentRoom());
			else
				dungeon.getCurrentRoom().getMilo().follow(dungeon.getCurrentRoom().getRaju(),dungeon.getCurrentRoom());
		}
		if(dungeon.getCurrentRoom().getLuna() != dungeon.getCurrentRoom().getPlayer())
		{
			if(rand.nextInt(13)<9)
				dungeon.getCurrentRoom().getLuna().follow(dungeon.getCurrentRoom().getPlayer(),dungeon.getCurrentRoom());
			else
				dungeon.getCurrentRoom().getLuna().follow(dungeon.getCurrentRoom().getZe(),dungeon.getCurrentRoom());
		}
		if(dungeon.getCurrentRoom().getZe() != dungeon.getCurrentRoom().getPlayer())
		{
			if(rand.nextInt(15)<8)
				dungeon.getCurrentRoom().getZe().follow(dungeon.getCurrentRoom().getRaju(),dungeon.getCurrentRoom());
			else
				dungeon.getCurrentRoom().getZe().follow(dungeon.getCurrentRoom().getLuna(),dungeon.getCurrentRoom());
		}
		if(dungeon.getCurrentRoom().getRaju() != dungeon.getCurrentRoom().getPlayer())
		{
			if(rand.nextInt(8)<5)
				dungeon.getCurrentRoom().getRaju().follow(dungeon.getCurrentRoom().getMilo(),dungeon.getCurrentRoom());
			else
				dungeon.getCurrentRoom().getRaju().follow(dungeon.getCurrentRoom().getLuna(),dungeon.getCurrentRoom());
		}
			
		
	}
	
	
}
