package projectblast.model;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import projectblast.model.entity.Entity;

public interface IBlastModel {
	
	void movePlayer(int playerID, Direction dir);
	void stopPlayer(int playerID);
	
	void primary(int playerID);
	void secondary(int playerID);
	
	
	void update(GameContainer gc, StateBasedGame game, int delta);
	
	/**
	 * @return an unsorted list of entities.
	 */
	List<Entity> getEntities();
	List<Player> getPlayers();
	
	void addEntity(Entity e);
	void removeEntity(Entity e);

	void increaseScaleFactor(int add);

	void setScaleFactor(int scaleFactor);

	int getScaleFactor();

	void shiftBalance(int factor);

	int getBalance();

	int getTowerBalance();

	void endGame(Team winner);

	
}
