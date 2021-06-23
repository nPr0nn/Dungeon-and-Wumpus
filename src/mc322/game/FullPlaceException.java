package mc322.game;

public class FullPlaceException extends RuntimeException{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "This place is already full";
      }

}
