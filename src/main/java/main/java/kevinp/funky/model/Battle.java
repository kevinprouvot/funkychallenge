package main.java.kevinp.funky.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.kevinp.funky.factory.HeroFactory;

public class Battle {

	private static Logger LOGGER = Logger.getLogger(Battle.class.getName());
	
	private Integer id;
	private EBattleStatus status;
	private long startTime;
	private long endTime;
	private List<Player> players;
	private Grid grid;

	//TODO move this constants in a propertie file
	private static final Integer gridMaxX = 100;
	private static final Integer gridMaxY = 100;
	private static final Integer maxPlayers = 2;

	public Battle(Integer id, Player player) {
		this.id = id;
		status = EBattleStatus.WAITING;
		startTime = 0;
		endTime = 0;
		players = new ArrayList<>();
		players.add(player);
	}

	/**
	 * Try to Add a player
	 * Won't add a player if he is already in the Battle
	 * @param player
	 */
	//TODO Move this logic inside a service
	public void addPlayer(Player player) {
		if (EBattleStatus.WAITING.equals(status)) {
			if(containPlayerWithIp(player.getIp())) {
				LOGGER.log(Level.WARNING, "Player [{0}] is already is Battle [{1}]", new Object[] {player.getIp(), id});
			}
			else {
				players.add(player);
				LOGGER.log(Level.INFO, "Player [{0}] joined Battle [{1}]", new Object[] {player.getIp(), id});
				if (players.size() == maxPlayers) {
					startBattle();
				}
			}
		}
		else {
			LOGGER.log(Level.WARNING, "Player [{0}] fail to join Battle [{1}]. Battle isn't WAITING", new Object[] {player.getIp(), id});
		}
	}

	private void startBattle() {
		LOGGER.log(Level.INFO, "Battle [{0}] started", id);
		
		startTime = Calendar.getInstance().getTimeInMillis();
		grid = new Grid(gridMaxX, gridMaxY);
		status = EBattleStatus.ACTIVE;
		for (Player player : players) {
			player.assignHero(HeroFactory.getInstance().buildHero(this));
		}
	}

	/**
	 * The time will be the difference between current time and startTime
	 * 
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
	 * Calculate game duration. value will be negative if game is still going
	 * 
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
