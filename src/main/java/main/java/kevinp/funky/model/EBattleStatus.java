package main.java.kevinp.funky.model;

/**
 * Battle can have 3 statuses.
 * WAITING : number of player required to launch the game is not reached
 * ACTIVE : battle is on going
 * TERMINATED : battle is over
 * 
 * Note: A Battle is created when a player join and there is no available battle.
 * This means that a battle cannot be empty.
 * It can be improved by adding more statuses if required
 * 
 * @author knprouvot
 *
 */
public enum EBattleStatus {
	WAITING,
	ACTIVE,
	TERMINATED
}
