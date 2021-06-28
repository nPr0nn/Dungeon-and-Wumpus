package mc322.game.exceptions;

public class ChangeRoomInvalidChar extends RuntimeException{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "the char sent has no meaning";
      }

}
