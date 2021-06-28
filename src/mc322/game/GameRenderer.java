package mc322.game;

import java.awt.Color;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.gfx.ImageTile;

public class GameRenderer {

      public static void drawTile(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY, String color){
            ImageTile image = GameMapTokens.getImageTile(name, color);
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawAttack(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageAttack(name);
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawItem(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageItem(name);
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawCharacter(int i,int j,double elevation, String name, Renderer r, int updateX,int dir ,String state){
            ImageTile image = GameMapTokens.getImageCharacter(name, state);
            elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, dir);
      }

      public static void drawEnemy(int i,int j,double elevation, String name, Renderer r, int updateX,int dir ,String state){
            ImageTile image = GameMapTokens.getImageEnemies(name, state);
            elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, dir);
      }


      public static void drawMenu(int i,int j, String name, Renderer r){
            ImageTile image = GameMapTokens.getImageMenu(name);
            r.drawImage(image,i, j);
      }

      public static void drawBag(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageItem(name);
            r.drawImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawLife(int i,int j,double hpMax,double hp,String name, Renderer r){
            ImageTile image = GameMapTokens.getImageCharacter(name, "life_icon");
            //r.drawImage(image, i, j);
            int updateDir = 0;
            double porcentHpMax = hp/hpMax;

            if(porcentHpMax == 0) updateDir = 2;
            else if (porcentHpMax < 0.5) updateDir = 1;
            r.drawImageTile(image,i-10, j-20, 0, updateDir);

            int green = LinearAlgebra.clamp((int)(255*porcentHpMax*2), 0, 255);
            int red = LinearAlgebra.clamp((int)(255 - (255*porcentHpMax)*(0.7)), 0, 255);

            int horixontalDistance = 40;
            int verticalDistance = 10;
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+7,horixontalDistance+i+((int)hpMax),50,50,50);
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+7,horixontalDistance+i+((int)hp),red,green,100);

      }

      public static void drawLifeEnemy(int i,int j,double elevation,double hpMax,double hp,Renderer r)
      {
            elevation +=0.5;

            Pair <Integer, Integer> b = Pair.of(i*16, j*16);
            b = LinearAlgebra.toIsometrica(b);
            i = b.getFirst() + 72-((int)hpMax/2);
            j = b.getSecond() + 162;

            //ImageTile image = GameMapTokens.getImageItem("charctersFace");
            //r.drawImage(i, j, image, 0, charact);

            double porcentHpMax = hp/hpMax;
            int red = LinearAlgebra.clamp((int)(255 -(255*porcentHpMax)),0,255);
            int green = LinearAlgebra.clamp((int)(255*porcentHpMax*2),0,255);
            //    	  green = 0;
            //    	  red = 255;

            int horixontalDistance = 8;
            int verticalDistance = 0;
            //System.out.println("i: "+i+" j: "+j);
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+4,horixontalDistance+i+((int)hpMax/2),50,50,50);
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+4,horixontalDistance+i+((int)hp/2),red,green,100);

      }

      public static void drawLifeWumpus(int i,int j,double elevation,double hpMax,double hp,Renderer r)
      {
            elevation +=0.5;

            Pair <Integer, Integer> b = Pair.of(i*16, j*16);
            b = LinearAlgebra.toIsometrica(b);
            i = b.getFirst() + 72-((int)hpMax/5*2);
            j = b.getSecond() + 162;

            //ImageTile image = GameMapTokens.getImageItem("charctersFace");
            //r.drawImage(i, j, image, 0, charact);

            double porcentHpMax = hp/hpMax;
            int red = LinearAlgebra.clamp((int)(255 -(255*porcentHpMax)),0,255);
            int green = LinearAlgebra.clamp((int)(255*porcentHpMax*2),0,255);
            //    	  green = 0;
            //    	  red = 255;

            int horixontalDistance = 8;
            int verticalDistance = 0;
            //System.out.println("i: "+i+" j: "+j);
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+4,horixontalDistance+i+((int)hpMax/2),50,50,50);
            r.fillRect(verticalDistance+j,horixontalDistance+i,verticalDistance+j+4,horixontalDistance+i+((int)hp/2),red,green,100);

      }


      public static void change_animation_state(String state, Dungeon dungeon){
            Room cRoom = dungeon.getCurrentRoom();

            if(cRoom.getMilo()!=null)
                  cRoom.getMilo().change_state(state);
            if(cRoom.getLuna()!=null)
                  cRoom.getLuna().change_state(state);
            if(cRoom.getRaju()!=null)
                  cRoom.getRaju().change_state(state);
            if(cRoom.getZe()!=null)
                  cRoom.getZe().change_state(state);

            return;
      }


      public static void drawBackground(Renderer r, Dungeon dungeon, double timing_background_light){
            int red, green, blue;
            String hex;
            String color = dungeon.getCurrentRoom().getColor();
            int pH = r.getHeight();
            int pW = r.getWidth();

            double seno = Math.sin(timing_background_light);
            seno *= 70;
            int lim = (int) seno + 500;


            for(int i = lim; i > 0; i--){
                  red = green = blue = i/4;

                  if(color == "Purple"){ blue *=2; red *=2; }
                  else if(color == "Green"){ green *= 2; }
                  else if(color == "Yellow"){ red *= 2; green *= 2; }
                  else if(color == "Red"){red *= 2; }
                  else if(color == "Blue") {blue *= 2; }

                  int a = 1;
                  int alpha = (int)(a * 255.99);

                  hex = String.format("%02x%02x%02x%02x", alpha, red, green, blue);
                  r.drawCirc(pW/2, pH/2, lim/2-i, (int) Long.parseLong(hex, 16) );
            }

            ArrayList<Pair<Integer, Integer>> poly = new ArrayList<>();

            int listX[] = {10, 60, 110, 160, 210, 260, 310, 360, 410, 459, 510};
            int listY[] = {0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 0};

            Random rand = new Random();


            hex = String.format("%02x%02x%02x%02x", 1, 255, 255, 255);
            int inc = (int)seno/7 ;
            double x,y;

            double ca = 0.5;
            double cb = 4;
            double ch = 2;
            double ck = 1;

            for(int k = 0; k < pW; k++){
                  x = (double) k;
                  //ch *= seno;
                  double ds = Math.sin((x-ch)/cb);
                  ds *= seno/2;
                  int s = (int) ds;
                  //System.out.println(s);
                  y = ca*(s) + ck*rand.nextDouble()*2;
                  poly.add( Pair.of( (int)x, (int)y ));
            }

            //poly.add(Pair.of(0, 0));
            //poly.add(Pair.of(100,100-inc));

            //for(int k = 0; k < listX.length; k++){
            //if(listY[k] != 0){
            //poly.add(Pair.of(listX[k], listY[k] ));
            //}
            //else{
            //poly.add(Pair.of(listX[k], listY[k]));

            //}
            //}


            //r.drawPolygon(poly, (int) Long.parseLong(hex, 16));

      }

}
