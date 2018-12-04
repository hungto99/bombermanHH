package de.pascalrost.bomberman.collision;

import de.pascalrost.bomberman.level.Level;

public class Collision {
	
	public static boolean flameCollision(int x, int y, Level level) {
		boolean up = true, down = true, right = true, left = true;
		
		for(int i = 0; i < Level.flame.size(); i++) {
			for(int f = 1; f <= Level.flame.get(i).getRange(); f++)	 {
				
				int flameX0 = Level.flame.get(i).getX();
				int flameY0 = Level.flame.get(i).getY();
				int flameX1 = Level.flame.get(i).getX() + (64 * f);
				int flameY1 = Level.flame.get(i).getY();
				int flameX2 = Level.flame.get(i).getX() - (64 * f);
				int flameY2 = Level.flame.get(i).getY();
				int flameX3 = Level.flame.get(i).getX();
				int flameY3 = Level.flame.get(i).getY() + (64 * f);
				int flameX4 = Level.flame.get(i).getX();
				int flameY4 = Level.flame.get(i).getY() - (64 * f);
				
				if(level.getTile(flameX1 >> 6, flameY1 >> 6).solid()) right = false;
				if(level.getTile(flameX2 >> 6, flameY2 >> 6).solid()) left = false;
				if(level.getTile(flameX3 >> 6, flameY3 >> 6).solid()) down = false;
				if(level.getTile(flameX4 >> 6, flameY4 >> 6).solid()) up = false;
				
				//center
				if(((x + 1) >= flameX0 && (x + 1) <= (flameX0 + 64)) && ((y + 1) >= flameY0 && (y + 1) <= (flameY0 + 64))) {
					return true;	
				}
				if(((x + 63) >= flameX0 && (x + 63) <= (flameX0 + 64)) && ((y + 1) >= flameY0 && (y + 1) <= (flameY0 + 64))) {
					return true;	
				}
				if(((x + 1) >= flameX0 && (x + 1) <= (flameX0 + 64)) && ((y + 63) >= flameY0 && (y + 63) <= (flameY0 + 64))) {
					return true;	
				}	
				if(((x + 63) >= flameX0 && (x + 63) <= (flameX0 + 64)) && ((y + 63) >= flameY0 && (y + 63) <= (flameY0 + 64))) {
					return true;	
				}
				
				//right side
				if(right) {
					if(((x + 1) >= flameX1 && (x + 1) <= (flameX1 + 64)) && ((y + 1) >= flameY1 && (y + 1) <= (flameY1 + 64))) {
						return true;	
					}
					if(((x + 63) >= flameX1 && (x + 63) <= (flameX1 + 64)) && ((y + 1) >= flameY1 && (y + 1) <= (flameY1 + 64))) {
						return true;	
					}
					if(((x + 1) >= flameX1 && (x + 1) <= (flameX1 + 64)) && ((y + 63) >= flameY1 && (y + 63) <= (flameY1 + 64))) {
						return true;	
					}	
					if(((x + 63) >= flameX1 && (x + 63) <= (flameX1 + 64)) && ((y + 63) >= flameY1 && (y + 63) <= (flameY1 + 64))) {
						return true;	
					}
				}
				
				
				//left side
				if(left) {
					if(((x + 1) >= flameX2 && (x + 1) <= (flameX2 + 64)) && ((y + 1) >= flameY2 && (y + 1) <= (flameY2 + 64))) {
						return true;	
					}
					if(((x + 63) >= flameX2 && (x + 63) <= (flameX2 + 64)) && ((y + 1) >= flameY2 && (y + 1) <= (flameY2 + 64))) {
						return true;	
					}
					if(((x + 1) >= flameX2 && (x + 1) <= (flameX2 + 64)) && ((y + 63) >= flameY2 && (y + 63) <= (flameY2 + 64))) {
						return true;	
					}	
					if(((x + 63) >= flameX2 && (x + 63) <= (flameX2 + 64)) && ((y + 63) >= flameY2 && (y + 63) <= (flameY2 + 64))) {
						return true;	
					}
				}
				
				
				//down
				if(down) {
					if(((x + 1) >= flameX3 && (x + 1) <= (flameX3 + 64)) && ((y + 1) >= flameY3 && (y + 1) <= (flameY3 + 64))) {
						return true;	
					}
					if(((x + 63) >= flameX3 && (x + 63) <= (flameX3 + 64)) && ((y + 1) >= flameY3 && (y + 1) <= (flameY3 + 64))) {
						return true;	
					}
					if(((x + 1) >= flameX3 && (x + 1) <= (flameX3 + 64)) && ((y + 63) >= flameY3 && (y + 63) <= (flameY3 + 64))) {
						return true;	
					}	
					if(((x + 63) >= flameX3 && (x + 63) <= (flameX3 + 64)) && ((y + 63) >= flameY3 && (y + 63) <= (flameY3 + 64))) {
						return true;	
					}
				}
				
				
				//up
				if(up) {
					if(((x + 1) >= flameX4 && (x + 1) <= (flameX4 + 64)) && ((y + 1) >= flameY4 && (y + 1) <= (flameY4 + 64))) {
						return true;	
					}
					if(((x + 63) >= flameX4 && (x + 63) <= (flameX4 + 64)) && ((y + 1) >= flameY4 && (y + 1) <= (flameY4 + 64))) {
						return true;	
					}
					if(((x + 1) >= flameX4 && (x + 1) <= (flameX4 + 64)) && ((y + 63) >= flameY4 && (y + 63) <= (flameY4 + 64))) {
						return true;	
					}	
					if(((x + 63) >= flameX4 && (x + 63) <= (flameX4 + 64)) && ((y + 63) >= flameY4 && (y + 63) <= (flameY4 + 64))) {
						return true;	
					}
				}
			}
		}
		
		return false;
	}

}
