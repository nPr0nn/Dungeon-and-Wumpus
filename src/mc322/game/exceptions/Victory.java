package mc322.game.exceptions;

public class Victory extends RuntimeException{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "You defeat the WUMPUS!";
      }

}
