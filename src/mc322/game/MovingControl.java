package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Character;

public class MovingControl {

	private GameContainer gc;
	private GameManager game;
	private Dungeon dg;
	
	private Pair<Integer, Integer> lastDestiny;
	private boolean movingToPointer;
	
	public MovingControl(GameManager game,Dungeon dg)
	{
		this.game=game;
		this.dg=dg;
		lastDestiny = null;
	}
	
	public void update(GameContainer gc,double dt,Pair<Integer, Integer> destiny, double timing_keys_move,Character charac)
	{
		if(destiny != null)
			startWalk(destiny);
		
        if(lastDestiny != null)
        {
              movingToPointer = GameBrain.mouse_action(gc,dg,timing_keys_move,movingToPointer,lastDestiny,charac);
              if(!movingToPointer)
              {
            	  lastDestiny = null;
              }
        }
	}
	
	public void startWalk(Pair<Integer, Integer> mouseClick)
	{
		movingToPointer = false;
		lastDestiny = mouseClick;
	}
}
