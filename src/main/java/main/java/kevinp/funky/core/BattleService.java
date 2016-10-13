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

public class BattleService {

	private static Logger LOGGER = Logger.getLogger(BattleService.class.getName());

	private volatile Battle queuedBattle;

	private volatile BattleList battlePool;

	//TODO Add History
	private volatile BattleList battleHistory;

	private static BattleService instance;

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

	public synchronized void join(String ip) {
		if (!battlePool.containPlayerWithIp(ip)) {
			Player player = getPlayer(ip);
			addPlayerToQueuedBattle(player);
		} else {
			LOGGER.log(Level.WARNING, "Player [{0}] attempt to join but is already in game", ip);
		}
	}

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
