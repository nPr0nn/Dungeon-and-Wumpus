package mc322.game.itens;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class ResistancePotion extends Item{

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	public void renderer(Renderer r) {
		GameRenderer.drawBag(29, -10, 0, "potion",r, 0, 2);
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		return "resistance potion";
	}

}
