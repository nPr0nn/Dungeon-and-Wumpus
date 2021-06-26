package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Character;

public class MovingControl {

      private Dungeon dg;

      private Pair<Integer, Integer> lastDestiny;
      private boolean movingToPointer;
      private boolean enemy;

      public MovingControl(Dungeon dg,boolean enemy)
      {
            this.enemy=enemy;
            this.dg=dg;
            lastDestiny = null;
      }

      public void update(double dt,Pair<Integer, Integer> destiny, double timing_keys_move,
                  Character charac,boolean combat, boolean keyPressed)
      {
            //System.out.println(timing_keys_move);
            if(destiny != null)
                  startWalk(destiny);

            if(lastDestiny != null)
            {
                  movingToPointer = GameBrain.mouse_action(dg,timing_keys_move,movingToPointer,lastDestiny,charac,combat,enemy);
                  movingToPointer &= !keyPressed;
                  if(!movingToPointer){
                        //System.out.println("parar de andar");
                        lastDestiny = null;
                  }
            }
      }
      public void update(double dt,Pair<Integer, Integer> destiny, double timing_keys_move,
                  Character charac,boolean combat){
            update(dt, destiny, timing_keys_move, charac, combat, false);
      }


      public boolean walking()
      {
            if(lastDestiny==null)
            {
                  //System.out.println("nao ando");
                  return false;
            }
            //System.out.println("ando");
            return true;
      }

      private void startWalk(Pair<Integer, Integer> mouseClick)
      {
            movingToPointer = false;
            lastDestiny = mouseClick;
      }
}
