package projectblast.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import projectblast.model.Movable.Direction;


public class BlastModel implements IBlastModel {
	
	private List<Entity> entities;
	private List<Player> players;
	
	public BlastModel(){ //Ska bytas ut mot BlastFactory??
		this(new LinkedList<Player>());
	}
	
	public BlastModel(List<Player> players){
		this.players = players;  
		this.entities = new LinkedList<Entity>();
		try {
			entities.addAll(MapReader.createEntities(new TiledMap("data/map/Map.tmx")));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		Animation[] animations = new Animation[4];
    	Image[] images = new Image[4];
    	try {
			images[0] = new Image("/data/image/SnowmanHeroDown.png");
			images[1] = new Image("/data/image/SnowmanHeroRight.png");
			images[2] = new Image("/data/image/SnowmanHeroUp.png");
			images[3] = new Image("/data/image/SnowmanHeroLeft.png");
			
		} catch (SlickException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
    	
    	animations[0] = new Animation(images, 1000);
        Hero hero = new Mage(200, 200, images[0], 4, Movable.Direction.EAST, animations, new Team("bomb", Color.red));
		players.add(new Player(hero));
		System.out.println(players.get(0));
		
		for(Player p: players){
			entities.add(p.getHero());
		}
		
	}
	@Override
	public void left(int playerID) {
		players.get(playerID-1).getHero().startMove(Direction.WEST);

	}

	@Override
	public void right(int playerID) {
		players.get(playerID-1).getHero().startMove(Direction.EAST);

	}

	@Override
	public void up(int playerID) {
		players.get(playerID-1).getHero().startMove(Direction.NORTH);

	}

	@Override
	public void down(int playerID) {
		players.get(playerID-1).getHero().startMove(Direction.SOUTH);

	}

	@Override
	public void primary(int playerID) {
		players.get(playerID-1).getHero().primaryAbility();

	}

	@Override
	public void secondary(int playerID) {
		players.get(playerID-1).getHero().secondaryAbility();
	}

	@Override
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	
	public void update(){
		//TODO remove hardcoding
		for (Player p: players){
			p.getHero().update();
		}
	}

	@Override
	public void stop(int playerID) {
		players.get(playerID-1).getHero().stopMove();
		
	}
	
	public boolean isFree(Rectangle r){
		
		for (Entity e: entities){
	    	if (e.getCollisionBox().intersects(r)){
	    		return false;
	    	}
	    }
		return true;
	}

}
