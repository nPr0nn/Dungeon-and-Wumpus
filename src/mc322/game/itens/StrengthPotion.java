package mc322.game.itens;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class StrengthPotion extends Item{

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	public void renderer(Renderer r) {
		GameRenderer.drawBag(27,-10,0, "potion",r, 0, 1);
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		return "strength potion";
	}

}
