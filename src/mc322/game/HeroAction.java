package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Character;

public class HeroAction {

	private String action;
	private Pair<Integer, Integer> target;
	private Heroes player;
	private Dungeon dg;
	
	HeroAction(Dungeon dg)
	{
		this.dg = dg;
	}

	public boolean already() {
		if((!action.equals("")) && target != null && player != null)
			return true;
		return false;
	}

	public void act(double dt) {
		System.out.println("agindo");
		
	}

	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		action = "";
		target = null;
		player = null;
		
	}

	public void getInfo(GameContainer gc,Room room) {
		if(player == null)
		{
			player = selectPlayer(gc,room);
			return;
		}
		if(target == null)
		{
			target = selectTarget(gc);
			return;
		}
		if(action == "")
		{
			action = decideAction(room);
		}
		
	}

	private String decideAction(Room room) {
		Character entity = room.getEntityAt(this.target.getFirst(),this.target.getSecond());
		if(entity == null)
		{
			return "walk";
		}
		return "atack";
	}

	private Pair<Integer, Integer> selectTarget(GameContainer gc) {
		Pair<Integer,Integer> click = KeysManager.verifyMouseClick(gc,dg,null);
		if(click != null)
			click = Pair.of(click.getSecond(),click.getFirst());
		return click;
	}

	private Heroes selectPlayer(GameContainer gc,Room room) {
		Heroes hero = null;
		Pair<Integer,Integer> click = KeysManager.verifyMouseClick(gc,dg,null);
		if(click != null)
		{
			Entity entity = room.getEntityAt(click.getSecond(),click.getFirst());
			if(entity instanceof Heroes)
			{
				hero = (Heroes) entity;
			}
		}
		return hero;
	}

	
}
