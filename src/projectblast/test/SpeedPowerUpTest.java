package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.powerup.IPowerUp;
import projectblast.model.attribute.powerup.SpeedPowerUp;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;

public class SpeedPowerUpTest {

    private Hero testHero = null;
    private IPowerUp powerUp = null;
    
    @Before
    public void before() {
        testHero = new Mage(new Position(0, 0), Direction.NORTH, null);
        powerUp = new SpeedPowerUp();
    }
    
    @Test
    public void testApply() {
        int heroSpeed = testHero.getSpeed();
        powerUp.apply(testHero);
        assertTrue(testHero.getSpeed() == heroSpeed + Constants.SPEED_POWERUP_MODIFIER);
    }

    @Test
    public void testReverse() {
        int heroSpeed = testHero.getSpeed();
        powerUp.reverse(testHero);
        assertTrue(testHero.getSpeed() == heroSpeed - Constants.SPEED_POWERUP_MODIFIER);
    }

    @Test
    public void testToString() {
        assertTrue(powerUp.toString().equals("Speed"));
    }

}
