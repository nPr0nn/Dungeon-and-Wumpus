package mc322.game.entitiesCharacters;

import java.util.ArrayList;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.engine.LinearAlgebra;
import mc322.game.Room;
import mc322.game.Bag;
import mc322.game.itens.Item;

public abstract class Heroes extends Character{

      protected int selected = 0;

      public Heroes(int i, int j,double elevation) {
            super(i, j, elevation);
      }

      public void use(Room room,Bag bag)
      {
            int iDir[] = {0,-1,0,1};
            int jDir[] = {1,0,-1,0};

            if(room.getChest()!=null){
                  if(room.getChest().getPos().getFirst() == this.i + iDir[this.updateDir]  && room.getChest().getPos().getSecond() == jDir[this.updateDir]+this.j)
                  {
                        room.getChest().toggleAnimation();

                        ArrayList<Item> itens;
                        itens = room.getChest().getItens();
                        if(itens == null)
                              return;
                        for(int i = 0;i<itens.size();i++)
                        {
                              bag.addItem(itens.get(i));
                        }


                  }
            }
      }

      @Override
      public void renderer(Renderer r) {
            GameRenderer.drawCharacter(i,j,elevation, "Pointer",r, (int)updateFrame%nFrames, 0, "idle");
      }

      public void toggleSelect(){
            if(this.selected == 1) this.selected = 0;
            else if(this.selected == 0) this.selected = 1;
      }

      public void move(int i, int j,Room room) {
            if(!((LinearAlgebra.getModulo(i-this.i)==1 && this.j==j)||(LinearAlgebra.getModulo(j-this.j)==1 && this.i==i)))
                  return;
            if(verifyMovement(i,j,room)==false)
                  return;

            int lastI = this.i;
            int lastJ = this.j;
            this.i = i;
            this.j = j;
            room.move(lastI,lastJ,i,j,this);
            this.change_state("idle");
      }

      public boolean move(char dir,Room room, double timing_keys_move){
            if(timing_keys_move != 0) return false;
            int tI=0;
            int tJ=0;
            int newDir = updateDir;

            switch(dir){
                  case 'A':
                        tI = i;
                        tJ = j-1;
                        newDir = 2;
                        break;
                  case 'S':
                        tI = i-1;
                        tJ = j;
                        newDir = 1;
                        break;
                  case 'D':
                        tI = i;
                        tJ = j+1;
                        newDir = 0;
                        break;
                  case 'W':
                        tI = i+1;
                        tJ = j;
                        newDir = 3;
                        break;
            }
            this.updateDir = newDir;

            move(tI,tJ,room);
            return true;
      }

      protected boolean verifyMovement(int i, int j, Room room) {
            if(room == null){
                  System.out.println("erro: sala e nula");
                  return false;
            }
            if(room.isAccessible(i,j,this.elevation,this.legSize,this.updateDir,this)) return true;
            return false;
      }
}
