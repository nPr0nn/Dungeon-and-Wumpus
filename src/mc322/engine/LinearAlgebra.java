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

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }

      public static int getModulo(int number){
            if(number < 0) number = -number;
            return number;
      }

      public static int clamp(int value, int min, int max){
            if(value<min)return min;
            if(value>max)return max;
            return value;
      }

      public static boolean between(int value, int min, int max){
            if(value<min) return false;
            if(value>max) return false;
            return true;
      }
      
      public static int min(int num1,int num2)
      {
    	  if(num1<num2)
    		  return num1;
    	  return num2;
      }
      
      public static int max(int num1,int num2)
      {
    	  if(num1>num2)
    		  return num1;
    	  return num2;
      }
      
      public static boolean insideRec(int i,int j, int i0,int j0, int i1, int j1)
      {
    	  if(!between(i,min(i0,i1),max(i0,i1)))
    		  return false;
    	  if(!between(j,min(j0,j1),max(j0,j1)))
    		  return false;
    	  return true;
      }

}
