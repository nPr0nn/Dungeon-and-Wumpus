package mc322.game;

import java.util.Random;
import java.util.ArrayList;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.sfx.AudioManager;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesCharacters.Enemys;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import mc322.game.entitiesCharacters.Wumpus;
import mc322.game.entitiesTiles.Chest;
import mc322.game.entitiesTiles.Wall;
import mc322.game.entitiesTiles.Door;
import mc322.game.entitiesTiles.Ladder;
import mc322.game.entitiesTiles.Platform;
import mc322.game.entitiesTiles.SafeZone;
import mc322.game.itens.Key;
import mc322.game.exceptions.*;

public class Room implements BasicObject {
      private final int size = 15;
      private Character entities[][] = new Character[size][size];
      private String numberRoom;
      private String rooms_around;
      private ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>(size);
      private Heroes player;
      private Chest chest;
      private Heroes milo;
      private Heroes ze;
      private Heroes raju;
      private Heroes luna;
      private String color;
      private Dungeon dungeon;
      private Wumpus bosswumpus;
      private boolean blocked;
      private int i;
      private int j;
      private boolean wumpus;
      private AudioManager audio = new AudioManager();


      public Room(MapBuilder mapBuilder,Pair<Integer,Integer>pos,String rooms_around,
                  String color,
                  Dungeon dungeon, boolean hasKey){

            Random rnd = new Random();

            this.numberRoom = "" + (rnd.nextInt(9)+1);

            //hasKey = true;
            if(hasKey && (this.numberRoom.equals("1") || this.numberRoom.equals("5") || this.numberRoom.equals("9")))
            {
                  this.numberRoom = "7";
            }
            this.color = color;
            this.rooms_around = rooms_around;
            boolean hasEnemys = true;
            wumpus = false;

            //            numberRoom = "5";
            //hasEnemys = false;

            boolean wumpus = false;
            if(color.equals("Origin"))
            {
                  numberRoom="0";
                  hasEnemys = false;
                  this.color = "Purple";
            }

            if(color.equals("Wumpus"))
            {
            	  wumpus = true;
                  numberRoom="wumpus";
                  hasEnemys = false;
                  this.color = "Black";
                  wumpus = true;
            }

            this.blocked = true;

            tiles = mapBuilder.buildTiles(size, pos, rooms_around,numberRoom,this);
            entities = mapBuilder.buildEntities(size, pos, numberRoom,this,hasEnemys,wumpus);

            if(wumpus){
                  bosswumpus = (Wumpus) entities[size/2][size/2];
            }
            this.updateHerosAtRoom();

            if(hasKey){
                  if(this.chest == null) System.out.println(this.numberRoom);
                  else if(this.color != "White"){
                        this.chest.insertItem(new Key(this.color));
                        //this.chest.insertItem(new Key("Purple"));
                        //this.chest.insertItem(new Key("Yellow"));
                        //this.chest.insertItem(new Key("Green"));
                        //this.chest.insertItem(new Key("Red"));
                        //this.chest.insertItem(new Key("Blue"));
                        //this.chest.insertItem(new Key("Black"));
                  }
            }
            this.dungeon = dungeon;
            this.i = pos.getFirst();
            this.j = pos.getSecond();
      }

      public void setWumpusDoor() {
            if(tiles.get(size-1).get(size/2).getFirst() instanceof Door){
                  ((Door)tiles.get(size-1).get(size/2).getFirst()).setWumpusDoor();
            }
            ((Wall)tiles.get(size-1).get(size/2-1).getFirst()).toggleVisible();
      }

      public boolean hasAllKeys(){
            return dungeon.getBag().hasAllKeys();
      }
      public void openWumpusDoor(){
            dungeon.getBag().openWumpusDoor();
      }



      private void renderTerrain(Renderer r){

            String floor = "tile";
            int elevationFloor = -1;

            for(int i = size-1; i > 0; i--){
                  for(int j = 0; j < size-1; j++){
                        GameRenderer.drawTile(i, j, elevationFloor, floor, r, 0, 0,this.color);
                  }
            }

            if(rooms_around.charAt(1) != '0' )
                  GameRenderer.drawTile(0, size/2, elevationFloor, floor, r,0,0,this.color);

            if(rooms_around.charAt(2) != '0')
                  GameRenderer.drawTile(size/2, size-1, elevationFloor, floor, r,0,0,this.color);

      }

      public void update(double dt) throws GameOver, Victory{
            boolean isEnemys = false;
            if(player == null){
                  updatePlayer();
            }
            if(bosswumpus != null) updateWumpus();
            updatePlayerSelected();
            for(int i = size-1; i >= 0; i--){
                  for(int j=0;j<size;j++){
                        if(tiles.get(i).get(j) != null){
                              tiles.get(i).get(j).getFirst().update(dt);
                              if(tiles.get(i).get(j).getSecond() != null)
                                    tiles.get(i).get(j).getSecond().update(dt);

                        }
                        if(entities[i][j] != null)
                        {
                              if(entities[i][j].getClass().getSuperclass()==Enemys.class)
                              {
                                    isEnemys = true;
                              }
                              entities[i][j].update(dt);
                              if(entities[i][j].getDead())
                              {

                                    if(entities[i][j] instanceof Raju)
                                    {
                                          this.raju = null;
                                          updatePlayer();
                                    }

                                    if(entities[i][j] instanceof Ze)
                                    {
                                          this.ze = null;
                                          updatePlayer();
                                    }

                                    if(entities[i][j] instanceof Milo)
                                    {
                                          this.milo = null;
                                          updatePlayer();
                                    }

                                    if(entities[i][j] instanceof Luna)
                                    {
                                          this.luna = null;
                                          updatePlayer();
                                    }

                                    entities[i][j]=null;
                              }

                        }
                  }
            }
            if(!isEnemys){
                  open();
                  dungeon.setState("Exploration");
            }
      }

      private void updateWumpus() throws Victory{
            if(bosswumpus.isWumpusDead()){
                  throw new Victory();
            }
      }

      private void updatePlayer() throws GameOver {
            if(luna != null && !luna.getDead())
            {
                  luna.select();
                  setPlayer(luna);
            }
            else if(milo != null && !milo.getDead())
            {
                  milo.select();
                  setPlayer(milo);
            }
            else if(raju != null && !raju.getDead())
            {
                  raju.select();
                  setPlayer(raju);
            }
            else if(ze != null && !ze.getDead())
            {
                  ze.select();
                  setPlayer(ze);
            }
            else throw new GameOver();
      }

      private void updatePlayerSelected(){
            if(luna != null && !luna.getDead()){
                  if(luna.getSelected()) setPlayer(luna);
            }
            else if(milo != null && !milo.getDead()){
                  if(milo.getSelected()) setPlayer(milo);
            }
            else if(raju != null && !raju.getDead()){
                  if(raju.getSelected()) setPlayer(raju);
            }
            else if(ze != null && !ze.getDead()){
                  if(ze.getSelected()) setPlayer(ze);
            }
            else throw new GameOver();
      }

      public void renderer(Renderer r) {

            renderTerrain(r);
            for(int i = size-1; i >= 0; i--){
                  for(int j=0;j<size;j++){

                        if(tiles.get(i).get(j) != null){
                              tiles.get(i).get(j).getFirst().renderer(r);
                              if(tiles.get(i).get(j).getSecond() != null)
                                    tiles.get(i).get(j).getSecond().renderer(r);

                        }
                        if(entities[i][j] != null) entities[i][j].renderer(r);
                  }
            }
      }

      public void addCharacter(int i, int j,Character character) throws FullPlaceException {
            if(this.entities[i][j] != null){
                  throw new FullPlaceException();
            }
            else this.entities[i][j] = character;
      }


      public void setMilo(Heroes milo){
            this.milo = milo;
      }
      public Heroes getMilo(){
            return milo;
      }
      public void setLuna(Heroes luna){
            this.luna = luna;
      }
      public Heroes getLuna(){
            return luna;
      }
      public void setZe(Heroes ze){
            this.ze = ze;
      }
      public Heroes getZe(){
            return ze;
      }
      public void setRaju(Heroes raju){
            this.raju = raju;
      }
      public Heroes getRaju(){
            return raju;
      }
      public void setPlayer(Heroes player){
            if(milo != null) milo.disselect();
            if(luna != null) luna.disselect();
            if(raju != null) raju.disselect();
            if(ze != null) ze.disselect();
            this.player = player;
            player.toggleSelect();
      }
      public Heroes getPlayer(){
            return this.player;
      }
      public void setChest(Chest chest){
            this.chest = chest;
      }
      public Chest getChest(){
            return this.chest;
      }

      public String getColor(){
            return this.color;
      }

      public void open()
      {
            this.blocked = false;
      }

      public boolean getBlocked()
      {
            return this.blocked;
      }


      public boolean isAccessible(int i, int j,double elevation, double legSize,int dir,Character charac){

            boolean cond2 = false;
            if(this.entities[i][j] instanceof Heroes)
            {
                  if(charac instanceof Heroes)
                  {
                        if(((Heroes)charac).getSelected())
                              cond2 = true;
                  }
            }

            if(this.entities[i][j] == null || cond2){

                  if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone){
                        if(elevation < legSize) return true;
                        return false;
                  }

                  if(tiles.get(i).get(j).getFirst() instanceof Ladder){
                        if(tiles.get(i).get(j).getFirst().getDirection()-dir %2==0) return true;
                  }

                  if(tiles.get(i).get(j).getFirst() instanceof Platform)
                        if(tiles.get(i).get(j).getSecond() == null && elevation > (1-legSize))
                              return true;

                  if(tiles.get(i).get(j).getFirst() instanceof Door && !this.blocked && !((Door)tiles.get(i).get(j).getFirst()).getClosed()){
                        if(i==0){
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
                        else System.out.println("error, the door is not well placed");
                  }
            }
            return false;
      }

      public void move(int i0,int j0,int i,int j,Character charac){

            Character removedEntity = null;
            //    	  System.out.println("movendo");
            if(entities[i][j] instanceof Heroes ) {
                  //            	System.out.println("estou tentnando me mover pra onde tem um cara");
                  if(charac instanceof Heroes && ((Heroes)charac).getSelected())
                  {
                        removedEntity  = this.entities[i][j];
                  }
                  else
                  {
                        System.err.println("Something went wrong, a hero tried to move to another");
                        return;
                  }
            }

            this.entities[i][j]=this.entities[i0][j0];

            if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
                  this.entities[i][j].setElevation(0);
            else if(tiles.get(i).get(j).getFirst() instanceof Ladder)
                  this.entities[i][j].setElevation(0.5);
            else if(tiles.get(i).get(j).getFirst() instanceof Door){ //if wants to change room
                  char dir='0'; //direcao para entrar na sala
                  int newRoomI=this.i; //nova posicao da sala
                  int newRoomJ=this.j; //nova posicao da sala

                  if(i==0){
                        //south
                        dir = 'n';
                        newRoomI = this.i;
                        newRoomJ = this.j-1; 
                  }
                  else if(i==size-1){
                        //north
                        dir = 's';
                        newRoomI = this.i;
                        newRoomJ = this.j+1;
                  }
                  else if(j==0){
                        //west
                        dir = 'e';
                        newRoomI = this.i-1;
                        newRoomJ = this.j;
                  }
                  else if(j==size-1){
                        //east
                        dir = 'w';
                        newRoomI = this.i+1;
                        newRoomJ = this.j;
                  }	
                  else System.out.println("error, the door is not well placed");
                  this.changeRoom(newRoomI,newRoomJ,dir);
            }
            else this.entities[i][j].setElevation(1);
            this.entities[i0][j0]=removedEntity;
            if(removedEntity != null)
            {
                  removedEntity.setPos(i0,j0);
                  if(tiles.get(i0).get(j0) == null || tiles.get(i0).get(j0).getFirst() instanceof SafeZone)
                        this.entities[i0][j0].setElevation(0);
                  else if(tiles.get(i0).get(j0).getFirst() instanceof Ladder)
                        this.entities[i0][j0].setElevation(0.5);
                  else this.entities[i0][j0].setElevation(1);

            }
      }

      public void updateHerosAtRoom(){
            if(this.milo!=null){
                  this.entities[this.milo.getPos().getFirst()][this.milo.getPos().getSecond()] = this.milo;
                  milo.setElevation(0);
            }
            if(this.raju!=null){
                  this.entities[this.raju.getPos().getFirst()][this.raju.getPos().getSecond()] = this.raju;
                  raju.setElevation(0);
            }
            if(this.ze!=null){
                  this.entities[this.ze.getPos().getFirst()][this.ze.getPos().getSecond()] = this.ze;
                  ze.setElevation(0);
            }
            if(this.luna!=null){
                  this.entities[this.luna.getPos().getFirst()][this.luna.getPos().getSecond()] = this.luna;
                  luna.setElevation(0);
            }
      }

      private void changeRoom(int iSala, int jSala, char dir){
            dungeon.setState("Combat");

    	  	audio.playMusic(GameMapTokens.getPathSound("newRoom"),false);
            // remover suas posicoes da sala antiga
            if(luna != null)
                  entities[luna.getPos().getFirst()][luna.getPos().getSecond()] = null;
            if(raju != null)
                  entities[raju.getPos().getFirst()][raju.getPos().getSecond()] = null;
            if(milo != null)
                  entities[milo.getPos().getFirst()][milo.getPos().getSecond()] = null;
            if(ze != null)
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
            if(luna != null)
                  luna.setPos(li,lj);
            if(raju != null)
                  raju.setPos(ri,rj);
            if(milo != null)
                  milo.setPos(mi,mj);
            if(ze != null)
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

      public char[][] builCharMap(boolean enemy){
            char map[][] = new char[size][size];
            for(int i = 0; i < size; i++){
                  for(int j = 0; j < size; j++){
                        Pair<Entity,Entity> tile = tiles.get(j).get(i);

                        if(tile == null || tile.getFirst() instanceof SafeZone) map[i][j] = '.';
                        else if(tile.getFirst() instanceof Platform && tile.getSecond()==null) map[i][j] = 'U';

                        else if(tile.getFirst() instanceof Ladder ){
                              if(tile.getFirst().getDirection() == 1) map[i][j] = 'M';
                              else map[i][j] = 'N';
                        }
                        else if(tile.getFirst() instanceof Door) map[i][j] = 'D';
                        else{
                              map[i][j] = '#';
                              continue;
                        }
                        if(entities[j][i]!=null)
                        {
                              //System.out.println(entities[j][i].getClass().getSuperclass()+"  "  +Enemys.class);
                              if(entities[j][i].getClass().getSuperclass() == Enemys.class && !enemy)
                              {
                                    //System.out.println("igaul j: "+j+" i "+i);
                                    map[i][j] = '#'; 
                                    continue;
                              }

                        }

                  }

            }

            return map;
      }

      public void attack(int i, int j, int damage, String attack) {
            if(this.entities[i][j] == null)
                  return;
            entities[i][j].hurt(damage, attack);
      }

      public Character getEntityAt(int i, int j) {
            return entities[i][j];
      }

      public Enemys chooseEnemy() throws NoEnemyHere {
            int numOfEnemies = 0;
            for(int i = 0;i<entities.length;i++)
            {
                  for(int j = 0;j<entities.length;j++)
                  {
                        if(entities[i][j] instanceof Enemys)
                        {
                              numOfEnemies++;
                        }
                  }
            }
            if(numOfEnemies<1)
                  throw new NoEnemyHere();
            Random rand = new Random();
            int enemynumber = rand.nextInt(numOfEnemies);

            for(int i = 0;i<entities.length;i++)
            {
                  for(int j = 0;j<entities.length;j++)
                  {
                        if(entities[i][j] instanceof Enemys)
                        {
                              if(enemynumber == 0)
                              {
                                    return (Enemys) entities[i][j];
                              }
                              enemynumber--;
                        }
                  }
            }
            throw new NoEnemyHere();
      }

      public char[][] buildMapEnemyTarget() {
            char map[][] = new char[size][size];

            for(int i = 0; i < size; i++){
                  for(int j = 0; j < size; j++){
                        Pair<Entity,Entity> tile = tiles.get(j).get(i);

                        if(tile == null || tile.getFirst() instanceof SafeZone) map[i][j] = '.';
                        else if(tile.getFirst() instanceof Platform && tile.getSecond()==null) map[i][j] = 'U';

                        else if(tile.getFirst() instanceof Ladder ){
                              if(tile.getFirst().getDirection() == 1) map[i][j] = 'M';
                              else map[i][j] = 'N';
                        }
                        else if(tile.getFirst() instanceof Door) map[i][j] = 'D';
                        else{
                              map[i][j] = '#';
                              continue;
                        }
                        if(entities[j][i]!=null)
                        {
                              if(entities[j][i] instanceof Heroes)
                              {
                                    map[i][j] = 'O'; 
                                    continue;
                              }

                        }

                  }

            }

            return map;
      }
      
      
      public String toString()
      {
    	  if(wumpus)
    		  return "Wumpus";
    	  return color;
      }

}

