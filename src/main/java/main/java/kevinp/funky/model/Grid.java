package main.java.kevinp.funky.model;

/**
 * Grid store information about the battlefield.
 * For the moment, only size of the grid is store.
 * 
 * In the future, information of obstacles can be store on each tiles of the grid.
 * @author knprouvot
 *
 */
public class Grid {
	private Integer maxX;
	private Integer maxY;
	
	public Grid(Integer maxX, Integer maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public Integer getMaxX() {
		return maxX;
	}

	public void setMaxX(Integer maxX) {
		this.maxX = maxX;
	}

	public Integer getMaxY() {
		return maxY;
	}

	public void setMaxY(Integer maxY) {
		this.maxY = maxY;
	}
}
