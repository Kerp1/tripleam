package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.powerup.IPowerUp;
import projectblast.model.attribute.powerup.RangePowerUp;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;

public class RangePowerUpTest {

    private Hero testHero = null;
    private IPowerUp powerUp = null;
    
    @Before
    public void before() {
        testHero = new Mage(new Position(0, 0), Direction.NORTH, null);
        powerUp = new RangePowerUp();
    }
    
    @Test
    public void testApply() {
        int heroRange = testHero.getPower();
        powerUp.apply(testHero);
        assertTrue(testHero.getPower() == heroRange + Constants.RANGE_POWERUP_MODIFIER);
    }

    @Test
    public void testReverse() {
        int heroRange = testHero.getPower();
        powerUp.reverse(testHero);
        assertTrue(testHero.getPower() == heroRange - Constants.RANGE_POWERUP_MODIFIER);
    }

    @Test
    public void testToString() {
        assertTrue(powerUp.toString().equals("Range"));
    }

}
