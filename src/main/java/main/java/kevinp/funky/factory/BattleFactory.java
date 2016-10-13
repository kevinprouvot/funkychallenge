package main.java.kevinp.funky.factory;

import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.Player;

public class BattleFactory {

private static BattleFactory instance;
	
	private Integer counter;

	private BattleFactory() {
		counter = 0;
	};
	
	
	public static BattleFactory getInstance() {
		if (instance == null) {
			instance = new BattleFactory();
		}
		return instance;
	}
	
	public synchronized Battle buildBattle(Player player) {
		Battle battle = new Battle(counter, player);
		counter++;
		return battle;
	}
}
