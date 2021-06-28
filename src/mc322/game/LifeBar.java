package mc322.game;

import java.util.ArrayList;

import mc322.engine.BasicObject;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.itens.HealthPotion;
import mc322.game.itens.Item;
import mc322.game.itens.Key;
import mc322.game.itens.ResistancePotion;
import mc322.game.itens.StrengthPotion;

public class LifeBar implements BasicObject{
      int lunaBarX, lunaBarY, lunaHp, lunaHpMax;
      int miloBarX, miloBarY, miloHp, miloHpMax;
      int rajuBarX, rajuBarY, rajuHp, rajuHpMax;
      int   zeBarX,   zeBarY,   zeHp,   zeHpMax;

      public LifeBar(){
            lunaBarX = 0;
            lunaBarY = 10;

            miloBarX = 0;
            miloBarY = 30;

            rajuBarX = 0;
            rajuBarY = 50;

            zeBarX   = 0;
            zeBarY   = 70;
      }

      public void setLifes(Room cRoom){
            if(cRoom.getLuna() != null)
                  lunaHpMax = cRoom.getLuna().getHpMax();
            if(cRoom.getMilo() != null)
                  miloHpMax = cRoom.getMilo().getHpMax();
            if(cRoom.getRaju() != null)
                  rajuHpMax = cRoom.getRaju().getHpMax();
            if(cRoom.getZe() != null) 
                  zeHpMax = cRoom.getZe().getHpMax();


            if(cRoom.getLuna() != null) lunaHp = cRoom.getLuna().getHp();
            else lunaHp = 0;

            if(cRoom.getMilo() != null) miloHp = cRoom.getMilo().getHp();
            else miloHp = 0;

            if(cRoom.getRaju() != null) rajuHp = cRoom.getRaju().getHp();
            else  rajuHp = 0;

            if(cRoom.getZe() != null) zeHp = cRoom.getZe().getHp();
            else zeHp = 0;
      }

      public void update(double dt){
            return;
      }
      public void renderer(Renderer r){
            GameRenderer.drawLife(lunaBarX, lunaBarY, lunaHpMax, lunaHp, "Luna",r);
            GameRenderer.drawLife(miloBarX, miloBarY, miloHpMax, miloHp, "Milo",r);
            GameRenderer.drawLife(rajuBarX, rajuBarY, rajuHpMax, rajuHp, "Raju",r);
            GameRenderer.drawLife(zeBarX, zeBarY, zeHpMax, zeHp, "Ze",r);

      }
}
