package mc322.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageTile extends Image{
      private int tileWidth, tileHeight;

      public ImageTile(String path, int tileWidth, int tileHeight){
            super(path);
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
      }

      // Getters and Setters
      
      public int getTileWidth(){
            return this.tileWidth;
      }
      public int getTileHeight(){
            return this.tileHeight;
      }

      public void setTileWidth(int tileWidth) {
            this.tileWidth = tileWidth;
      }
      public void setTileHeight(int tileHeight) {
            this.tileHeight = tileHeight;
      }

}
