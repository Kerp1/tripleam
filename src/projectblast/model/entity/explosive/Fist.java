package projectblast.model.entity.explosive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import projectblast.model.BlastModel;
import projectblast.model.Direction;
import projectblast.model.core.ExplosionCore;
import projectblast.model.core.ICore;
import projectblast.model.entity.Entity;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;

public class Fist extends Explosive {

	public Fist(Position position, int speed, Direction direction, Hero owner) {
		super(position, speed, direction, owner);
		setName(Id.FIST);
		setLife(Constants.FIST_TIME);
		setPosition(BlastModel.snapToGrid(getPosition()));
	}
	
	@Override
	public boolean allowPassage(Entity entity){
		return false;
	}
	
	public void update(){
		setLife(getLife()- 1);
		if(getLife()==0){
			destroy();
		}
	}
	
	public ICore getCore() {
		List<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.EAST);
		directionList.add(Direction.NORTH);
		directionList.add(Direction.WEST);
		directionList.add(Direction.SOUTH);
		Iterator<Direction> iter = directionList.iterator();
		while(iter.hasNext()){
			if(iter.next() == getDirection().opposite()){
				iter.remove();
			}
		}
		ExplosionCore core = new ExplosionCore(Constants.EXPLOSION_TIME, BlastModel.snapToGrid(getPosition()), getOwner().getPower(), directionList);
		
		return core;
	}

}
