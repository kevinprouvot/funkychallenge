package main.java.kevinp.funky.model;

import java.util.ArrayList;
import java.util.List;

public class BattleList {

	private List<Battle> battleList;
	
	public BattleList() {
		battleList = new ArrayList<>();
	}
	
	public boolean containPlayerWithIp(String targetIp) {
		return battleList.stream().anyMatch(battle -> battle.containPlayerWithIp(targetIp));
	}
	
	public void addBattle(Battle battle) {
		battleList.add(battle);
	}
}
