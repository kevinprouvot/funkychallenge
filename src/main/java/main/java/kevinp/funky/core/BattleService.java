package main.java.kevinp.funky.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.kevinp.funky.factory.BattleFactory;
import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.BattleList;
import main.java.kevinp.funky.model.Player;

/**
 * BattleService is responsible of the interactions between Players and Battles
 * It also handle pools of battles.
 * @author knprouvot
 *
 */
public class BattleService {

	private static Logger LOGGER = Logger.getLogger(BattleService.class.getName());

	/**QueuedBattle awaits for players to join*/
	private volatile Battle queuedBattle;

	/** BattlePool store every battle currently active**/
	private volatile BattleList battlePool;

	//TODO Add History
	/** Once a battle is finished, it is stored in the battleHistory list,
	 * In the future, persisting battles in the database might be a better solution*/
	private volatile BattleList battleHistory;

	private static BattleService instance;

	/** List of all the players. This list might be persisted in the database in the future */
	private static List<Player> players;

	private BattleService() {
		LOGGER.info("BattleService initialisation");
		battlePool = new BattleList();
		battleHistory = new BattleList();
		players = new ArrayList<>();
	}

	public static synchronized BattleService getInstance() {
		if (instance == null) {
			instance = new BattleService();
		}
		return instance;
	}

	/**
	 * Join method will check that the player is not in an active game already.
	 * If not, it will add the player to the queued battle
	 */
	public synchronized void join(String ip) {
		if (!battlePool.containPlayerWithIp(ip)) {
			Player player = getPlayer(ip);
			addPlayerToQueuedBattle(player);
		} else {
			LOGGER.log(Level.WARNING, "Player [{0}] attempt to join but is already in game", ip);
		}
	}

	/**
	 * getPlayer method will try to fetch a player from the player list.
	 * If no player exists already using this ip, il will create a new one
	 */
	private Player getPlayer(String ip) {
		LOGGER.log(Level.FINE, "Loading information about player [{0}]", ip);
		Optional<Player> currentPlayer = players.stream().filter(player -> player.getIp().equals(ip)).findFirst();
		if (!currentPlayer.isPresent()) {
			LOGGER.log(Level.FINE, "Creating Player [{0}]", ip);
			Player player = new Player(ip);
			players.add(player);
			return player;
		} else {
			LOGGER.log(Level.FINE, "Player [{0}] found", ip);
			return currentPlayer.get();
		}
	}

	/**
	 * This method will checked that a queuedBattle is instantiated in order to add the player to it.
	 * If no queudBattle is instantiated, it will create a new one with the player on it.
	 * @param player
	 */
	private void addPlayerToQueuedBattle(Player player) {
		LOGGER.log(Level.FINE, "Queuing Player [{0}] ", player.getIp());
		if (queuedBattle == null) {
			queuedBattle = BattleFactory.getInstance().buildBattle(player);
		} else {
			queuedBattle.addPlayer(player);
			if (queuedBattle.isActive()) {
				LOGGER.log(Level.FINE, "Adding Battle [{0}] to battlePool", queuedBattle.getId());
				battlePool.addBattle(queuedBattle);
				queuedBattle = null;
			}
		}
	}
}
