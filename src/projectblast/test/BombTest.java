package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.entity.Id;
import projectblast.model.entity.explosive.Bomb;
import projectblast.model.entity.hero.Bomber;
import projectblast.model.helper.Constants;

public class BombTest {

	private Bomb bomb;
	private Bomber bomber; 
	
	@Before
	public void before() {
		bomber = new Bomber(new Position(5,5), null, null);
		bomb = new Bomb(new Position(5, 5), 4, Direction.NORTH, bomber);
	}
	
	@Test
	public void testBomb() {
		assertTrue(bomb.getName().equals(Id.BOMB));
		assertFalse(bomb.getDirection() == Direction.NONE);
		assertFalse(bomb.isMoving());
		assertFalse(bomb.isDestroyed());
		assertTrue(bomb.isOnGrid());
		assertFalse(bomb.getLife()<=0);
	}
	
	@Test
	public void testAllowPassage() {
		assertTrue(bomb.allowPassage(bomber));
		bomber.move(100,100);
		assertFalse(bomb.allowPassage(bomber));
	}
	
	
	@Test
	public void testUpdate() {
		assertTrue(bomb.getLife() == Constants.BOMB_TIME);
		bomb.update();
		assertFalse(bomb.isMoving());
		assertFalse(bomb.getLife() == Constants.BOMB_TIME);
		for(int i = 0; i < Constants.BOMB_TIME-1; i++){
			bomb.update();
		}
		assertTrue(bomb.isDestroyed());
	}
	


}
