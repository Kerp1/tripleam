package projectblast.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projectblast.model.attribute.Direction;
import projectblast.model.attribute.Position;
import projectblast.model.attribute.powerup.AmmoPowerUp;
import projectblast.model.attribute.powerup.IPowerUp;
import projectblast.model.entity.hero.*;
import projectblast.model.helper.Constants;

public class AmmoPowerUpTest {

    private Hero testHero = null;
    private IPowerUp powerUp = null;
    
    @Before
    public void before() {
        testHero = new Mage(new Position(0, 0), Direction.NORTH, null);
        powerUp = new AmmoPowerUp();
    }
    
    @Test
    public void testApply() {
        int heroAmmo = testHero.getAmmo();
        powerUp.apply(testHero);
        assertTrue(testHero.getAmmo() == heroAmmo + Constants.AMMO_POWERUP_MODIFIER);
    }

    @Test
    public void testReverse() {
    	powerUp.apply(testHero);
        int heroAmmo = testHero.getAmmo();
        powerUp.reverse(testHero);
        assertTrue(testHero.getAmmo() == heroAmmo - Constants.AMMO_POWERUP_MODIFIER);
    }

    @Test
    public void testToString() {
        assertTrue(powerUp.toString().equals("Ammo"));
    }

}
