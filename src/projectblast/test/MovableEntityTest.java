package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import projectblast.model.*;
import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.entity.DestructibleBlock;
import projectblast.model.entity.Id;
import projectblast.model.entity.MovableEntity;

public class MovableEntityTest {

	@Test
	public void testMovableEntity() {
		
		MovableEntity a = new DestructibleBlock(new Position(1,1), 1, Direction.EAST, new Rectangle(1,1,1,1));
		assertTrue(a.getDirection() == Direction.EAST);
		assertTrue(a.getPosition() != new Position(1,1));
		assertTrue(a.getPosition().equals(new Position(1,1)));
		assertFalse(a.getPosition().equals(new Position(1,2)));
		assertTrue(a.getSpeed() == 1);
		
		
	}
	
	@Test
	public void testName() {
		
		MovableEntity a = new DestructibleBlock(new Position(1,1), 1, Direction.EAST, new Rectangle(1,1,1,1));
		assertTrue(a.getName() == null);
		a.setName(Id.TOWER);
		assertTrue(a.getName() == Id.TOWER);
	}
	
	@Test
	public void testMovement() {
		
		MovableEntity a = new DestructibleBlock(new Position(2,1), 1, Direction.EAST, new Rectangle(1,1,1,1));
		a.setSpeed(2);
		assertTrue(a.getSpeed() == 2);
		a.move(Direction.WEST);
		
		assertFalse(a.getDirection() == Direction.WEST);
		assertFalse(a.isMoving());
		
		assertTrue(a.getSpeed() == 2);
		assertTrue(a.getPosition().equals(new Position(1,1)));
		a.place(50,50);
		assertTrue(a.getPosition().equals(new Position(50,50)));
		
		a.startMove(Direction.NORTH);
		assertTrue(a.getDirection() == Direction.NORTH);
		assertTrue(a.getPosition().equals(new Position(50,50)));
		assertTrue(a.getSpeed() == 2);
		//a.update();
		//assertTrue(a.getPosition().equals(new Position(50,48)));
	}


}
