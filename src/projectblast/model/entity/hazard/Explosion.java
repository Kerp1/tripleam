package projectblast.model.entity.hazard;

import org.newdawn.slick.geom.Rectangle;

import projectblast.model.attribute.Position;
import projectblast.model.entity.Destructible;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Id;
import projectblast.model.entity.Tower;


public class Explosion extends Hazard {
	
	public Explosion(Position position) {
		super(position,  new Rectangle(position.getX() + 1, position.getY() + 1, 30, 30));
		setName(Id.EXPLOSION);
	}


	@Override
	public boolean allowPassage(Entity entity) {
		return true;
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof Destructible && !(entity instanceof Tower)) {
			Destructible dest = (Destructible)entity;
			dest.destroy();
		}
		
	}

	@Override
	public void update() {
	}
	
}
