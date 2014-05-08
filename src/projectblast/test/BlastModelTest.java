package projectblast.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.newdawn.slick.Color;

import projectblast.model.BlastModel;
import projectblast.model.Player;
import projectblast.model.Position;
import projectblast.model.Team;
import projectblast.model.Movable.Direction;
import projectblast.model.Team.Side;
import projectblast.model.hero.Brute;
import projectblast.model.hero.Hero;
import projectblast.model.hero.Mage;

public class BlastModelTest {

	@Test
	public void testSnapToGrid() {
		Position pos = new Position(142,193);
		BlastModel model = new BlastModel();
		pos = model.snapToGrid(pos);
		assertTrue(pos.getX()%32 == 0);
		assertTrue(pos.getY()%32 == 0);
		
		pos = new Position(12,12);
		pos = model.snapToGrid(pos);
		assertTrue(pos.getY()%32 == 0);
		assertTrue(pos.getY()%32 == 0);
	}
	@Test
	public void testPrimary(){
		
	}
	@Test
	public void testMovePlayer(){
		List<Player> players = new ArrayList<Player>();
		Position pos = new Position(1,1);
		int speed = 1;
		Direction dir = Direction.EAST;
		Team team = new Team("Test", Color.red, Side.LEFT );
		Hero mage = new Mage(pos,dir,team);
		players.add(new Player(mage));
		
		BlastModel model = new BlastModel(players);
		
		int xPos = mage.getX();
		model.movePlayer(1, Direction.EAST);
		
		assertTrue(mage.getX() != xPos);
		assertTrue(mage.getX() == xPos+4);
		
		xPos = mage.getX();
		model.movePlayer(1, Direction.WEST);
		assertTrue(mage.getX() == xPos-4);
		
		int yPos = mage.getY();
		model.movePlayer(1, Direction.NORTH);
		assertTrue(mage.getY() == yPos-4);
		
		yPos = mage.getY();
		model.movePlayer(1, Direction.SOUTH);
		assertTrue(mage.getY() == yPos+4);

		
		xPos = mage.getX();
		yPos = mage.getY();
		Position magePos = new Position(xPos, yPos);
		Hero brute = new Brute(magePos,dir,team);
		players.add(new Player(brute));

		
		model.movePlayer(2, Direction.EAST);
		assertTrue(mage.getX() == brute.getX());
		assertTrue(mage.getY() == brute.getY());
		
		model.movePlayer(1, Direction.EAST);
		assertTrue(mage.getX() == brute.getX());
		assertTrue(mage.getY() == brute.getY());
	}
}