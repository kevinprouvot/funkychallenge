package main.java.kevinp.funky.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.kevinp.funky.factory.HeroFactory;

public class Battle {
	private Integer id;
	private EBattleStatus status;
	private long startTime;
	private long endTime;
	private List<Player> players;
	private Grid grid;
	
	private static final Integer gridMaxX = 100;
	private static final Integer gridMaxY = 100;
	private static final Integer maxPlayers = 2;
	
	public Battle(Integer id, Player player) {
		status = EBattleStatus.WAITING;
		startTime = 0;
		endTime = 0;
		players = new ArrayList<>();
		players.add(player);
	}
	
	public void addHero(Player player) {
		if (EBattleStatus.WAITING.equals(status)) {
			players.add(player);
			if (players.size() == maxPlayers) {
				startBattle();
			}
		}
	}
	
	private void startBattle() {
		startTime = Calendar.getInstance().getTimeInMillis();
		grid = new Grid(gridMaxX, gridMaxY);
		status = EBattleStatus.ACTIVE;
		for (Player player : players) {
			player.assignHero(HeroFactory.getInstance().buildHero(this));
		}
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
	
	public Boolean containPlayerWithIp(String targetIp) {
		return players.stream().anyMatch(player -> player.getIp().equals(targetIp));
	}
	
	public boolean isActive() {
		return EBattleStatus.ACTIVE.equals(status);
	}
}
