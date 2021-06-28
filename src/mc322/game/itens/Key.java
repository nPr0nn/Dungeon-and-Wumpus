package mc322.game.itens;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class Key extends Item{

      String Color;
      public Key(String Color)
      {
            this.Color = Color;
            this.updateFrame = 0;
            this.updateDir = 0; 

            this.initAnimation = true;
            this.velocityAnim = 8;
            this.nFrames = 4;

            switch(Color)
            {
                  case "Black":
                        this.updateDir = 0;
                        break;
                  case "Purple":
                        this.updateDir = 1;
                        break;
                  case "Yellow":
                        this.updateDir = 2;
                        break;
                  case "Red":
                        this.updateDir = 3;
                        break;
                  case "Green":
                        this.updateDir = 4;
                        break;
                  case "Blue":
                        this.updateDir = 5;
                        break;
                  default:
                        System.err.println("The color of the key is wrong: " + Color);
                        break;
            }
      }

      @Override
      public void update(double dt) {
            this.updateFrame += this.velocityAnim*dt;
      }

      @Override
      public void renderer(Renderer r) {
            int renderer_frame = (int) this.updateFrame%nFrames;
            GameRenderer.drawBag(24+((this.updateDir)%3)*2,4+((this.updateDir/3)*3),0,"key",r, renderer_frame, this.updateDir);

      }

      @Override
      public void toggleAnimation() {
            // TODO Auto-generated method stub

      }

      public String toString()
      {
            return ""+this.Color+" key";
      }
      public String getColor()
      {
            return this.Color;
      }
}
