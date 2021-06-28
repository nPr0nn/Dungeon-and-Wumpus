package mc322.game;


import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesCharacters.Enemys;
import mc322.game.exceptions.*;

public class EnemyAction {

	private String action;
	private Pair<Integer, Integer> target;
	private Enemys enemy;
	private Dungeon dg;
	private MovingControl mc;
	private boolean firstStep;
	private boolean finished;
	private int counter;
	
	public EnemyAction(Dungeon dg)
	{
		this.dg = dg;
		mc = new MovingControl(dg,true);
	}

	public boolean already() {
		if((!action.equals("")) && target != null && enemy != null)
			return true;
		return false;
	}

	public void act(double dt,double timing_keys_move) {
		//System.out.println("acao sobre inimigo: "+enemy);
		switch(action)
		{
		case "walk":
			
			if(firstStep)
			{
				mc.update(dt,Pair.of(target.getSecond(),target.getFirst()),timing_keys_move,enemy,true);
				firstStep = false;
			}
			else
				mc.update(dt,null,timing_keys_move,enemy,true);
			if(!mc.walking())
			{
				
				finished = true;
			}
			counter++;
			if(counter > 100)
			{
				finished = true;
			}
			//System.out.println("andando");
			break;
		case "attack":
			//System.out.println("atacando "+target);
			enemy.attack(target.getFirst(),target.getSecond(),dg.getCurrentRoom());
			finished = true;
			break;
		}
		
	}

	public boolean finished() {
		return finished;
	}

	public void reset() {
		action = "";
		target = null;
		enemy = null;
		firstStep = false;
		finished = false;
		counter = 0;
		
	}

	public void getInfo(GameContainer gc,Room room) throws NoEnemyHere {
		if(enemy == null)
		{	
//			System.out.println("escolhendo inimigo");
			enemy = selectEnemy(room);
			return;
		}
		if(target == null)
		{
//			System.out.println("escolhendo alvo");
			target = selectTarget(room);
			return;
		}
		if(action == "")
		{
//			System.out.println("inimigo quer ir para "+target);
			action = decideAction(room);
		}
		
	}

	private String decideAction(Room room) {
		Character entity = room.getEntityAt(this.target.getFirst(),this.target.getSecond());
		if(entity == null)
		{
			firstStep=true;
			return "walk";
		}
		return "attack";
	}

	private Pair<Integer, Integer> selectTarget(Room room) {
		Pair<Integer, Integer> alvo =enemy.choseTarget(room);
		return Pair.of(alvo.getSecond(),alvo.getFirst());
	}

	private Enemys selectEnemy(Room room) throws NoEnemyHere {
		return dg.getCurrentRoom().chooseEnemy();
	}

}
