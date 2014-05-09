package projectblast.model.explosive;

import projectblast.model.BlastModel;
import projectblast.model.Entity;
import projectblast.model.Id;
import projectblast.model.Paralyzer;
import projectblast.model.Position;
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
		startMove();
		super.update();
		if(!BlastModel.isFree(this, getDirection(), getSpeed())) {
			move(getDirection(), getSpeed());
		}
	}
	

}
