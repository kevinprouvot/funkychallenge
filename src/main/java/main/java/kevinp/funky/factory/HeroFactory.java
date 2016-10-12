package main.java.kevinp.funky.factory;

import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.Hero;

public class HeroFactory {

	private static HeroFactory instance;
	
	private final static Integer MAX_HP = 10; 
	
	private HeroFactory() {};
	
	public static HeroFactory getInstance() {
		if (instance == null) {
			instance = new HeroFactory();
		}
		return instance;
	}
	
	public Hero buildHero(Battle battle) {
		Hero hero = new Hero(battle.getId(), 0, 0, MAX_HP);
		//TODO: handle generate random position
		return hero;
	}
}
