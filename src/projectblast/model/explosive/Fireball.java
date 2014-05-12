package projectblast.model.explosive;

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.Id;
import projectblast.model.Position;
import projectblast.model.hazard.Paralyzer;
import projectblast.model.hero.Hero;


/**
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	
	
	public Fireball(Position position,  int speed,  Direction direction, Hero owner) {
		super(position,  speed, direction,  owner);
		setName(Id.FIREBALL);
		
	}
	
	@Override
	public boolean allowPassage(Entity entity){
		return true;
	}
	
	@Override
	public void collide(Entity entity) {
		if(entity != getOwner() && !(entity instanceof Paralyzer)) {
			this.destroy();
		}
	}
	
	public void update() {
		//TODO fix that fireball is an exception to other movables. Fireball want to enter all other entities, and then explode. <--- problem
		startMove(); //Fireball always try to move
		super.update(); //Parent decide how to move
		if(!BlastModel.isFree(this, getDirection(), getSpeed())) { //When I made hero move like the others Fireball became an Exception =(
			move(getDirection(), getSpeed()); //Fireball is rebellious, doesn't care if parent tells it don't move!
		}
	}
	

}
