package main.java.kevinp.funky.factory;

import java.util.Random;

import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.Hero;

public class HeroFactory {

	private static HeroFactory instance;
	
	private Random randomGenerator;
	
	private final static Integer MAX_HP = 10; 
	
	private HeroFactory() {
		randomGenerator = new Random();
	};
	
	public static HeroFactory getInstance() {
		if (instance == null) {
			instance = new HeroFactory();
		}
		return instance;
	}
	
	public Hero buildHero(Battle battle) {
		Integer randomX = randomGenerator.nextInt(battle.getGrid().getMaxX());
		Integer randomY = randomGenerator.nextInt(battle.getGrid().getMaxY());

		//TODO handle position already taken
		Hero hero = new Hero(battle.getId(), randomX, randomY, MAX_HP);
		return hero;
	}
	
}
