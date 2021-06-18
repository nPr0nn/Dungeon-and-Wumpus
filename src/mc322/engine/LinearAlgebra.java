package mc322.engine;

import java.util.ArrayList;

import mc322.engine.Pair;

public abstract class LinearAlgebra{
      public static Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
            int nx = p.getFirst() + p.getSecond();
            int ny = (p.getSecond() - p.getFirst())/2;

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }
      
      public static Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
    	  int ny = (p.getSecond()*2 + p.getFirst())/2;
    	  int nx = p.getFirst() - ny;
//    	  int nx = p.getFirst()/2 - p.getSecond();
//        int ny = p.getFirst()/2 + p.getFirst();
            
            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }

      public static Pair<Integer, Integer> getOrigin(){
            Pair <Integer, Integer> origin = Pair.of(11, 7);
            return origin;
      }

      public static int getModulo(int number){
            if(number < 0) number = -number;
            return number;
      }
      
      public static int clamp(int value, int min, int max )
  	{
  		if(value<min)
  			return min;
  		if(value>max)
  			return max;
  		return value;
  	}
  	
  	public static boolean between(int value, int min, int max)
  	{
  		if(value<min)
  			return false;
  		if(value>max)
  			return false;
  		return true;
  	}
  	
  	public static char[][] solveMaze(char map[][]) // for Square maps
  	{
  		char auxMap[][] = new char[map.length][map.length];
  		for(int i = 0;i<map.length;i++)
  		{
  			for(int j = 0;j<map.length;j++)
  			{
  				auxMap[i][j] = map[i][j];
  				
  			}
  		}
  		ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
  		ArrayList<Pair<Integer,Integer>> newNews = new ArrayList<Pair<Integer,Integer>>();
  		int dirI[] = {1,0,0,-1};
  		int dirJ[] = {0,-1,1,0};
  		
  		for(int i = 0;i<map.length;i++)
  		{
  			for(int j = 0;j<map.length;j++)
  			{
  				if(map[i][j]=='E' || map[i][j]=='e')
  					newNews.add(Pair.of(i,j));
  				
  			}
  		}
  		
  		boolean running = true;
  		
  		int counting = 0;
  		while(running && counting <map.length*map.length/2)
  		{
  			for(int i = 0;i<newNews.size();i++)
  			{
  				news.add(newNews.get(i));
  			}
  			
  			newNews.clear();
  			
  			if(news.size()==0)
  			{
  				running = false;
  				break;
  			}
  			
  			for(int i = 0;i<news.size();i++)
  			{
  				for(int k =0;k<4;k++)
  				{
  					if(!between(news.get(i).getFirst()+dirI[k],0,map.length-1) || !between(news.get(i).getSecond()+dirJ[k],0,map.length-1))
  						continue;
  					char v = map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]];
  					if(v=='.' || v=='B' || v == 'U' || v == 'M' || v == 'N' || v == 'E' || v == 'e' || v == 'b')
  					{
  						
  						char c = auxMap[news.get(i).getFirst()][news.get(i).getSecond()];
  						if(c == 'B' || c == 'b')
  						{
  							running = false;
  							break;
  						}
  						if(v=='U')
  	  					{
  	  						if(c != 'U' && c != 'M' && c != 'N' && c != 'E')
  	  							continue;
  	  					}
  						if(v == 'N')
  						{
  							if(k==0 || k == 3) // n deve conter: 2  1  deve conter: 3 4
  								continue;
  						}
  						if(v == 'M')
  						{
  							if(k==1 || k == 2 || k ==0 ) // n deve conter: 3 4    deve conter: 2 1
  								continue;
  						}
  						if(v == '.')
  						{
  							if(c != '.' && c != 'M' && c != 'N' && c != 'e')
  							{
  								continue;
  							}
  							//System.out.println("Sou "+ c+" e vou para "+ v);
  						}
  						if(v == 'b')
  						{
  							if(c != '.' && c != 'M' && c != 'N' && c != 'e')
  							{
  								continue;
  							}
  							//System.out.println("Sou "+ c+" e vou para "+ v);
  						}
  						if(v == 'B')
  						{
  							if(c != 'U' && c != 'M' && c != 'N' && c != 'E')
  							{
  								continue;
  							}
  							//System.out.println("Sou "+ c+" e vou para "+ v);
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
  					
  						
  				}
  				if(!running)
  					break;
  			}
  			news.clear();
  			counting ++;
  		}
  		
  		return map;
  	}
}
