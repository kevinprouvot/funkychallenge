package main.java.kevinp.funky.model;

public class MoveIntent {

	private EDirection direction;
	
	// Can be improved by using the previous MoveIntent, this way we can 
	//create an history of the move (Replay)
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
