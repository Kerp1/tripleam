package projectblast;

public class Player {

	private int score;
	private Hero hero;
	public Player(Hero hero){
		this.hero = hero;
	}
	
	public Hero getHero(){
		return hero;
	}
}