package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      
      protected String name;
      
      protected boolean initAnimation;
      protected String state;

      protected int velocityAnim;
      protected int velocityAnimIdle;
      protected int velocityAnimMoving;
      
      protected int nFrames;
      protected int nFramesAttacking;
      protected int nFramesIdle;
      protected int nFramesMoving;

      protected double elevation;
      protected int i;
      protected int j;
      protected int updateDir;
      protected double updateFrame;

      public abstract void update(double dt);
      public abstract void renderer(Renderer r);
      public abstract void toggleAnimation();
      public void setElevation(double newElevation)
      {
    	  this.elevation = newElevation;
      }
      public int getDirection()
      {
    	  return this.updateDir;
      }
      
      public void setPos(int i,int j)
      {
    	  this.i=i;
    	  this.j=j;
      }
      
      public Pair<Integer,Integer> getPos()
      {
    	  return Pair.of(this.i,this.j);
      }
      
}
