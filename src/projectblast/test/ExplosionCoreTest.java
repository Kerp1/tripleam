package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.core.ExplosionCore;
import projectblast.model.entity.Block;
import projectblast.model.entity.SolidBlock;
import projectblast.model.*;

public class ExplosionCoreTest {

	@Test
	public void testCreate() {
		ArrayList<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.WEST);
		directionList.add(Direction.EAST);
		directionList.add(Direction.NORTH);
		directionList.add(Direction.SOUTH);
		BlastModel model = new BlastModel();
		Block sb = new SolidBlock(new Position(32,32));
		model.addEntity(sb);
		
		ExplosionCore ec = new ExplosionCore(1, new Position(0,0), 3,directionList);
		assertTrue(ec.getNextPosition().equals(new Position (0,0)));
		
		for(int i = 0; i < 13; i++){
			ec.step(null);
			ec.create();
			assertTrue(ec.getParts().size() == i+1);
		}
		
		ec = new ExplosionCore(1, new Position(0,0), 3,directionList);
		ec.step(sb);
		ec.create();
		assertTrue(ec.getParts().size() == 1);
	}
	@Test
	public void testStep(){
		ArrayList<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.WEST);
		directionList.add(Direction.EAST);
		directionList.add(Direction.NORTH);
		directionList.add(Direction.SOUTH);
		SolidBlock sb = new SolidBlock(new Position(32,32));
		ExplosionCore ec = new ExplosionCore(1, new Position(0,0), 3,directionList);
		
		for(int i = 0; i < 14; i++){
			ec.step(null);
			ec.create();
		}
		assertTrue(ec.isCreated());

		
		ec = new ExplosionCore(1, new Position(0,0), 3,directionList);
		
		//ExplosionCore should not be created until it has 13 parts
		for(int i = 0; i<14;i++){
			ec.step(null);
			ec.create();
			assertTrue(ec.getParts().size() == i+1);

		}
		assertTrue(ec.isCreated());
		assertFalse(ec.step(sb));


	}
	@Test
	public void testGetNextPosition(){
		ArrayList<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.WEST);
		directionList.add(Direction.EAST);
		directionList.add(Direction.NORTH);
		directionList.add(Direction.SOUTH);
		SolidBlock sb = new SolidBlock(new Position(64,0));
		ExplosionCore ec = new ExplosionCore(1, new Position(0,0), 3,directionList);
		Position pos = ec.getNextPosition();
		
		
		//Testing if the explosion core will create explosions in right places
		
		assertTrue(ec.getNextPosition().equals(ec.getStartingPosition()));
		if(ec.step(null)){
			ec.create();
		}
		assertTrue(ec.getNextPosition().equals(new Position(-32,0)));
		
		if(ec.step(null)){
			ec.create();
		}
		assertTrue(ec.getNextPosition().equals(new Position(-64,0)));
		
		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(-96,0)));
		
		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(32,0)));
		
		if(ec.step(sb)){
			ec.create();
		}
		
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,-32)));

		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,-64)));
		

		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,-96)));
		
		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,32)));
		
		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,64)));
		
		if(ec.step(null)){
			ec.create();
		}
		pos = ec.getNextPosition();
		assertTrue(ec.getNextPosition().equals(new Position(0,96)));
		
}
	
}
