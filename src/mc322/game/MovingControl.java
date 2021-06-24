package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;

public class MovingControl {

	private GameContainer gc;
	private GameManager game;
	private Dungeon dg;
	
	private Pair<Integer, Integer> lastMouseClick;
	private boolean movingToPointer;
	
	public MovingControl(GameManager game,Dungeon dg)
	{
		this.game=game;
		this.dg=dg;
		lastMouseClick = null;
	}
	
	public void update(GameContainer gc,double dt,Pair<Integer, Integer> mouseClick, double timing_keys_move)
	{
		if(mouseClick != null)
			startWalk(mouseClick);
		
        if(lastMouseClick != null)
        {
              movingToPointer = GameBrain.mouse_action(gc,dg,timing_keys_move,movingToPointer,lastMouseClick);
              if(!movingToPointer)
              {
            	  lastMouseClick = null;
              }
        }
	}
	
	public void startWalk(Pair<Integer, Integer> mouseClick)
	{
		movingToPointer = false;
		lastMouseClick = mouseClick;
	}
}
