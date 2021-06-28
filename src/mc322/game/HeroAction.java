package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesCharacters.Ze;
import mc322.game.entitiesCharacters.Enemys;

public class HeroAction {

	private String action;
	private Pair<Integer, Integer> target;
	private Heroes player;
	private Dungeon dg;
	private MovingControl mc;
	private boolean firstStep;
	private boolean finished;
      private Bag bag;

      private boolean isZe;
      private boolean isAttackingEnemy;

	public HeroAction(Dungeon dg, Bag bag)
	{
		this.dg = dg;
            this.bag = bag;
		mc = new MovingControl(dg,false);

            this.isZe = false;
            this.isAttackingEnemy = false;
	}

	public boolean already() {
		if((!action.equals("")) && target != null && player != null)
			return true;
		return false;
	}

	public void act(double dt,double timing_keys_move) {
		switch(action)
		{
		case "walk":
			
			if(firstStep)
			{
				mc.update(dt,Pair.of(target.getSecond(),target.getFirst()),timing_keys_move,player,true);
				firstStep = false;
			}
			else
				mc.update(dt,null,timing_keys_move,player,true);
			if(!mc.walking())
				finished = true;
			//System.out.println("andando");
			break;
		case "attack":
			//System.out.println("atacando");
                  player.attack(target.getFirst(),target.getSecond(),dg.getCurrentRoom());
			finished = true;
			break;

            case "redo":
                  player = null;
                  target = null;
                  action = "";
                  break;
		}

		
	}

	public boolean finished() {
		return finished;
	}

	public void reset() {
		action = "";
		target = null;
		player = null;
		firstStep = false;
		finished = false;
		isZe = false;
	}

	public void getInfo(GameContainer gc,Room room) {
		if(player == null){
			player = selectPlayer(gc,room);
			return;
            }
            if(target == null)
            {
                  target = selectTarget(gc);
                  room.setPlayer(player);
                  if(player instanceof Ze) isZe = true;
                  
			return;
		}
		if(action == "")
		{
			action = decideAction(room);
		}
		
	}

	private String decideAction(Room room) {
		Character entity = room.getEntityAt(this.target.getFirst(),this.target.getSecond());

            if(entity instanceof Enemys) isAttackingEnemy = true;
            else isAttackingEnemy = false;

		if(entity == null){
			firstStep=true;
			return "walk";
		}

            if(!isAttackingEnemy && !isZe) return "redo";

		return "attack";
	}

	private Pair<Integer, Integer> selectTarget(GameContainer gc) {
		Pair<Integer,Integer> click = KeysManager.verifyMouseClick(gc,dg,bag,true);
		if(click != null)
		{
			click = Pair.of(click.getSecond(),click.getFirst());
			if(click.getFirst()==player.getPos().getFirst() && click.getSecond()==player.getPos().getSecond())
				click = null;
		}
		return click;
	}

	private Heroes selectPlayer(GameContainer gc,Room room) {
		Heroes hero = null;
		Pair<Integer,Integer> click = KeysManager.verifyMouseClick(gc,dg,bag,false);
		if(click != null)
		{
			Entity entity = room.getEntityAt(click.getSecond(),click.getFirst());
			if(entity instanceof Heroes)
			{
				hero = (Heroes) entity;
				hero.select();
			}
		}
		return hero;
	}

	
}
