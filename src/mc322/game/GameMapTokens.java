package mc322.game;

import mc322.engine.gfx.ImageTile;

import java.util.HashMap;
import java.util.Map;

public abstract class GameMapTokens{
      private static ImageTile image;
      private static int tileWidth = 64;
      private static int tileHeight = 64;

      private static String PNG = ".png";
      private static String CSV = ".csv";
      private static String WAV = ".wav";

      private static String ASSETS = "_assets";
      private static String SOUNDS = "_sounds";
      private static String DATA   = "_data";

      private static Map<String, ImageTile> mapTokens = new HashMap<>();

      private static String DIR_BOSSES     = ASSETS + "/bosses/";
      private static String DIR_CHARACTERS = ASSETS + "/characters/";
      private static String DIR_ENEMIES    = ASSETS + "/enemies/";
      private static String DIR_ITENS      = ASSETS + "/itens/";
      private static String DIR_MENU       = ASSETS + "/menu/";
      private static String DIR_NPCS       = ASSETS + "/NPCs/";
      
      private static String DIR_TILES      = ASSETS + "/tiles/";

      private static String DIR_CSV        = DATA + "/layouts/";
      private static String DUNGEON        = DATA + "/dungeon.txt";

      public static String getDungeonPATH(){
            return DUNGEON;
      }
      public static String getRoomPATH(String numberRoom){
            return DIR_CSV+numberRoom+CSV;
      }

      // TokensTiles: blank, door, pillar, tile_half, tile_ladder, tile_side_wall, tile_wall, tile, torch
      // TokensColors: Red, Blue, Purple, Yellow, Green, White, Black
      public static ImageTile getImageTile(String object, String color){
            if(mapTokens.get(object+color) == null){
                  String path = DIR_TILES + color + "/" + object + PNG;
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTokens.put(object+color, image);
            }
            return mapTokens.get(object+color);
      }

     // TokensCharacter: Milo, Luna, Raju, Ze
      // TokensStates: idle, moving
      public static ImageTile getImageItem(String object){
            if(mapTokens.get(object) == null){
                  String path = DIR_ITENS + object + PNG;
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTokens.put(object, image);
            }
            return mapTokens.get(object);
      }

      public static ImageTile getImageMenu(String object){
          if(mapTokens.get(object) == null){
                String path = DIR_MENU + object + PNG;
                image = new ImageTile(path, tileWidth, tileHeight);
                mapTokens.put(object, image);
          }
          return mapTokens.get(object);
      }

      // TokensCharacter: Milo, Luna, Raju, Ze
      // TokensStates: ilde, moving
      public static ImageTile getImageCharacter(String object, String state){
            if(mapTokens.get(object+state) == null){
                  String path = DIR_CHARACTERS + object + "/" + state + PNG;
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTokens.put(object+state, image);
            }
            return mapTokens.get(object+state);
      }
      
      public static ImageTile getImageEnemies(String object, String state){
          if(mapTokens.get(object+state) == null){
                String path = DIR_ENEMIES + object + "/" + state + PNG;
                //System.out.println(path);
                image = new ImageTile(path, tileWidth, tileHeight);
                mapTokens.put(object+state, image);
          }
          return mapTokens.get(object+state);
    }
      
      
      public static String getPathSound(String object){
        return SOUNDS + "/" + object + WAV;
    }

}
