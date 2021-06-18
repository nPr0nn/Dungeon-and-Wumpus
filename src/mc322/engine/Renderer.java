package mc322.engine;

import java.awt.image.DataBufferInt;
import java.lang.Math;

import mc322.engine.LinearAlgebra;
import mc322.game.GameMapTokens;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;

public class Renderer{
      
      private int pW, pH;
      private int[] p;

      public Renderer(GameContainer gc){
            this.pW = gc.getWidth();
            this.pH = gc.getHeight();
            this.p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
            
      }

      public void clear(){
            for(int i = 0; i < p.length; i++){
                  p[i] = 0xff000000;
            }
      }

      public void setPixel(int x, int y, int value){
            if( (x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) return;
            p[x + y*pW] = value;
      }

//      public void drawImage(Image image, int offX, int offY){
//
//            if(offX < -image.getWidth()  || offX >= pW) return;
//            if(offY < -image.getHeight() || offY >= pH) return; 
//
//            int newX = 0,
//                newY = 0,
//                newWidth  = image.getWidth(),
//                newHeight = image.getHeight();
//
//            if(offX < 0) newX -= offY;
//            if(offY < 0) newY -= offY;
//            if(newWidth + offX > pW)  newWidth  -= newWidth  + offX - pW;
//            if(newHeight + offY > pH) newHeight -= newHeight + offY - pH;
//
//            for(int y = newY; y < newHeight; y++){
//                  for(int x = newX; x < newWidth; x++){
//                        setPixel(x + offX, y + offY, image.getP()[x + y*image.getWidth()] );     
//                  }
//            }           
//      }

      public void drawImage(ImageTile image, int offX, int offY, int tileX, int tileY){

            if(offX < -image.getTileWidth()  || offX >= pW) return;
            if(offY < -image.getTileHeight() || offY >= pH) return; 

            int newX = 0,
                newY = 0,
                newWidth  = image.getTileWidth(),
                newHeight = image.getTileHeight();

            if(offX < 0) newX -= offX;
            if(offY < 0) newY -= offY;
            if(newHeight + offY > pH) newHeight -= newHeight + offY - pH;

            int fixX = tileX*newWidth;
            int fixY = tileY*newHeight;

            for(int y = newY; y < newHeight; y++){
                  for(int x = newX; x < newWidth; x++){
                        setPixel(x + offX, y + offY, image.getP()[(x+fixX)+ (y+fixY)*image.getWidth()]);     
                  }
            }  
      }

      public void drawIsometricImage(int i, int j, ImageTile image, int tileX, int tileY){
            int tx = pW/2 - 7*image.getTileWidth()/2 - 24;
            int ty = pH/2 - image.getTileHeight()/2 + 24;

            int sizeX = image.getTileWidth()/4;
            int sizeY = image.getTileHeight()/4;

            Pair <Integer, Integer> b = Pair.of(i*sizeX, j*sizeY);
            b = LinearAlgebra.toIsometrica(b);
            drawImage(image, b.getFirst() + tx, b.getSecond() + ty, tileX, tileY);
      }

}
