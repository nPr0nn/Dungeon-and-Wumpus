package mc322.engine;

import java.awt.image.DataBufferInt;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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

      // Bresenham’s Line Algorithm

      // Bresenham’s Line Algorithm
      public void drawLine(Pair<Integer, Integer> a, Pair<Integer, Integer> b, int color){
            int d = 0;
            int x1 = a.getFirst();
            int y1 = a.getSecond();

            int x2 = b.getFirst();
            int y2 = b.getSecond();

            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int dx2 = dx*dx;
            int dy2 = dy*dy;

            int ix = x1 < x2 ? 1 : -1;
            int iy = y1 < y2 ? 1 : -1;

            int x = x1;
            int y = y1;

            if (dx >= dy) {
                  while (true) {
                        setPixel(x, y, color);
                        if (x == x2) break;
                        x += ix;
                        d += dy2;
                        if (d > dx) {
                              y += iy;
                              d -= dx2;
                        }
                  }
            } else {
                  while (true) {
                        setPixel(x, y, color);
                        if (y == y2) break;
                        y += iy;
                        d += dx2;
                        if (d > dy) {
                              x += ix;
                              d -= dy2;
                        }
                  }
            }

            //int m = 2*(yf - yi);
            //int slope_error = m - (xf - xi);

            //for(int x = xi, y = yi; x <= xf; x++){
            //setPixel(x, y, color);
            //slope_error += m;
            //if(slope_error >= 0){
            //y++;
            //slope_error -=  2*(xf - xi);
            //}
            //}
      }

      public void drawPolygon(ArrayList<Pair<Integer, Integer>> poly, int color){
            for(int i = 0; i < poly.size()-1; i++){
                  drawLine(poly.get(i), poly.get(i+1), color);
            }
      }

      public void drawPoints(ArrayList<Pair<Integer, Integer>> points, int color){
            for(int i = 0; i < points.size()-1; i++){
                  setPixel(points.get(i).getFirst(), points.get(i).getSecond(), color);
            }
      }

      public void drawRect(int xi, int yi, int lenX, int lenY, int color){
            for(int i = yi; i < yi+lenY; i++){
                  for(int j = xi; j < xi+lenX; j++){
                        int x = j;
                        int y = i;
                        setPixel(x, y, color);
                  }
            }
      }

      // Bresenham’s Circle Algorithm
      public void drawCirc(int xi, int yi, int r, int color){
            int y = r;
            int x = 0;
            int fp = 3 - 2*r;

            int di[] = {1, -1, 1, -1};
            int dj[] = {1, 1, -1, -1};

            while(y >= x){
                  for(int k = 0; k < 4; k++){
                        setPixel(x*di[k] + xi, y*dj[k] + yi, color);
                        setPixel(y*di[k] + xi, x*dj[k] + yi, color);
                  }

                  x++;
                  if(fp <= 0) fp += 4*x + 6;
                  else {
                        y--;
                        fp += 4*(x - y) + 10;
                  }

            }

      }

      public void fillRect(int xi,int yi, int xo, int yo, int red,int green,int blue){
            String hex = String.format("%02x%02x%02x", red, green, blue);
            
            for(int i =yi;i<yo;i++){
                  for(int j = xi;j<xo;j++){
                        setPixel(i, j, (int)Long.parseLong(hex, 16));
                  }
            }
      }


      public void drawImage(Image image, int offX, int offY){

            if(offX < -image.getWidth()  || offX >= pW) return;
            if(offY < -image.getHeight() || offY >= pH) return; 

            int newX = 0,
                newY = 0,
                newWidth  = image.getWidth(),
                newHeight = image.getHeight();

            //if(offX < 0) newX -= offY;
            if(offY < 0) newY -= offY;
            if(newWidth + offX > pW)  newWidth  -= newWidth  + offX - pW;
            if(newHeight + offY > pH) newHeight -= newHeight + offY - pH;

            for(int y = newY; y < newHeight; y++){
                  for(int x = newX; x < newWidth; x++){
                        setPixel(x + offX, y + offY, image.getP()[x + y*image.getWidth()] );     
                  }
            }           
      }

      public void drawImage(int i, int j, ImageTile image, int tileX, int tileY){
            int tx = pW/2 - 7*image.getTileWidth()/2 - 24;
            int ty = pH/2 - image.getTileHeight()/2 + 24;

            int sizeX = image.getTileWidth()/4;
            int sizeY = image.getTileHeight()/4;

            Pair <Integer, Integer> b = Pair.of(i*sizeX, j*sizeY);
            drawImageTile(image, b.getFirst() + tx, b.getSecond() + ty, tileX, tileY);
      }


      public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){

            if(offX < -image.getTileWidth()  || offX >= pW) return;
            if(offY < -image.getTileHeight() || offY >= pH) return; 

            int newX = 0,
                newY = 0,
                newWidth  = image.getTileWidth(),
                newHeight = image.getTileHeight();

            if(offX < 0) newX -= offX;
            if(offY < 0) newY -= offY;
            if(newWidth + offX > pW)  newWidth  -= newWidth  + offX - pW;
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

            //System.out.println(tx + " " + ty);

            int sizeX = image.getTileWidth()/4;
            int sizeY = image.getTileHeight()/4;

            //System.out.println("sizeX = "+sizeX+" sizeY: "+sizeY);

            Pair <Integer, Integer> b = Pair.of(i*sizeX, j*sizeY);
            b = LinearAlgebra.toIsometrica(b);
            drawImageTile(image, b.getFirst() + tx, b.getSecond() + ty, tileX, tileY);
      }

      public int getWidth(){
            return pW;
      }
      public int getHeight(){
            return pH;
      }

}
