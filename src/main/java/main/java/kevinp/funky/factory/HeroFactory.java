package main.java.kevinp.funky.factory;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.Hero;

public class HeroFactory {
	
	private static Logger LOGGER = Logger.getLogger(HeroFactory.class.getName());

	private static HeroFactory instance;
	
	private Random randomGenerator;
	
	//TODO move this constant in a propertie file
	private final static Integer MAX_HP = 10; 
	
	private HeroFactory() {
		LOGGER.info("HeroFactory Initialisation");
		randomGenerator = new Random();
	};
	
	public static HeroFactory getInstance() {
		if (instance == null) {
			instance = new HeroFactory();
		}
		return instance;
	}
	
	public Hero buildHero(Battle battle) {
		LOGGER.log(Level.FINE, "Building Hero for Battle [{0}]", battle.getId());
		Integer randomX = randomGenerator.nextInt(battle.getGrid().getMaxX());
		Integer randomY = randomGenerator.nextInt(battle.getGrid().getMaxY());

		//TODO handle position already taken
		Hero hero = new Hero(battle.getId(), randomX, randomY, MAX_HP);
		return hero;
	}
	
}
