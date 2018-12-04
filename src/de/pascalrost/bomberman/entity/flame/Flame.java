package de.pascalrost.bomberman.entity.flame;


import de.pascalrost.bomberman.entity.Entity;
import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.level.Level;

public class Flame extends Entity {
	
	private int counter, anim, range;
	private int upBreakable = 0, downBreakable = 0, leftBreakable = 0, rightBreakable = 0;
	private int r = -1, l = -1, d = -1, u = -1;
	
	public Flame(int x, int y, int range, Sprite sprite, Level level) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		counter = 0;
		removed = false;
		this.level = level;
		this.range = range;
	}
	
	public void update() {
		counter++;
		
		if(anim < 7500) anim++;
		else anim = 0;
	}
	
	public void remove() {
		removed = true;
	}
	
	public void render(Screen screen) {
		if(anim % 25 > 20) {
			sprite = Sprite.flame_5;
		}else if(anim % 25 > 15) {
			sprite = Sprite.flame_4;
		}else if(anim % 25 > 10) {
			sprite = Sprite.flame_3;
		}else if(anim % 25 > 5) {
			sprite = Sprite.flame_2;
		}else {
			sprite = Sprite.flame_1;
		}
		
		for(int i = 1; i <= range; i++) {
			if(r == -1) {
				if(level.getTile((x >> 6) + i, y >> 6).solid()) r = i - 1;
				if(level.getTile((x >> 6) + i, y >> 6).breakable()) rightBreakable = i;
			}
			if(l == -1) {
				if(level.getTile((x >> 6) - i, y >> 6).solid()) l = i - 1;
				if(level.getTile((x >> 6) - i, y >> 6).breakable()) leftBreakable = i;
			}
			if(d == -1) {
				if(level.getTile(x >> 6, (y >> 6) + i).solid()) d = i - 1;
				if(level.getTile(x >> 6, (y >> 6) + i).breakable()) downBreakable = i;
			}
			if(u == -1) {
				if(level.getTile(x >> 6, (y >> 6) - i).solid()) u = i - 1;
				if(level.getTile(x >> 6, (y >> 6) - i).breakable()) upBreakable = i;
			}

			if(i == range) {
				if(r == -1) r = i;
				if(l == -1) l = i;
				if(d == -1) d = i;
				if(u == -1) u = i;
			}
		}
		
		for(int i = 1; i <= range; i++) {
			screen.renderFlame(x >> 6, y >> 6, sprite);
			
			if(r >= i || rightBreakable >= i) screen.renderFlame((x >> 6) + i, y >> 6, sprite);
			if(l >= i || leftBreakable >= i) screen.renderFlame((x >> 6) - i, y >> 6, sprite);
			if(d >= i || downBreakable >= i) screen.renderFlame(x >> 6, (y >> 6) + i, sprite);
			if(u >= i || upBreakable >= i) screen.renderFlame(x >> 6, (y >> 6) - i, sprite);
			
			if(counter == 25) {
				remove();
				if(rightBreakable == i) level.addBrokenBricks((x >> 6) + i, y >> 6);
				if(leftBreakable == i) level.addBrokenBricks((x >> 6) - i, y >> 6);
				if(downBreakable == i) level.addBrokenBricks(x >> 6, (y >> 6) + i);
				if(upBreakable == i) level.addBrokenBricks(x >> 6, (y >> 6) - i);
			}
			
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRange() {
		return range;
	}
	
	public boolean flameCollision(int x, int y) {
		for(int i = 1; i < range; i++) {
			if(x >= this.x >> 6 && x <= this.x >> 6 && y >= this.y >> 6 && y <= this.y >> 6) {
				return true;
			}
			if(r >= i || rightBreakable >= i) {
				if(x >= (this.x >> 6) + i && x <= (this.x >> 6) + i && y >= this.y >> 6 && y <= this.y >> 6) {
					return true;
				}
			}
			if(l >= i || leftBreakable >= i) {
				if(x >= (this.x >> 6) - i && x <= (this.x >> 6) - i && y >= this.y >> 6 && y <= this.y >> 6) {
					return true;
				}
			}
			if(d >= i || downBreakable >= i) {
				if(x >= this.x >> 6 && x <= this.x >> 6 && y >= (this.y >> 6) + i && y <= (this.y >> 6) + i) {
					return true;
				}
			}
			if(u >= i || upBreakable >= i) {
				if(x >= this.x >> 6 && x <= this.x >> 6 && y >= (this.y >> 6) - i && y <= (this.y >> 6) - i) {
					return true;
				}
			}
		}
		
		return false;
	}

}
