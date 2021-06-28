package mc322.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import mc322.engine.Pair;
import mc322.engine.CSVHandling;
import mc322.engine.LinearAlgebra;
import mc322.game.entitiesCharacters.Eye;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Wumpus;
import mc322.game.entitiesCharacters.Ze;
import mc322.game.entitiesTiles.*;
import mc322.game.entitiesCharacters.Character;

public class MapBuilder{

      private Room[][] rooms;
      private final int mapHeight = 20;
      private final int mapWidth  = 20;

      private Pair <Integer, Integer> origin;
      private Heroes player;
      
      private int numbersOfColors[] = {0,0,0,0,0,0,0}; //Black,Purple,Yellow,Red,Green,Blue,WHITE
      


      public Room[][] buildRooms(String dungeonPath, Dungeon dungeon) { 
            this.origin = GameBrain.getOrigin();
            this.rooms = new Room[mapHeight][mapWidth];


            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(dungeonPath);

            String scannedDungeon[][] = scannerCSV.requestCommands();

            for(int i = 0; i < mapHeight; i++){
                for(int j = 0; j < mapHeight; j++){
                	char token = scannedDungeon[i][j].charAt(0);
                	switch(token){
	                    case 'y':
	                        numbersOfColors[2]=numbersOfColors[2]+1;
	                          break;
	                    case 'k':
	                    	numbersOfColors[0]=numbersOfColors[0]+1;
	                          break;
	                    case 'b':
	                    	numbersOfColors[5]=numbersOfColors[5]+1;
	                          break;
	                    case 'g':
	                    	numbersOfColors[4]=numbersOfColors[4]+1;
	                          break;
	                    case 'r':
	                    	numbersOfColors[3]=numbersOfColors[3]+1;
	                          break;
	                    case 'p':
	                    	numbersOfColors[1]=numbersOfColors[1]+1;
	                          break;
	                    case 'f':
	                    	numbersOfColors[6]=numbersOfColors[6]+1;
	                          break;
	                    case '0':
	                    default:
	                          break;
                	}
                	
                }
            }
            
            int selectedRoom[] = {0,0,0,0,0,0,0};
            Random rand = new Random();
            for(int i = 0;i<selectedRoom.length;i++)
            {
            	
            	selectedRoom[i]+=(rand.nextInt(numbersOfColors[i]));
            }
            
            for(int i = 0; i < mapHeight; i++){
                  for(int j = 0; j < mapHeight; j++){

                        Pair<Integer, Integer> pi = Pair.of(i, j);
                        char token = scannedDungeon[i][j].charAt(0);

                        String rooms_around = "";
                        if(token != '0'){
                              rooms_around += scannedDungeon[i][j+1].charAt(0);
                              rooms_around += scannedDungeon[i][j-1].charAt(0);
                              rooms_around += scannedDungeon[i+1][j].charAt(0);
                              rooms_around += scannedDungeon[i-1][j].charAt(0); 
                        }

                        switch(token){
                              case 'y':
                            	  
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Yellow", dungeon,selectedRoom[2]==0);
                                    selectedRoom[2]--;
                                    break;
                              case 'k':
                            	  
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Black", dungeon,selectedRoom[0]==0);
                                    selectedRoom[0]--;
                                    break;
                              case 'b':
                            	  
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Blue", dungeon,selectedRoom[5]==0);
                                    selectedRoom[5]--;
                                    break;
                              case 'g':
                            	  
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Green", dungeon,selectedRoom[4]==0);
                                    selectedRoom[4]--;
                                    break;
                              case 'r':

                                    rooms[i][j] = new Room(this, pi, rooms_around,"Red", dungeon,selectedRoom[3]==0);
                                    selectedRoom[3]--;
                                    break;
                              case 'p':
                            	  
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Purple", dungeon,selectedRoom[1]==0);
                                    selectedRoom[1]--;
                                    break;
                              case 'f':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"White", dungeon,selectedRoom[1]==0);
                                    selectedRoom[6]--;
                                    break;
                              case 'o':
                                  rooms[i][j] = new Room(this, pi, rooms_around,"Origin", dungeon,false);
                                  break;
                                  
                              case 'w':
                                  rooms[i][j] = new Room(this, pi, rooms_around,"Wumpus", dungeon,false);
                                  rooms[i][j-1].setWumpusDoor();
                                  break;
                              case '0':
                              default:
                                    rooms[i][j] = null;
                                    break;
                        }
                  }
            }
            return rooms;
      }

      public ArrayList<ArrayList<Pair<Entity,Entity>>>buildTiles(int size,Pair<Integer,Integer>pos, 
            String rooms_around,
            String numberRoom,
            Room room){
            
            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(GameMapTokens.getRoomPATH(numberRoom));

            String scannedRoom[][] = scannerCSV.requestCommands();
            ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>();

            for(int i=0; i < size; i++) tiles.add(new ArrayList<Pair<Entity, Entity>>());

            for(int i = 0; i < size;i++){
                  for(int j = 0 ; j < size;j++){
                        char token = scannedRoom[j][0].charAt(i);
                        String dir = chooseOrientation(i, j, size);

                        boolean blocked = is_blocked_filter(token, dir, rooms_around);
                        Pair <Entity, Entity>pe;
                        pe = EntityTilesLoader.getEntity(token,blocked,i,j,dir,room.getColor(),room);
                        tiles.get(i).add(pe);

                        if(pe!=null && pe.getFirst() instanceof Chest){
                              room.setChest((Chest) pe.getFirst());
                        }
                        else if(pe!=null && pe.getSecond() instanceof Chest){
                              room.setChest((Chest) pe.getSecond());
                        }

                  }
            }
            return tiles;
      }

      private String chooseOrientation(int i, int j, int size){
            if(i == size-1) return "north";
            if(i == 0) return "south";
            if(j == 0) return "west";
            if(j == size-1) return "east";
            return "internal";
      }

      private boolean is_blocked_filter(char token, String dir, String rooms_around){
            if(token != 'd') return false;
            if(dir == "north" && rooms_around.charAt(0) == '0') return true;
            if(dir == "south" && rooms_around.charAt(1) == '0') return true;
            if(dir == "east" && rooms_around.charAt(2)  == '0') return true;
            if(dir == "west" && rooms_around.charAt(3)  == '0') return true;

            return false;
      }

      public Character[][] buildEntities(int size,Pair<Integer,Integer> pos,String numberRoom,Room room,boolean buildEnemys,boolean wumpus) {
            player = null;
            Character entities[][] = new Character[size][size];

            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(GameMapTokens.getRoomPATH(numberRoom));

            String scannedRoom[][] = scannerCSV.requestCommands();

            for(int i = 0; i < size;i++)
                  for(int j = 0 ; j < size;j++)
                        entities[i][j] = null;
            
            if(this.origin.getFirst() == pos.getFirst() && this.origin.getSecond() == pos.getSecond()){
                  int targetI = 2;
                  int targetJ = 7;
                  char token = scannedRoom[targetJ][0].charAt(targetI);

                  if(token == '.' || token == 's' ||token =='S'){
                        player = new Luna(targetI,targetJ,0);
                        entities[targetI][targetJ] = player;
                        room.setZe(new Ze(targetI-1,targetJ-1,0));
                        room.setLuna(player);
                        room.setRaju(new Raju(targetI-1,targetJ,0));
                        room.setMilo(new Milo(targetI-1,targetJ+1,0));

                  }
                  player.toggleSelect();
                  room.setPlayer(player);

            }
            if(buildEnemys && !wumpus)
            {
            	Random rand = new Random();
            	int numberOfEnemys = rand.nextInt(5);
            	int nGeneratedEnemys = 0;
                  while(nGeneratedEnemys<numberOfEnemys)
                  {
                        int targetI = rand.nextInt(size);
                        int targetJ = rand.nextInt(size);
                        char token = scannedRoom[targetJ][0].charAt(targetI);
                        if(token == '.' || token == 'a')
                        {
                              int elevation = 0;
                              if(token == 'a')
                                    elevation = 1;

                              if(entities[targetI][targetJ]==null)
                              {
                                    entities[targetI][targetJ] = new Eye(targetI, targetJ, elevation);
                                    nGeneratedEnemys++;
                              }
                        }
                  }
            }
            if(wumpus)
            {
                  entities[size/2][size/2] = new Wumpus(size/2,size/2,0);
            }
            
            return entities;
      }

      public Heroes getPlayer(){
            return player;
      }


}

