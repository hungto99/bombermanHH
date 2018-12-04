package de.pascalrost.bomberman.entity.creep;

import java.util.Random;

import de.pascalrost.bomberman.collision.Collision;
import de.pascalrost.bomberman.entity.mob.Mob;
import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.level.Level;


public class Creep extends Mob {
	
	private Random random;
	private int anim, xa, ya, creepDir, counter, flip;
	
	public Creep(int x, int y, Sprite sprite, Level level) {
		this.x = x << 6;
		this.y = y << 6;
		this.sprite = sprite;
		this.level = level;
		creepDir = 0;
		counter = 0;
		anim = 0;
		removed = false;
		flip = -1;
		random = new Random();
	}
	
	public void update() {	
		xa = 0;
		ya = 0;
		
		if(anim < 7500) anim++;
		else anim = 0;
		
		updateCreepDir();
		generateRandomDir();
		
		for(int i = 0; i < Level.flame.size(); i++) {
			if(Level.flame.get(i).flameCollision(x + 20 >> 6, y + 10 >> 6)) {
				removed = true;
				break;
			}
		}
		
		/*
		if(Collision.flameCollision(x + 20, y + 10, level)) {
			removed = true;
		}*/
	}
	
	private void generateRandomDir() {
		if(x % sprite.SIZE == 0 && y % sprite.SIZE == 0) {
			if(counter < 7500) counter++;
			else counter = 0;
		}
		
		if(tileCollision(xa, ya) || counter % 4 == 0) creepDir = random.nextInt(4);
		else move(xa, ya);
	}
	
	public void updateCreepDir() {
		if(creepDir == 0) ya -= 2;
		else if(creepDir == 1) xa += 2;
		else if(creepDir == 2) ya += 2;
		else xa -= 2;
	}
	
	public void render(Screen screen) {
		if(dir == 0) {
			flip = -1;
			if(anim % 60 > 50) {
				sprite = Sprite.creep_back_6;
			}else if(anim % 60 > 40) {
				sprite = Sprite.creep_back_5;
			}else if(anim % 60 > 30) {
				sprite = Sprite.creep_back_4;
			}else if(anim % 60 > 20) {
				sprite = Sprite.creep_back_3;
			}else if(anim % 60 > 10) {
				sprite = Sprite.creep_back_2;
			}else {
				sprite = Sprite.creep_back_1;
			}
		}else if(dir == 1) {
			flip = -1;
			if(anim % 60 > 50) {
				sprite = Sprite.creep_side_6;
			}else if(anim % 60 > 40) {
				sprite = Sprite.creep_side_5;
			}else if(anim % 60 > 30) {
				sprite = Sprite.creep_side_4;
			}else if(anim % 60 > 20) {
				sprite = Sprite.creep_side_3;
			}else if(anim % 60 > 10) {
				sprite = Sprite.creep_side_2;
			}else {
				sprite = Sprite.creep_side_1;
			}
		}else if(dir == 2) {
			flip = -1;
			if(anim % 60 > 50) {
				sprite = Sprite.creep_forward_6;
			}else if(anim % 60 > 40) {
				sprite = Sprite.creep_forward_5;
			}else if(anim % 60 > 30) {
				sprite = Sprite.creep_forward_4;
			}else if(anim % 60 > 20) {
				sprite = Sprite.creep_forward_3;
			}else if(anim % 60 > 10) {
				sprite = Sprite.creep_forward_2;
			}else {
				sprite = Sprite.creep_forward_1;
			}
		}else {
			flip = 3;
			if(anim % 60 > 50) {
				sprite = Sprite.creep_side_6;
			}else if(anim % 60 > 40) {
				sprite = Sprite.creep_side_5;
			}else if(anim % 60 > 30) {
				sprite = Sprite.creep_side_4;
			}else if(anim % 60 > 20) {
				sprite = Sprite.creep_side_3;
			}else if(anim % 60 > 10) {
				sprite = Sprite.creep_side_2;
			}else {
				sprite = Sprite.creep_side_1;
			}
		}
		
		screen.renderCreeps(x >> 64, y >> 64, sprite, flip);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
