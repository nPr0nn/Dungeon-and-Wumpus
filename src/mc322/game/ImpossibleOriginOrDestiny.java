package mc322.game;

public class ImpossibleOriginOrDestiny extends RuntimeException{

      private static final long serialVersionUID = 4251364228306816660L;

      public String toString(){
            return "the position of origin or destiny of the entity is not possible";
      }

}
