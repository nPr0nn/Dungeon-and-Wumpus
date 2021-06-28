package mc322.game.exceptions;

public class GameOver extends RuntimeException{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "All heros are dead, you lose!";
      }

}
