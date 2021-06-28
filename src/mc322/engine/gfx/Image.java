package mc322.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class Image{
      
      private int width, height;
      private int p[];

      public Image(String path){
            BufferedImage image = null;

            try{
                  image = ImageIO.read(new File(path));
            }
            catch(IOException e){ 
                  e.printStackTrace(); 
            }

            width = image.getWidth();
            height = image.getHeight();
            p = image.getRGB(0, 0, width, height, null, 0, width);

            image.flush();
      }

      // Getters and Setters
      
      public void setWidth(int width){
            this.width = width;
      }
      public void setHeight(int height){
            this.height = height;
      }
      public void setP(int[] p){
            this.p = p;
      }

      public int getWidth(){
            return this.width;
      }
      public int getHeight(){
            return this.height;
      }
      public int[] getP(){
            return this.p;
      }

}
