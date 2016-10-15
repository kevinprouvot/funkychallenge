package main.java.kevinp.funky.model;

/**
 * A Position store coordinates of a hero.
 * It can be used in the future to store position of anything else.
 * 
 * At the moment, a Position is responsible of his movement.
 * Logic around movement might be moved in a service in the future.
 * @author knprouvot
 *
 */
public class Position {

	private Integer x;
	private Integer y;
	
	public Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public void move(long battleTime, MoveIntent moveIntend, Grid grid) {
		if (moveIntend.getDirecton() != null) {
			switch (moveIntend.getDirecton()) {
			case LEFT:
				if (x - 1 >= 0) {
					x--;
				}
				break;
				
			case RIGHT:
				if (x + 1 < grid.getMaxX()) {
					x++;
				}
				break;
				
			case UP:
				if (y - 1 >= 0) {
					y--;
				}
				break;
				
			case DOWN:
				if (y + 1 < grid.getMaxY()) {
					y++;
				}
				break;
			}
			moveIntend.reset(battleTime);
		}
	}
}
