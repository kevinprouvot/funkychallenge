package main.java.kevinp.funky.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A battle list object which store battles.
 * The idea is to gather all methods related to Battle manipulations.
 * @author knprouvot
 *
 */
public class BattleList {

	private List<Battle> battleList;
	
	public BattleList() {
		battleList = Collections.synchronizedList(new ArrayList<>());
	}
	
	public boolean containPlayerWithIp(String targetIp) {
		return battleList.stream().anyMatch(battle -> battle.containPlayerWithIp(targetIp));
	}
	
	public void addBattle(Battle battle) {
		battleList.add(battle);
	}
}
