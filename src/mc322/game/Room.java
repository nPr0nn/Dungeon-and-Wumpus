package mc322.game;

import java.util.Random;
import java.util.ArrayList;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesCharacters.Heros;
import mc322.game.entitiesTiles.Chest;
import mc322.game.entitiesTiles.Door;
import mc322.game.entitiesTiles.Ladder;
import mc322.game.entitiesTiles.Platform;
import mc322.game.entitiesTiles.SafeZone;

public class Room implements BasicObject {
      private final int size = 15;
      private Entity entities[][] = new Entity[size][size];

      private String numberRoom;
      private ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>(size);
      private Heros player;
      private Chest chest;
      private Heros milo;
      private Heros ze;
      private Heros raju;
      private Heros luna;
      private String color;
      private Dungeon dungeon;
      private int i;
      private int j;
      
      public Room(MapBuilder mapBuilder, Pair<Integer, Integer> pos,String color, Dungeon dungeon){
            Random rnd = new Random();
            this.numberRoom = "" + (rnd.nextInt(9)+1);
            //numberRoom = "7";
            this.color = color;
            tiles = mapBuilder.buildTiles(size, pos, numberRoom,this);
            entities = mapBuilder.buildEntities(size, pos, numberRoom,this);
            this.updateHerosAtRoom();
            
            this.dungeon = dungeon;
            this.i = pos.getFirst();
            this.j = pos.getSecond();
      }

      private void renderTerrain(Renderer r){
    	  
            String floor = "tile";
            int elevationFloor = -1;

            for(int i = size-1; i > 0; i--){
                  for(int j = 0; j < size-1; j++){
                        GameRenderer.drawTile(i, j, elevationFloor, floor, r, 0, 0,this.color);
                  }
            }

            GameRenderer.drawTile(size/2, size-1, elevationFloor, floor, r,0,0,this.color);
            GameRenderer.drawTile(0, size/2, elevationFloor, floor, r,0,0,this.color);
      }

      public void update(double dt) {
    	  for(int i = size-1; i >= 0; i--){
              for(int j=0;j<size;j++){
                    if(tiles.get(i).get(j) != null){
                          tiles.get(i).get(j).getFirst().update(dt);
                          if(tiles.get(i).get(j).getSecond() != null)
                                tiles.get(i).get(j).getSecond().update(dt);
                                                 	  
                    }
                    if(entities[i][j] != null)
	                  {
	                	  entities[i][j].update(dt);
	                  } 
              }
        }
      }

      public void renderer(Renderer r) {
    	 
            this.renderTerrain(r);
            for(int i = size-1; i >= 0; i--){
                  for(int j=0;j<size;j++){
                	  
                        if(tiles.get(i).get(j) != null){
                              tiles.get(i).get(j).getFirst().renderer(r);
                              if(tiles.get(i).get(j).getSecond() != null)
                                    tiles.get(i).get(j).getSecond().renderer(r);
                              
                        }
                        if(entities[i][j] != null)
                      	  entities[i][j].renderer(r);
                  }
            }
      }

	public void addCharacter(int i, int j,Character character) throws FullPlaceException {
		if(this.entities[i][j] != null)
		{
			throw new FullPlaceException();
		}
		else
		{
			this.entities[i][j] = character;
		}
		
	}
	
	public void setMilo(Heros milo)
	{
		this.milo = milo;
	}
	
	public Heros getMilo()
	{
		return milo;
	}
	
	public void setLuna(Heros luna)
	{
		this.luna = luna;
	}
	
	public Heros getLuna()
	{
		return luna;
	}
	
	public void setZe(Heros ze)
	{
		this.ze = ze;
	}
	
	public Heros getZe()
	{
		return ze;
	}
	
	public void setRaju(Heros raju)
	{
		this.raju = raju;
	}
	
	public Heros getRaju()
	{
		return raju;
	}
	
	public void setPlayer(Heros player)
	{
		this.player = player;
	}
	
	public Heros getPlayer()
	{
		return this.player;
	}
	
	public void setChest(Chest chest)
	{
		this.chest = chest;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public boolean isAccessible(int i, int j,double elevation, double legSize,int dir)
	{
		
		if(this.entities[i][j] == null)
		{
			if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
			{
				if(elevation < legSize)
					return true;
				return false;
			}
			if(tiles.get(i).get(j).getFirst() instanceof Ladder)
			{
				if(tiles.get(i).get(j).getFirst().getDirection()-dir %2==0)
				return true;
			}
			if(tiles.get(i).get(j).getFirst() instanceof Platform && tiles.get(i).get(j).getSecond() == null && elevation > (1-legSize))// if it is platform
				return true;
			if(tiles.get(i).get(j).getFirst() instanceof Door)
			{
				if(i==0)
				{
					if(dungeon.getRoom(this.i,this.j-1) !=null) //south
						return true;
					else
						return false;
				}
				else if(i==size-1)
				{
					if(dungeon.getRoom(this.i,this.j+1) !=null)//north
						return true;
					else
						return false;
				}
				else if(j==0)
				{
					if(dungeon.getRoom(this.i - 1,this.j) !=null)//west
						return true;
					else
						return false;
				}
				else if(j==size-1)
				{
					if(dungeon.getRoom(this.i + 1,this.j) !=null) //east
						return true;
					else
						return false;
				}	
				else
				{
					System.out.println("error, the door is not well placed");
				}
			}
				
		}
		return false;
	}
	
	public void move(int i0,int j0,int i,int j)
	{
		this.entities[i][j]=this.entities[i0][j0];
		if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
		{
			this.entities[i][j].setElevation(0);
		}
		else if(tiles.get(i).get(j).getFirst() instanceof Ladder)
			this.entities[i][j].setElevation(0.5);
		else if(tiles.get(i).get(j).getFirst() instanceof Door)
		{
			
			char dir='0'; //direcao para entrar na sala
			int newRoomI=this.i; //nova posicao da sala
			int newRoomJ=this.j; //nova posicao da sala
			if(i==0)
			{
				//south
				dir = 'n';
				newRoomI = this.i;
				newRoomJ = this.j-1; 
			}
			else if(i==size-1)
			{
				//north
				dir = 's';
				newRoomI = this.i;
				newRoomJ = this.j+1;
			}
			else if(j==0)
			{
				//west
				dir = 'e';
				newRoomI = this.i-1;
				newRoomJ = this.j;
			}
			else if(j==size-1)
			{
				//east
				dir = 'w';
				newRoomI = this.i+1;
				newRoomJ = this.j;
			}	
			else
			{
				System.out.println("error, the door is not well placed");
			}
			this.changeRoom(newRoomI,newRoomJ,dir);
		}
		else
			this.entities[i][j].setElevation(1);
		this.entities[i0][j0]=null;
	}
	
	public void updateHerosAtRoom()
	{
		if(this.milo!=null)
		{
			this.entities[this.milo.getPos().getFirst()][this.milo.getPos().getSecond()] = this.milo;
			milo.setElevation(0);
		}
		if(this.raju!=null)
		{
			this.entities[this.raju.getPos().getFirst()][this.raju.getPos().getSecond()] = this.raju;
			raju.setElevation(0);
		}
		if(this.ze!=null)
		{
			this.entities[this.ze.getPos().getFirst()][this.ze.getPos().getSecond()] = this.ze;
			ze.setElevation(0);
		}
		if(this.luna!=null)
		{
			this.entities[this.luna.getPos().getFirst()][this.luna.getPos().getSecond()] = this.luna;
			luna.setElevation(0);
		}
		
	}
	
	private void changeRoom(int iSala, int jSala, char dir)
	{
		// remover suas posicoes da sala antiga
		entities[luna.getPos().getFirst()][luna.getPos().getSecond()] = null;
		entities[raju.getPos().getFirst()][raju.getPos().getSecond()] = null;
		entities[milo.getPos().getFirst()][milo.getPos().getSecond()] = null;
		entities[ze.getPos().getFirst()][ze.getPos().getSecond()] = null;
		
		
		//setar posicoes corretas de cada um
		
		int li=0;
		int lj=0;
		int ri=0;
		int rj=0;
		int mi=0;
		int mj=0;
		int zi=0;
		int zj=0;
		switch(dir)
		{
		case 'w':
			li=size/2;
			lj=2;
			ri=size/2;
			rj=1;
			mi=size/2-1;
			mj=1;
			zi=size/2+1;
			zj=1;
			break;
		case 'e':
			li=size/2;
			lj=size-3;
			ri=size/2;
			rj=size-2;
			mi=size/2-1;
			mj=size-2;
			zi=size/2+1;
			zj=size-2;
			break;
		case 'n':
			lj=size/2;
			li=size-3;
			rj=size/2;
			ri=size-2;
			mj=size/2-1;
			mi=size-2;
			zj=size/2+1;
			zi=size-2;
			break;
		case 's':
			lj=size/2;
			li=2;
			rj=size/2;
			ri=1;
			mj=size/2-1;
			mi=1;
			zj=size/2+1;
			zi=1;
			break;
		default:
			throw new ChangeRoomInvalidChar();
		}
		luna.setPos(li,lj);
		raju.setPos(ri,rj);
		milo.setPos(mi,mj);
		ze.setPos(zi,zj);
		
		
		// adicionar todos os personagens na nova sala
		this.dungeon.getRoom(iSala,jSala).setLuna(this.luna);
		this.dungeon.getRoom(iSala,jSala).setZe(this.ze);
		this.dungeon.getRoom(iSala,jSala).setMilo(this.milo);
		this.dungeon.getRoom(iSala,jSala).setRaju(this.raju);
		
		
		//player
		this.dungeon.getRoom(iSala,jSala).setPlayer(this.player);
		
		
		
		// remover eles da sala antiga
		this.luna = null;
		this.raju = null;
		this.milo = null;
		this.ze = null;
		
		//remover player
		this.player = null;
		
		//atualizar nova sala
		this.dungeon.getRoom(iSala,jSala).updateHerosAtRoom();
		this.dungeon.setAtual(iSala,jSala);
	}
	
	public char[][] builCharMap(int iBegin, int jBegin, int iEnd, int jEnd)
	{
		char map[][] = new char[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(tiles.get(i).get(j)==null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
				{
					map[i][j] = '.';
				}
				else if(tiles.get(i).get(j).getFirst() instanceof Platform && tiles.get(i).get(j).getSecond() == null)
				{
					map[i][j] = 'U';
				}
				else if(tiles.get(i).get(j).getFirst() instanceof Ladder )
				{
					if(tiles.get(i).get(j).getFirst().getDirection()==1)
						map[i][j] = 'M';
					else
						map[i][j] = 'N';
				}
				else
				{
					map[i][j] = '#';
					continue;
				}
				if(entities[i][j]!=null)
				{
					if(i==iBegin && j == jBegin)
					{
						if(tiles.get(i).get(j) != null && (tiles.get(i).get(j).getFirst() instanceof Platform ||tiles.get(i).get(j).getFirst() instanceof Ladder))
							map[i][j] = 'B';
						else
							map[i][j] = 'b';
						continue;
					}
					if(i==iEnd && j == jEnd)
					{
						if(tiles.get(i).get(j) != null && (tiles.get(i).get(j).getFirst() instanceof Platform ||tiles.get(i).get(j).getFirst() instanceof Ladder))
							map[i][j] = 'E';
						else
							map[i][j] = 'e';
						continue;
					}
					map[i][j] = '#';
					
				}
			}
		}
		
		
		return map;
	}

}
