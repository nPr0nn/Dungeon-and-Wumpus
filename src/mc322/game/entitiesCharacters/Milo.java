package mc322.game.entitiesCharacters;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Milo extends Heros{

	int velocidade_anim = 10;
	private double temp = 0;
	
	public Milo(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Milo";
		this.updateDir =3;
		
	}
	
	@Override
	protected void interact(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atack(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hurt(int damage) {
		// TODO Auto-generated method stub
		
	}

	public void update(double dt) {
        temp += velocidade_anim*dt;
		
	}

	@Override
	public void renderer(Renderer r) {
		GameRenderer.drawCharacter(i,j,elevation,name,r, (int)(temp%6), this.updateDir,"idle");
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

}
