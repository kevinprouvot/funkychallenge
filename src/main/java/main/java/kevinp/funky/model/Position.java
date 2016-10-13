package main.java.kevinp.funky.model;

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
	
	//TODO move this logic inside a service
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
