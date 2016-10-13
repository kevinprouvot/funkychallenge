package main.java.kevinp.funky.model;

public class Player {

	private String ip;
	private Hero hero;
	private MoveIntent moveIntent;
	
	public Player(String ip) {
		this.ip = ip;
		hero = Hero.emptyHero;
		moveIntent = new MoveIntent();
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
	
	public void setMoveIntent(EDirection direction) {
		this.moveIntent.setDirection(direction);
	}
	
	//TODO move this logic inside a service
	public void move(long battleTime, Grid grid) {
		hero.move(battleTime, grid, this.moveIntent);
		
		moveIntent.reset(battleTime);
	}
	
	public Integer getBattleId() {
		return hero.getBattleId();
	}
}
