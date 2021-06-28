package mc322.game;

import java.util.ArrayList;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.engine.UnexpectedError;
import mc322.game.entitiesCharacters.Character;
import java.util.Random;
import mc322.game.exceptions.*;


public abstract class GameBrain{

      public static Pair<Integer, Integer> getOrigin(){
            //Purple:
            Pair <Integer, Integer> origin = Pair.of(11, 1);

            //Green:
            //Pair <Integer, Integer> origin = Pair.of(8, 12);

            //Yellow:
            //Pair <Integer, Integer> origin = Pair.of(6, 12);

            //Blue:
            //Pair <Integer, Integer> origin = Pair.of(10, 13);

            //Red:
            //Pair <Integer, Integer> origin = Pair.of(13, 12);

            //Black
            //Pair <Integer, Integer> origin = Pair.of(3, 15);
            return origin;
      }


      // Solve Maze with BFS
      //static class Node{
      //Pair <Integer, Integer> pt;
      //int dist;

      //Node(Pair <Integer, Integer> p, int dist){
      //this.pt = p;
      //this.dist = dist;
      //}
      //}
      //public static boolean isSafe(int i, int j, int n){
      //return (i >= 0) && (j >= 0) && (i < n) && (j < n); 
      //}

      //public static boolean isPossible(char mat[][], int i, int j, int dirI[], int dirJ[], int k){
      //int ni = i + dirI[k];
      //int nj = j + dirJ[k];

      //boolean cond1 = (".D".indexOf(mat[ni][nj]) != -1 && ".MN".indexOf(mat[i][j]) != -1);


      //boolean cond02 = mat[i][j] == 'N' && (k == 3 || k == 0);
      //boolean cond03 = mat[i][j] == 'M' && (k == 1 || k == 2);
      //boolean cond2 = mat[ni][nj] == 'N' && (k == 3 || k == 0);
      //boolean cond3 = mat[ni][nj] == 'M' && (k == 1 || k == 2);

      //boolean cond4 = mat[ni][nj] == 'U' && ("UMN".indexOf(mat[i][j]) != -1 );

      //if(mat[i][j]=='N'||mat[i][j]=='M')
      //{
      ////cond1=cond1&&(cond02||cond03);
      ////cond4=cond4&&(cond02||cond03);
      //}

      //return (cond1 || cond2 || cond3 || cond4);
      //}

      //      public static String solveMaze(char mat[][],int iBeg, int jBeg, int iEnd, int jEnd){
      //
      //            Pair <Integer, Integer> src  = Pair.of(jBeg, iBeg);
      //            Pair <Integer, Integer> dest = Pair.of(iEnd, jEnd);
      //            for(int i = 0;i<mat.length;i++){
      //                for(int j = 0;j<mat.length;j++) {
      //                      if(i == src.getFirst() && j == src.getSecond()) System.out.print('s');
      //                      else if(i == dest.getFirst() && j == dest.getSecond()) System.out.print('d');
      //                      else System.out.print(mat[i][j]);
      //                }
      //                System.out.println();
      //            }
      //            
      //            // Impossible to reach
      //            if(mat[jBeg][iBeg]=='#'){
      //                   throw new ImpossibleOriginOrDestiny();
      //            }
      //            if(mat[iEnd][jEnd]=='#') {
      //                  throw new ImpossibleOriginOrDestiny();
      //            }
      //
      //            //directions
      //            int dirI[] = {1,0,0,-1};
      //            int dirJ[] = {0,-1,1,0};
      //
      //            //BFS
      //            String solution = "";
      //            int n = mat.length;
      //
      //            int distance[][] = new int[n][n];
      //            boolean visited[][] = new boolean[n][n];
      //            ArrayDeque<Node> q = new ArrayDeque<>();
      //
      //            for(int d[] : distance) Arrays.fill(d, -1);
      //            distance[src.getFirst()][src.getSecond()] = 0;
      //            visited[src.getFirst()][src.getSecond()] = true;
      //
      //            Node s = new Node(src, 0);
      //            q.addLast(s);
      //
      //            boolean ok = false;
      //
      //            while (!q.isEmpty()){
      //                  Node curr = q.removeFirst();
      //                  Pair <Integer, Integer> pt = curr.pt;
      //                  int i = pt.getFirst();
      //                  int j = pt.getSecond();
      //
      //                  if (i == dest.getFirst() && j == dest.getSecond()){
      //                        int dist = curr.dist;
      //
      //                        distance[i][j] = dist;
      //                        String pathmoves = "";
      //
      //                        while (i != src.getFirst() || j != src.getSecond()){
      //                        	if (j > 0   && distance[i][j - 1] == dist - 1) { 
      //	                                if(isPossible(mat, i, j, dirI, dirJ, 1)){
      //	                                      pathmoves += 'W'; j--; 
      //	                                      int ni = i + dirI[0];
      //	                                      int nj = j + dirJ[0];
      //	                                      boolean cond1 = (".D".indexOf(mat[ni][nj]) != -1 && ".MN".indexOf(mat[i][j]) != -1);
      //	                                      System.out.println(cond1);
      //	                                }
      //	                          }
      //                        	  else if (i < n-1 && distance[i + 1][j] == dist - 1){ 
      //	                                if(isPossible(mat, i, j, dirI, dirJ, 0))
      //	                                      pathmoves += 'A'; i++; 
      //	                          }
      //	                          else if (j < n-1 && distance[i][j + 1] == dist - 1){ 
      //	                                if(isPossible(mat, i, j, dirI, dirJ, 2))
      //	                                      pathmoves += 'S'; j++; 
      //	                          }
      //	                          else if (i > 0   && distance[i - 1][j] == dist - 1){ 
      //	                                if(isPossible(mat, i, j, dirI, dirJ, 3))
      //	                                      pathmoves += 'D'; i--; 
      //	                          }
      //	                          else
      //	                          {
      //	                        	  System.out.println("mat: ");
      //	                        	  for(int iu = 0;iu<mat.length;iu++){
      //	                                  for(int ju = 0;ju<mat.length;ju++) {
      //	                                        if(iu == src.getFirst() && ju == src.getSecond()) System.out.print('s');
      //	                                        else if(iu == dest.getFirst() && ju == dest.getSecond()) System.out.print('d');
      //	                                        else System.out.print(mat[iu][ju]);
      //	                                  }
      //	                                  System.out.println();
      //	                              }
      //	                        	  System.out.println("distance: ");
      //	                        	  for(int iu = 0;iu<distance.length;iu++){
      //	                                  for(int ju = 0;ju<distance.length;ju++) {
      ////	                                        if(iu == src.getFirst() && ju == src.getSecond()) System.out.print('s');
      ////	                                        else if(iu == dest.getFirst() && ju == dest.getSecond()) System.out.print('d');
      ////	                                        else 
      //	                                        if(distance[iu][ju]==-1)
      //	                                        	System.out.print("0");
      //	                                        else
      //	                                        	System.out.print(distance[iu][ju]);
      //	                                  }
      //	                                  System.out.println();
      //	                              }
      //	                          }
      //                              dist--;
      //                        }
      //                        for(int k = pathmoves.length() - 1; k >= 0; --k) solution += pathmoves.charAt(k);
      //                        ok = true;
      //                        break;
      //                  }
      //
      //                  for(int k = 0; k < 4; k++){
      //                        int ni = i + dirI[k];
      //                        int nj = j + dirJ[k];
      //
      //                        if ( !isSafe(ni, nj, n) || visited[ni][nj]) continue;
      //
      //                        if( isPossible(mat, i, j, dirI, dirJ, k)) {
      //                              visited[ni][nj] = true;
      //                              Node adjCell = new Node(Pair.of(ni,nj), curr.dist + 1);
      //                              q.addLast(adjCell);
      //                              distance[ni][nj] = curr.dist + 1;
      //                        }
      //
      //                  }
      //
      //            }
      //
      //            if (ok)
      //        	{
      //            	System.out.println(solution);
      //            	return solution;
      //        	}
      //            return null;
      //      }

      private static String generateCommands(char map[][],int j,int i) throws ImpossibleOriginOrDestiny
      {
            i +=j;
            j = i-j;
            i-=j;

            String commands = "";
            int conting = 0;
            boolean notFound = true;
            while(conting < map.length*map.length/2 && notFound)
            {
                  switch(map[i][j])
                  {
                        case 'e':
                        case 'E':
                              notFound = false;
                              break;
                        case 'V':
                              i++;
                              commands+="D";
                              break;
                        case 'A':
                              i--;
                              commands+="A";
                              break;
                        case '<':
                              j--;
                              commands+="S";
                              break;
                        case '>':
                              j++;
                              commands+="W";
                              break;
                        default:
                              //System.out.println("vendo: "+map[i][j]+" commands: "+commands);
                              throw new ImpossibleOriginOrDestiny();

                  }
            }
            return commands;
      }

      public static String solveMaze(char map[][],int iBeg,int jBeg, int iEnd, int jEnd) throws ImpossibleOriginOrDestiny // for Square maps
            , UnexpectedError
            {
                  char auxMap[][] = new char[map.length][map.length];
                  iBeg +=jBeg;
                  jBeg = iBeg-jBeg;
                  iBeg-=jBeg;
                  for(int i = 0;i<map.length;i++)
                  {
                        for(int j = 0;j<map.length;j++)
                        {
                              auxMap[i][j] = map[i][j];
                        }
                  }

                  //set begin
                  if(map[iBeg][jBeg]=='#')
                        throw new ImpossibleOriginOrDestiny();

                  //set end
                  if(map[iEnd][jEnd]=='.')
                        map[iEnd][jEnd]='e';
                  else if(map[iEnd][jEnd]=='#')
                        throw new ImpossibleOriginOrDestiny();
                  else if(map[iEnd][jEnd]=='D')
                        map[iEnd][jEnd]='e';
                  //throw new DoorSelected();
                  else
                        map[iEnd][jEnd]='E';



                  //arrays of new points
                  ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
                  ArrayList<Pair<Integer,Integer>> newNews = new ArrayList<Pair<Integer,Integer>>();

                  //directions
                  int dirI[] = {1,0,0,-1};
                  int dirJ[] = {0,-1,1,0};

                  //add origin
                  newNews.add(Pair.of(iEnd,jEnd));


                  boolean running = true;

                  int counting = 0;
                  while(running && counting <map.length*map.length/2)
                  {
                        //pass all newnews points to new
                        news.clear();
                        for(int i = 0;i<newNews.size();i++)
                        {
                              news.add(newNews.get(i));
                        }
                        newNews.clear();

                        //if there is no points to solve, break
                        if(news.size()==0)
                        {
                              running = false;
                              break;
                        }

                        //run all points
                        for(int i = 0;i<news.size();i++)
                        {
                              if(news.get(i).getFirst() == iBeg && news.get(i).getSecond()== jBeg)
                              {
                                    running = false;
                                    break;
                              }

                              //in all directions
                              for(int k =0;k<4;k++)
                              {
                                    //inside the map
                                    if(!LinearAlgebra.between(news.get(i).getFirst()+dirI[k],0,map.length-1) || !LinearAlgebra.between(news.get(i).getSecond()+dirJ[k],0,map.length-1))
                                          continue;

                                    //to think better solutions, the v and c comments are relative to the character walking (and not this algoritm)
                                    //where is under me
                                    char v = map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]];
                                    //where i go
                                    char c = auxMap[news.get(i).getFirst()][news.get(i).getSecond()];

                                    if(v=='#'||v=='E'||v=='e'||v=='A'||v=='V'||v=='<'||v=='>')
                                          continue;


                                    if(v=='U')
                                    {
                                          if(c=='.')
                                                continue;
                                          if(c=='N')
                                                if(k==1||k==2)
                                                      continue;
                                          if(c=='M')
                                                if(k==0||k==3)
                                                      continue;

                                    }
                                    if(v=='.')
                                    {
                                          if(c=='U')
                                                continue;
                                          if(c=='N')
                                                if(k==1||k==2)
                                                      continue;
                                          if(c=='M')
                                                if(k==0||k==3)
                                                      continue;

                                    }

                                    if(v=='M')
                                    {
                                          if(c=='M')
                                                if(k==0||k==3)
                                                      continue;

                                    }

                                    if(v=='N')
                                    {
                                          if(c=='N')
                                                if(k==1||k==2)
                                                      continue;

                                    }



                                    switch(k)
                                    {
                                          case 0:
                                                map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = 'A';
                                                break;
                                          case 1:
                                                map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = '>';
                                                break;
                                          case 2:
                                                map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = '<';
                                                break;
                                          case 3:
                                                map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = 'V';
                                                break;
                                    }

                                    newNews.add(Pair.of(news.get(i).getFirst()+dirI[k], news.get(i).getSecond()+dirJ[k]));
                              }
                              if(!running)
                                    break;
                        }
                        counting ++;
                  }

                  return generateCommands(map,iBeg,jBeg);
            }


      static void walk(Dungeon dungeon, double timing_keys_move,boolean combat) {
            if(!dungeon.getFollow()){
                  GameRenderer.change_animation_state("idle", dungeon);
                  dungeon.getCurrentRoom().getPlayer().change_state("moving");
                  return;
            }

            if(combat)
                  return;
            Room cRoom = dungeon.getCurrentRoom();
            Random rand = new Random();

            if(cRoom.getMilo()!=null && !cRoom.getMilo().getSelected()){
                  cRoom.getMilo().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);

                  if(rand.nextInt(9)<7 && cRoom.getLuna()!=null)
                        cRoom.getMilo().followHero(cRoom.getLuna(),cRoom,false,timing_keys_move);
                  else if(cRoom.getRaju()!=null)
                        cRoom.getMilo().followHero(cRoom.getRaju(),cRoom,false,timing_keys_move);
                  else
                        cRoom.getMilo().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);
            }

            if(cRoom.getLuna()!=null && !cRoom.getLuna().getSelected()){
                  if(rand.nextInt(13)<9) 
                        cRoom.getLuna().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);
                  else if(cRoom.getZe()!=null)
                        cRoom.getLuna().followHero(cRoom.getZe(),cRoom,false,timing_keys_move);
                  else
                        cRoom.getLuna().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);
            }

            if(cRoom.getZe()!=null && !cRoom.getZe().getSelected()) {
                  cRoom.getZe().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);

                  if(rand.nextInt(15)<8 && cRoom.getRaju()!=null) 
                        cRoom.getZe().followHero(cRoom.getRaju(),cRoom,false,timing_keys_move);
                  else if(cRoom.getLuna()!=null)
                        cRoom.getZe().followHero(cRoom.getLuna(),cRoom,false,timing_keys_move);
                  else
                        cRoom.getZe().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);
            }

            if(cRoom.getRaju()!=null && !cRoom.getRaju().getSelected()){
                  cRoom.getRaju().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);

                  if(rand.nextInt(8)<5 && cRoom.getMilo()!=null)
                        cRoom.getRaju().followHero(cRoom.getMilo(),cRoom,false,timing_keys_move);
                  else if(cRoom.getLuna()!=null)
                        cRoom.getRaju().followHero(cRoom.getLuna(),cRoom,false,timing_keys_move);
                  else
                        cRoom.getRaju().followHero(cRoom.getPlayer(),cRoom,false,timing_keys_move);
            }
      }

      public static boolean mouse_action(Dungeon dungeon, double timing_keys_move, 
                  boolean movingToPointer, Pair<Integer, Integer> p,Character charac,boolean combat,boolean enemy){

            boolean mov = charac.followPointer(p.getFirst(),p.getSecond(),dungeon.getCurrentRoom(),true,timing_keys_move,movingToPointer,enemy);
            GameBrain.walk(dungeon, timing_keys_move,combat);
            return mov;
      }

      public static Pair<Integer, Integer> chooseCloserHero(char[][] map, int iBeg, int jBeg, int range) {

            int distance = 0;
            int distances[][] = new int[map.length][map.length];
            for(int i0 = 0;i0<map.length;i0++)
            {
                  for(int j0 = 0;j0<map.length;j0++)
                  {
                        distances[i0][j0]=-1;
                  }
            }

            int dirI[]= {0,0,1,-1};
            int dirJ[]= {1,-1,0,0};

            ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
            news.add(Pair.of(iBeg,jBeg));
            distances[iBeg][jBeg]=0;

            while(distance<range)
            {
                  //			for(int i0 = 0;i0<map.length;i0++,System.out.println())
                  //			{
                  //				for(int j0 = 0;j0<map.length;j0++)
                  //				{
                  //					if(i0==iBeg && j0 == jBeg)
                  //						System.out.print('B');
                  //					else
                  //					System.out.print(map[i0][j0]);
                  //				}
                  //			}
                  //			for(int i0 = 0;i0<map.length;i0++,System.out.println())
                  //			{
                  //				for(int j0 = 0;j0<map.length;j0++)
                  //				{
                  //					System.out.print((distances[i0][j0]+1)%9);
                  //				}
                  //			}


                  int nElements = news.size();
                  for(int n = 0;n<nElements;n++)
                  {
                        for(int k=0;k<4;k++)
                        {
                              if(news.get(n).getFirst()+dirI[k]<0||news.get(n).getFirst()+dirI[k]>=map.length||news.get(n).getSecond()+dirJ[k]<0||news.get(n).getSecond()+dirJ[k]>=map.length)
                                    continue;
                              if(map[news.get(n).getFirst()+dirI[k]][news.get(n).getSecond()+dirJ[k]] == 'O')
                                    return Pair.of(news.get(n).getFirst()+dirI[k],news.get(n).getSecond()+dirJ[k]);
                              if(distances[news.get(n).getFirst()+dirI[k]][news.get(n).getSecond()+dirJ[k]]==-1)
                              {
                                    distances[news.get(n).getFirst()+dirI[k]][news.get(n).getSecond()+dirJ[k]] = distance;
                                    news.add(Pair.of(news.get(n).getFirst()+dirI[k],news.get(n).getSecond()+dirJ[k]));
                              }
                        }
                  }
                  for(int n = 0;n<nElements;n++)
                        news.remove(0);
                  distance ++;
            }
            Random rand= new Random();
            while(true)
            {
                  //System.out.println("decidinfo onde ir");

                  int k = rand.nextInt(map.length);
                  int l = rand.nextInt(map.length);
                  if(map[k][l]=='.'||map[k][l]=='U')
                        return Pair.of(k,l);

            }

      }

}
