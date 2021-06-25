package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Enemys;
import mc322.game.entitiesCharacters.Heroes;

public class Turn {

	private boolean playerTurn;
	private boolean combat;
	private Dungeon dg;
	private HeroAction heroAction;
	private EnemyAction enemyAction;
	
	
	public Turn(Dungeon dg)
	{
		combat = false;
		this.dg = dg;
		heroAction =new HeroAction(dg);
		heroAction.reset();
		enemyAction =new EnemyAction();
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
					System.out.println("cabou");
					heroAction.reset();
					playerTurn = !playerTurn;
				}
			}
			else
			{
				heroAction.getInfo(gc,dg.getCurrentRoom());
			}
			
		}
		else
		{
			if(enemyAction.already())
			{
				enemyAction.act(dt);
				if(enemyAction.finished())
				{
					enemyAction.reset();
					playerTurn = !playerTurn;
				}
			}
			else
			{
				enemyAction.getInfo();
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
		dg.getCurrentRoom().getLuna().disselect();
		dg.getCurrentRoom().getZe().disselect();
		dg.getCurrentRoom().getRaju().disselect();
		dg.getCurrentRoom().getMilo().disselect();
		dg.getCurrentRoom().getPlayer().select();
		heroAction.reset();
		enemyAction.reset();
		combat = false;
		playerTurn = false;
		
	}

}
