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
	
	public Battle(Integer id) {
		status = EBattleStatus.WAITING;
		startTime = 0;
		endTime = 0;
		heroes = new ArrayList<>();
	}
	
	public void startBattle() {
		status = EBattleStatus.ACTIVE;
		startTime = Calendar.getInstance().getTimeInMillis();
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
}
