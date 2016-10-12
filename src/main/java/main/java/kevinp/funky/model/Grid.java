package main.java.kevinp.funky.model;

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
