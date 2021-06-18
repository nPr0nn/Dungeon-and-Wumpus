package mc322.game;

import mc322.engine.Renderer;
import mc322.engine.gfx.ImageTile;

public class GameRenderer {

	public static void drawTile(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY, String color){
            ImageTile image = GameMapTokens.getImageTile(name, color);
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
	}

      public static void drawItem(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageItem(name, "Purple");
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
	}
      
    public static void drawCharacter(int i,int j,double elevation, String name, Renderer r, int updateX,int dir ,String state)
    {
    	ImageTile image = GameMapTokens.getImageCharacter(name, state);
    	elevation+=0.5;
    	r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, dir);
    }
}
