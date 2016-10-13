package main.java.kevinp.funky.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.kevinp.funky.factory.BattleFactory;
import main.java.kevinp.funky.model.Battle;
import main.java.kevinp.funky.model.BattleList;
import main.java.kevinp.funky.model.Player;

public class BattleService {

	private volatile Battle queuedBattle;

	private volatile BattleList battlePool;

	private volatile BattleList battleHistory;

	private static BattleService instance;

	private static List<Player> players;

	private BattleService() {
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
		if (!battlePool.containPlayerWithIp(ip))
		{
			Player player = getPlayer(ip);
			tryAddPlayer(player);
		}
	}

	private Player getPlayer(String ip) {
		Optional<Player> currentPlayer = players.stream().filter(player -> player.getIp().equals(ip)).findFirst();
		if (!currentPlayer.isPresent()) {
			Player player = new Player(ip);
			players.add(player);
			return player;
		}
		else {
			return currentPlayer.get();
		}
	}

	private void tryAddPlayer(Player player) {
		if (queuedBattle == null) {
			queuedBattle = BattleFactory.getInstance().buildBattle(player);
		} else {
			queuedBattle.addHero(player);
			if (queuedBattle.isActive()) {
				battlePool.addBattle(queuedBattle);
				queuedBattle = null;
			}
		}
	}
}
