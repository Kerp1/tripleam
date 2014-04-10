package projectblast.model;


/**
 * @author A.Freudenthaler
 *
 */
public class Fireball extends Explosive {
	private Hero owner;
	private boolean isDestroyed;
	
	public Fireball(Position position,  int speed,  Direction direction, Hero owner) {
		super(position,  speed, direction,  owner);
		startMove();
		setName("Fireball");
		this.owner = owner;
	}

	@Override
	public void destroy() {
		if(this.getCollisionBox().intersects(owner.getCollisionBox())){
			throw new NullPointerException("Fireball hit its owner");
		}else{
			isDestroyed = true;
		}
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

}
