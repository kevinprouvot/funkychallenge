package main.java.kevinp.funky.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Battle {
	private Integer id;
	private EBattleStatus status;
	private long startTime;
	private long endTime;
	private List<Hero> heroes;
	private Grid grid;
	
	private static final Integer gridMaxX = 100;
	private static final Integer gridMaxY = 100;
	private static final Integer maxHero = 2;
	
	public Battle(Integer id, Hero hero) {
		status = EBattleStatus.WAITING;
		startTime = 0;
		endTime = 0;
		heroes = new ArrayList<>();
		heroes.add(hero);
	}
	
	public void addHero(Hero hero) {
		if (EBattleStatus.WAITING.equals(status)) {
			heroes.add(hero);
			if (heroes.size() == maxHero) {
				status = EBattleStatus.ACTIVE;
			}
		}
	}
	
	public void startBattle() {
		startTime = Calendar.getInstance().getTimeInMillis();
		grid = new Grid(gridMaxX, gridMaxY);
		status = EBattleStatus.ACTIVE;
	}
	
	/**
	 * The time will be the difference between current time and startTime
	 * @return elapsed time in this battle
	 */
	public long getBattleTime() {
		return Calendar.getInstance().getTimeInMillis() - startTime;
	}
	
	public void terminateBattle() {
		status = EBattleStatus.TERMINATED;
		endTime = Calendar.getInstance().getTimeInMillis();
	}
	
	public Integer getId() {
		return id;
	}
	
	public EBattleStatus getBattleStatus() {
		return status;
	}
	
	/**
	 * Calculate game duration.
	 * value will be negative if game is still going
	 * @return game duration
	 */
	public long getBattleDuration() {
		return endTime - startTime;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
}
