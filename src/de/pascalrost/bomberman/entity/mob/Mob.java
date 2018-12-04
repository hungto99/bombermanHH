package de.pascalrost.bomberman.entity.mob;

import de.pascalrost.bomberman.entity.Entity;
import de.pascalrost.bomberman.level.Level;

public class Mob extends Entity {
	
	protected int dir = 2;
	public boolean isAlive;
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!tileCollision(xa, ya)) {
			x += xa;
			y += ya;
		}	
	}
	
	public void shoot() {
		
	}
	
	public void update() {
		
	}
	
	public boolean tileCollision(int xa, int ya) {
		boolean solid = false;
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0, xx = x + 5, yy = y + 5;
			
		if(dir == 0) {
			x1 = (xx + xa) >> 6;
			y1 = (yy + ya) >> 6;
			x2 = (x + xa + 58) >> 6;
			y2 = (y + ya) >> 6;
		}else if(dir == 1) {
			x1 = (xx + xa + 58) >> 6;
			y1 = (yy + ya) >> 6;
			x2 = (xx + xa + 58) >> 6;
			y2 = (yy + ya + 58) >> 6;
		}else if(dir == 2) {
			x1 = (xx + xa) >> 6;
			y1 = (yy + ya + 58) >> 6;
			x2 = (xx + xa + 58) >> 6;
			y2 = (yy + ya + 58) >> 6;
		}else {
			x1 = (x + xa) >> 6;
			y1 = (yy + ya) >> 6;
			x2 = (x + xa) >> 6;
			y2 = (yy + ya + 58) >> 6;
		}
		
		if(level.getTile(x1, y1).solid() || level.getTile(x2, y2).solid()) solid = true;
		
		return solid;
	}

}
