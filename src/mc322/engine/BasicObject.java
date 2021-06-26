package mc322.engine;

import mc322.game.GameOver;

public interface BasicObject{
      public void update(double dt) throws GameOver;
      public void renderer(Renderer r);
}
