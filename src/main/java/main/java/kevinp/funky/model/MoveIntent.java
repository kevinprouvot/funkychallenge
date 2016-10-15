package main.java.kevinp.funky.model;

/**
 * MoveIntent or the intent of a movement.
 * The idea behind it was to store the intent of the player to move the hero.
 * As a player cannot move his hero more than once every second, the last intended
 * movement is store and executed as soon as it is available.
 * 
 * This implementation was assuming that the game would be build around a main loop.
 * For the moment, we will be updating hero's positions upon rest call.
 * @author knprouvot
 *
 */
public class MoveIntent {

	private EDirection direction;
	
	// Can be improved by using the previous MoveIntent, this way we can 
	// create an history of the move (Replay)
	private long lastExecutionTime;
	
	public MoveIntent() {
		lastExecutionTime = -10000;
	}
	
	public EDirection getDirecton() {
		return direction;
	}
	
	public long getLastExecuctionTime() {
		return lastExecutionTime;
	}
	
	/**
	 * This method is called after a movement has been done.
	 * It will nullify the direction and set the last execution time
	 * @param battleTime
	 */
	public void reset(long battleTime) {
		direction = null;
		lastExecutionTime = battleTime;
	}

	public void setDirection(EDirection direction) {
		this.direction = direction;
	}
}
