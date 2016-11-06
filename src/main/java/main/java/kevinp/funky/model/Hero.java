package main.java.kevinp.funky.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.kevinp.funky.constants.Constants;

/**
 * Hero class represent a hero in the game.
 * A Hero is link to a player, and a player can have multiple hero.
 * This way, we can store a history of each hero played by any player
 * @author knprouvot
 *
 */
public class Hero {

	private static Logger LOGGER = Logger.getLogger(Hero.class.getName());
	
	private Integer battleId;
	private Integer hp;
	private Position position;
	private long lastAction;
	
	
	// A dummy Hero will avoid Null Pointer Exception
	public static final Hero emptyHero = new Hero(-1, -1, -1, -1);
	
	public Hero(Integer battleId, Integer x, Integer y, Integer hp) {
		this.battleId = battleId;
		position = new Position(x, y);
		this.hp = hp;
		lastAction = 0;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	public Integer getBattleId() {
		return this.battleId;
	}
	
	public long getLastAction() {
		return lastAction;
	}

	public void setLastAction(long lastAction) {
		this.lastAction = lastAction;
	}

	//TODO Move this logic inside a service
	public void move(long battleTime, Grid grid, EDirection direction) {
		if (battleTime - lastAction > Constants.ACTION_MINIMUM_INTERVAL) {
			position.move(battleTime, direction, grid);
			lastAction = battleTime;
		}
		else {
			LOGGER.log(Level.WARNING, "Hero in battle [{0}]", battleId);
		}
	}
}
