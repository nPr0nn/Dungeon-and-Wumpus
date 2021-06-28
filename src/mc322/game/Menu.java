package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public class Menu implements BasicObject{

      GameManager game;
      String STATE;


      public Menu(GameManager game)
      {
            this.game = game;
            this.STATE = "game";
      }

      public void update(double dt) {

      }

      public void setState(String State)
      {
            this.STATE = State;
            switch(this.STATE)
            {
                  case "map":
                        this.game.pause();
                        break;
                  case "game":
                        this.game.unpause();
                        break;
                  case "victory":
                        this.game.pause();
                        break;
                  case "defeat":
                        this.game.pause();
                        break;
                  default:
                        break;
            }

      }

      public String getState()
      {
            return this.STATE;
      }

      public void renderer(Renderer r) {
            switch(this.STATE)
            {
                  case "map":
                        GameRenderer.drawMenu(0,0,"DungeonMap",r);
                        break;
                  case "victory":
                        GameRenderer.drawMenu(0,0,"Win",r);
                        break;
                  case "defeat":
                        GameRenderer.drawMenu(0,0,"Defeat",r);
                        break;
                  default:
                        break;
            }
      }

}
