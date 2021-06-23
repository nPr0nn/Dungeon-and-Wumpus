package mc322.game.itens;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;

public class HealthPotion extends Item{

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderer(Renderer r) {
		GameRenderer.drawBag(25,-10,0, "potion",r, 0, 0);
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		return "health potion";
	}

}
