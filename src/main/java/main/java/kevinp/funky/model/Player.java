package main.java.kevinp.funky.model;

/**
 * A Player represent anyone who is playing the game.
 * Each player is identified by an IP address.
 * @author knprouvot
 *
 */
public class Player {

	private String ip;
	private Hero hero;
	
	public Player(String ip) {
		this.ip = ip;
		hero = Hero.emptyHero;
	}
	
	public void assignHero(Hero hero) {
		this.hero = hero;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public Integer getHeroHp() {
		return hero.getHp();
	}
	
	public Position getHeroPosition() {
		return hero.getPosition();
	}
	
	public void move(long battleTime, Grid grid, EDirection direction) {
		hero.move(battleTime, grid, direction);
	}
	
	public Integer getBattleId() {
		return hero.getBattleId();
	}
}
