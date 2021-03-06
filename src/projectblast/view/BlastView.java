package projectblast.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import projectblast.model.IBlastModel;
import projectblast.model.Player;
import projectblast.model.entity.Entity;
import projectblast.model.entity.Tower;
import projectblast.model.entity.hero.Hero;
import projectblast.model.helper.Constants;


/**
 * 
 * @author A.Freudenthaler
 *
 */
public class BlastView implements IBlastView {
	
	private IBlastModel model;
	private ImageDatabase images;
	private StatusBar statusBar;
	private List<Entity> entities;
	private List<List<Entity>> entityRows;
	
	public BlastView(IBlastModel model){
		this.model  = model;
		this.images = ImageDatabase.getImageDatabase();
		statusBar   = new StatusBar(model);
		this.entities = model.getEntities();
		this.entityRows = new ArrayList<List<Entity>>();
		for(int i = 0; i < Constants.TILE_AMOUNT_Y; i++){
			entityRows.add(new ArrayList<Entity>());
		}
		
	}
	//TODO Magic numbers
	//This method sorts all entities in the y Position
		private void sortEntities(){
			int square;
			//Go through every row and sorts the entityRows list 
			for(int i = 0; i < Constants.TILE_AMOUNT_Y; i++){
				square = (i)*32;
				
				for(Entity e: entities){
					if(e.getY() >= square && e.getY() < square+32){
					entityRows.get(i).add(e);
					}	

				}
			}
			
			
		//Removes all entities then adds them in the correct order	
			entities.removeAll(entities);		

			for(int j = 0; j < Constants.TILE_AMOUNT_Y; j++){
				
			for(int i=0;i< entityRows.size(); i++){
			Collections.sort(entityRows.get(i));
			
			}
			entities.addAll(entityRows.get(j));
			
			entityRows.get(j).removeAll(entityRows.get(j));
			}
				
		}
	

	public void render(Graphics g) {
		
		//Draw background
		Image im = null;
		try {
			im = new Image("data/image/Pebbles.png");
		} catch (SlickException e1) {
			throw new NullPointerException("Pebbles.png does not exist!");
		}
		for (int x = 0; x < Constants.GAME_WIDTH; x += im.getWidth()){
			for (int y = 0; y < Constants.GAME_HEIGHT; y += im.getHeight()){
				im.draw(x, y);
			}
		}
		
		
		//Sort entities
		sortEntities();
		
		Color color;
		//Draw all entities
		for (Entity e: entities){
		   	g.drawAnimation(images.getAnimation(e), e.getX(), e.getY()); 
		   //	g.drawRect(e.getCollisionBox().getX(), e.getCollisionBox().getY(), e.getCollisionBox().getWidth(), e.getCollisionBox().getHeight());

		}
		
		//Draw mana bars if necessary
		for(Player p: model.getPlayers()){
			Hero h = p.getHero();
			
			
			if (h.getMana() < 100){
				g.setColor(Color.black);
				g.fillRect(h.getX(),h.getY()+Constants.TILE_SIZE,Constants.TILE_SIZE,8);
				g.setColor(h.getTeam().getColor());
				g.fillRect(h.getX(),h.getY()+Constants.TILE_SIZE,(Constants.TILE_SIZE * (h.getMana()/100f)),8);
			}
		}
	
		for (Entity e: entities){
			if (e instanceof Tower){
				Tower t = (Tower)e;
				String s;
				g.setColor(Color.cyan);
				g.drawString(t.getPowerUp().toString(), t.getX(), t.getY() + Constants.TILE_SIZE * 1.5f);
			}
		}
		 
		//Draw statusbar
		statusBar.render(g,model.getPlayers());
		 
		/* //Debug drawing of tiles
		for(int x = 0; x < Constants.GAME_WIDTH; x += 32) {
		    for(int y = 0; y < Constants.GAME_HEIGHT; y += 32) {
		        g.drawRect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
		    }
		}*/ 
	}
}

