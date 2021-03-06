package projectblast.view;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import projectblast.model.IBlastModel;
import projectblast.model.Player;
import projectblast.model.helper.Constants;


public class StatusBar {
	private IBlastModel model;
	
	public StatusBar(IBlastModel m){
		this.model = m;
	}
	
	
	
	public void render(Graphics g, List<Player> players){
		List<Player> left = new ArrayList<Player>();
		List<Player> right = new ArrayList<Player>();
		
		left.add(players.get(0));
		//Split players into teams
		for (Player p: players){
			if (p.getHero().getTeam().equals(players.get(0).getHero().getTeam())){
				
				left.add(p);
			} else {
				right.add(p);
			}
		}
		if (right.isEmpty() || left.isEmpty()){
			throw new NullPointerException("There must be at least one player per team! \nCheck StatusBar render method!");
		}
		
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, Constants.GAME_WIDTH, Constants.MAP_YOFFSET);
		
		//Left team
		g.setColor(left.get(0).getHero().getTeam().getColor());
		g.drawString(left.get(0).getHero().getTeam().getName(), 0, 0);
		g.drawString("Deaths: " + left.get(0).getHero().getDeathCount(), 0, 30);
		//Right team
		g.setColor(right.get(0).getHero().getTeam().getColor());
		g.drawString(right.get(0).getHero().getTeam().getName(), Constants.GAME_WIDTH - 128, 0);
		g.drawString("Deaths: " + right.get(0).getHero().getDeathCount(), Constants.GAME_WIDTH - 128, 30);
		//Tug of War
		if (model.isGameOver() == 0){
			g.setColor(right.get(0).getHero().getTeam().getColor());
			g.fillRect(Constants.GAME_WIDTH/2 - 500, 16, 1000, 32);
			g.setColor(left.get(0).getHero().getTeam().getColor());
			g.fillRect(Constants.GAME_WIDTH/2 - 500, 16, model.getBalance() + 500, 32);
		} else {
			g.setColor(model.getWinner().getColor());
			g.drawString("A winner is " + model.getWinner().getName() + "!", Constants.GAME_WIDTH/2 - 96, Constants.MAP_YOFFSET/2);
		}
		
	}

}
