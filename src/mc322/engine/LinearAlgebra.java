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

}
