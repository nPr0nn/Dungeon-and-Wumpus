package mc322.game;

import mc322.engine.GameContainer;
import mc322.game.exceptions.*;

public class Turn {

	private boolean playerTurn;
	private boolean combat;
	private Dungeon dg;
	private HeroAction heroAction;
	private EnemyAction enemyAction;
	
	
	public Turn(Dungeon dg, Bag bag){
		combat = false;
		this.dg = dg;
		heroAction =new HeroAction(dg, bag);
		heroAction.reset();
		enemyAction =new EnemyAction(dg);
		enemyAction.reset();
	}
	
	public void update(GameContainer gc,double dt,double timing_keys_move) {
		if(!combat)
			return;
		if(playerTurn)
		{
			if(heroAction.already())
			{
				heroAction.act(dt,timing_keys_move);
				if(heroAction.finished())
				{
					//System.out.println("cabou");
					heroAction.reset();
					playerTurn = !playerTurn;
				}
			}
			else
			{
				//System.out.println("jogar");
				heroAction.getInfo(gc,dg.getCurrentRoom());
			}
			
		}
		else
		{
			if(enemyAction.already())
			{
				enemyAction.act(dt,timing_keys_move);
				if(enemyAction.finished())
				{
					
					enemyAction.reset();
					disselectAll();
					playerTurn = !playerTurn;
				}
			}
			else
			{
				try {
					enemyAction.getInfo(gc,dg.getCurrentRoom());
				} catch (NoEnemyHere e) {
					this.stop();
				}
			}
			
		}
	}

	public void start() {
		dg.getCurrentRoom().getPlayer().disselect();
		heroAction.reset();
		enemyAction.reset();
		playerTurn = true;
		combat = true;
		
	}

	public void stop() {
		disselectAll();
		dg.getCurrentRoom().getPlayer().select();
		heroAction.reset();
		enemyAction.reset();
		combat = false;
		playerTurn = false;
		
	}
	
	private void disselectAll()
	{
		if(dg.getCurrentRoom().getLuna()!=null)
		dg.getCurrentRoom().getLuna().disselect();
		if(dg.getCurrentRoom().getZe()!=null)
		dg.getCurrentRoom().getZe().disselect();
		if(dg.getCurrentRoom().getRaju()!=null)
		dg.getCurrentRoom().getRaju().disselect();
		if(dg.getCurrentRoom().getMilo()!=null)
		dg.getCurrentRoom().getMilo().disselect();
	}

}
