package main.java.kevinp.funky.model;

public class Hero {
	Integer battleId;
	Integer hp;
	Position position;
	
	// A dummy Hero will avoid Null Pointer Exception
	public static final Hero emptyHero = new Hero(-1, -1, -1, -1);
	
	public Hero(Integer battleId, Integer x, Integer y, Integer hp) {
		this.battleId = battleId;
		position = new Position(x, y);
		this.hp = hp;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	public Integer getBattleId() {
		return this.battleId;
	}
	
	//TODO Move this logic inside a service
	public void move(long battleTime, Grid grid, MoveIntent moveIntend) {
		position.move(battleTime, moveIntend, grid);
	}
}
