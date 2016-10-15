package main.java.kevinp.funky.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.Player;

public class BattleFactory {

private static BattleFactory instance;
	
	private static Logger LOGGER = Logger.getLogger(BattleFactory.class.getName());

	private Integer counter;

	private BattleFactory() {
		LOGGER.info("BattleFactory Initialisation");
		counter = 0;
	};
	
	
	public static BattleFactory getInstance() {
		if (instance == null) {
			instance = new BattleFactory();
		}
		return instance;
	}
	
	/**
	 * buildBattle method is in charge on battle creation.
	 * It will be responsible for generating his id.
	 * For the moment, the id is created from a counter, but it can be improved
	 */
	public synchronized Battle buildBattle(Player player) {
		LOGGER.log(Level.FINE, "Building battle [{0}]", counter);
		Battle battle = new Battle(counter, player);
		counter++;
		return battle;
	}
}
