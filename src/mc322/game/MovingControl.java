package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Character;

public class MovingControl {

	private Dungeon dg;
	
	private Pair<Integer, Integer> lastDestiny;
	private boolean movingToPointer;
	
	public MovingControl(Dungeon dg)
	{
		this.dg=dg;
		lastDestiny = null;
	}
	
	public void update(double dt,Pair<Integer, Integer> destiny, double timing_keys_move,Character charac)
	{
		
		if(destiny != null)
			startWalk(destiny);
		
        if(lastDestiny != null)
        {
              movingToPointer = GameBrain.mouse_action(dg,timing_keys_move,movingToPointer,lastDestiny,charac);
              if(!movingToPointer)
              {
            	  lastDestiny = null;
              }
        }
	}
	
	public boolean walking()
	{
		if(lastDestiny==null)
			return false;
		return true;
	}
	
	private void startWalk(Pair<Integer, Integer> mouseClick)
	{
		movingToPointer = false;
		lastDestiny = mouseClick;
	}
}
