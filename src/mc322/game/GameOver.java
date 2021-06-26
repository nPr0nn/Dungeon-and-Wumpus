package mc322.game;

public class GameOver extends Exception{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "All heros are dead, you lose!";
      }

}
