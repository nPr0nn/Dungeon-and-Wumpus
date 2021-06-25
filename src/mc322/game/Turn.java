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
	
//	public void update(double dt) {
//		if(!combat)
//			return;
//		boolean notDecided = true;
//		if(playerTurn)
//		{
//			
//			while(notDecided)
//			{
//				Heroes selected = selectPlayer();
//				if(selected == null)
//					continue;
//				
//				Pair<Integer, Integer> target = selectTarget();
//				String action = selectAction(target);
//				if(action == "")
//					continue;
//				switch(action)
//				{
//					case "atack":
//						notDecided = false;
//						break;
//					case "walk":
//						
//						notDecided = false;
//						break;
//					default:
//						System.out.println("this action is invalid");
//						break;	
//				}
//				
//				//acao dos herois
//			}
//		}
//		else
//		{
//			Enemys enemy = selectEnemy();
//			Pair<Integer, Integer> targetOfEnemy = selectTargetForEnemy();
//			enemy.atack(targetOfEnemy,dg.getCurrentRoom());
//		}
//		playerTurn = !playerTurn;
//	}
	
	
	public void update(GameContainer gc,double dt) {
		if(!combat)
			return;
		if(playerTurn)
		{
			if(heroAction.already())
			{
				heroAction.act(dt);
				if(heroAction.finished())
				{
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
	
	
	



//	private Pair<Integer, Integer> selectTargetForEnemy() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private Enemys selectEnemy() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private Pair<Integer, Integer> selectTarget() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private String selectAction(Pair<Integer, Integer> target) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private Heroes selectPlayer() {
//		
//		return null;
//	}

	public void start() {
		heroAction.reset();
		enemyAction.reset();
		playerTurn = true;
		combat = true;
		
	}

	public void stop() {
		heroAction.reset();
		enemyAction.reset();
		combat = false;
		playerTurn = false;
		
	}

}
