package mc322.game;

import mc322.engine.Pair;
import mc322.game.entitiesTiles.*;

public abstract class EntityTilesLoader {

      public static Pair<Entity, Entity> getEntity(char token, boolean blocked, int i, int j, 
                  String dir,String color,Room room){

            Pair<Entity, Entity> entityTile = null;

            boolean internal;
            int elevation = 0;

            switch(token){
                  // External Wall;
                  case '#':
                        internal = false;
                        if( (dir == "north" || dir == "west") && (i%2 == 1 || j%2 == 1) )
                              entityTile = Pair.of(new Wall(i,j,internal,dir,elevation,color),new Torch(i,j,dir,elevation,color));
                        else
                              entityTile = Pair.of(new Wall(i,j,internal,dir,elevation,color),null);

                        break;

                        // Internal Wall;
                  case 'l':
                        internal = true;
                        dir = "west";
                        entityTile = Pair.of(new Wall(i, j, internal, dir, elevation, color),null);
                        break;
                  case 'L':
                        internal = true;
                        elevation = 1;
                        dir = "west";
                        entityTile = Pair.of(new Platform(i,j, color),new Wall(i, j, internal, dir, elevation, color));
                        break;
                  case 'k':
                        internal = true;
                        dir = "north";
                        entityTile = Pair.of(new Wall(i, j, internal, dir, elevation, color),null);
                        break;
                  case 'K':
                        internal = true;
                        elevation = 1;
                        dir = "north";
                        entityTile = Pair.of(new Platform(i,j, color),new Wall(i, j, internal, dir, elevation, color));
                        break;

                        // Door
                  case 'd':
                        if(blocked == true){
                              internal = false;
                              if( (dir == "north" || dir == "west") && (i%2 == 1 || j%2 == 1) )
                                    entityTile = Pair.of(new Wall(i,j,internal,dir,elevation,color),new Torch(i,j,dir,elevation,color));
                              else
                                    entityTile = Pair.of(new Wall(i,j,internal,dir,elevation,color),null);
                              break;
                        }
                        entityTile = Pair.of(new Door(i, j, dir,elevation, color,room),null);
                        break;

                        // Elevated Floor
                  case 'a':
                        entityTile = Pair.of(new Platform(i, j, color),null);
                        break;

                        // Ladder
                  case 'm':
                        dir = "north-south";
                        entityTile = Pair.of(new Ladder(i, j, dir,elevation, color),null);
                        break;
                  case 'n':
                        dir = "west-east";
                        entityTile = Pair.of(new Ladder(i, j, dir,elevation, color),null);
                        break;

                        // Pillar
                  case 'b':
                        entityTile = Pair.of(new Pillar(i, j,dir,elevation, color),null);
                        break;

                        // SafeZone
                  case 's':
                        entityTile = Pair.of(new SafeZone(i, j),null);
                        break;

                        // Chest
                  case 'o':
                        dir = "north-south";
                        entityTile = Pair.of(new Chest(i, j, dir, elevation),null);
                        break;
                  case 'O':
                        dir = "north-south";
                        elevation = 1;
                        entityTile = Pair.of(new Platform(i, j, color),new Chest(i, j, dir, elevation));
                        break;
                  case 'r':
                        dir = "east-west";
                        entityTile = Pair.of(new Chest(i, j, dir, elevation),null);
                        break;
                  case 'R':
                        dir = "east-west";
                        elevation = 1;
                        entityTile = Pair.of(new Platform(i, j, color),new Chest(i, j, dir, elevation));
                        break;

                        // Blank Space
                  case '.':
                        break;

                  default:
                        System.out.println("Error while loading EntityTile");
                        break;
            }

            return entityTile;
      }
}
