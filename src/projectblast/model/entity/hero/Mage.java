package projectblast.model.entity.hero;

import projectblast.model.*;
import projectblast.model.core.ICore;
import projectblast.model.core.ParalyzerCore;
import projectblast.model.entity.explosive.Explosive;
import projectblast.model.entity.explosive.Fireball;
import projectblast.model.helper.Constants;
import projectblast.model.helper.Id;
import projectblast.model.helper.Position;
import projectblast.model.powerup.*;

/**
 * 
 * @author franton
 *
 */
public class Mage extends Hero {
	public Mage(Position position, Direction direction, Team team) {
		super(position, direction,  team);
		setName(Id.MAGE);
		
	}

	@Override
	public Explosive primaryAbility() {
		Fireball fireball = new Fireball(new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())),  4,  getDirection(), this);
		if(getAmmo()<= 0 || !BlastModel.isFree(fireball, fireball.getDirection(), fireball.getSpeed())){
			return null;
		}else{
			setAmmo(getAmmo()-1);
			addExplosive(fireball);
			return fireball;
		}
	}

	@Override
	public ICore secondaryAbility() {
		return new ParalyzerCore(Constants.PARALYZER_TIME, new Position(BlastModel.snapToGrid(getX()), BlastModel.snapToGrid(getY())), getDirection());
	}

	@Override
	protected void addInitialPowerUps() {
		
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new SpeedPowerUp());
		addPowerUp(new RangePowerUp());
		addPowerUp(new AmmoPowerUp());
		addPowerUp(new AmmoPowerUp());
		addPowerUp(new AmmoPowerUp());
	}
}