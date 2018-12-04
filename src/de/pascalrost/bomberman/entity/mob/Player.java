package de.pascalrost.bomberman.entity.mob;

import de.pascalrost.bomberman.entity.bomb.Bomb;
import de.pascalrost.bomberman.entity.flame.Flame;
import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.input.Keyboard;
import de.pascalrost.bomberman.level.Level;

public class Player extends Mob {
	
	private Keyboard input;
	private int anim, bombRange;
	private boolean walking;
	private int speed;
	private int flip;
	private Bomb bomb;
	private int bombsInHand;
	private boolean isDying;
	
	public Player(int x, int y, Keyboard input, Level level) {
		this.x = x << 6;
		this.y = y << 6;
		this.input = input;
		anim = 0;
		walking = false;
		speed = 2;
		flip = -1;
		this.level = level;
		sprite = Sprite.player_forward_1;
		bombsInHand = 3;
		isAlive = true;
		bombRange = 5;
		isDying = false;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		
		if(anim < 7500) anim++;
		else anim = 0;
		
		if(input.up) ya -= speed;
		if(input.down) ya += speed;
		if(input.left) xa -= speed;
		if(input.right) xa += speed;
		if(input.space) {
			if(bombsInHand > 0 && !checkSpot()) {
				bombsInHand--;
				plantBomb();
			}
		}
	
		if(xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		}else {
			walking = false;
		}
		
		clear();
		
		if(creepCollision()) isAlive = false;
		
		for(int i = 0; i < Level.flame.size(); i++) {
			if(Level.flame.get(i).flameCollision(x >> 6, y >> 6)) {
				isAlive = false;
				break;
			}
		}
	}
	
	private boolean creepCollision() {
		for(int i = 0; i < Level.creep.size(); i++) {
			if(x + 20 >= Level.creep.get(i).getX() && x + 20 < (Level.creep.get(i).getX() + 64) && y + 20 >= Level.creep.get(i).getY() & y + 20 < (Level.creep.get(i).getY() + 64)) return true;
			if((x - 20 + 64) >= Level.creep.get(i).getX() && (x - 20 + 64) < (Level.creep.get(i).getX() + 64) && y + 20 >= Level.creep.get(i).getY() & y + 20 < (Level.creep.get(i).getY() + 64)) return true;
			if(x + 20 >= Level.creep.get(i).getX() && x + 20 < (Level.creep.get(i).getX() + 64) && (y - 20 + 64) >= Level.creep.get(i).getY() & (y - 20 + 64) < (Level.creep.get(i).getY() + 64)) return true;
			if((x - 20 + 64) >= Level.creep.get(i).getX() && (x - 20 + 64) < (Level.creep.get(i).getX() + 64) && (y - 20 + 64) >= Level.creep.get(i).getY() & (y - 20 + 64) < (Level.creep.get(i).getY() + 64)) return true;
		}
		return false;
	}
	
	public boolean checkSpot() {
		for(int i = 0; i < Level.bomb.size(); i++) {
			if((x) >= Level.bomb.get(i).getX() && (x) < (Level.bomb.get(i).getX() + 64) && (y + 10) >= level.bomb.get(i).getY() && (y + 10) < (level.bomb.get(i).getY() + 64)) return true;
			if((x + 64) >= Level.bomb.get(i).getX() && (x + 64) < (Level.bomb.get(i).getX() + 64) && (y + 10) >= level.bomb.get(i).getY() && (y + 10) < (level.bomb.get(i).getY() + 64)) return true;
			if((x) >= Level.bomb.get(i).getX() && (x) < (Level.bomb.get(i).getX() + 64) && (y + 10 + 64) >= level.bomb.get(i).getY() && (y + 10 + 64) < (level.bomb.get(i).getY() + 64)) return true;
			if((x + 64) >= Level.bomb.get(i).getX() && (x + 64) < (Level.bomb.get(i).getX() + 64) && (y + 10 + 64) >= level.bomb.get(i).getY() && (y + 10 + 64) < (level.bomb.get(i).getY() + 64)) return true;
		}
		
		return false;
	}
	
	private void plantBomb() {
		bomb = new Bomb(((x + 20) >> 6) << 6, ((y + 20) >> 6) << 6, Sprite.bomb_1, level, bombRange);
		level.addBomb(bomb);
	}
	
	private void clear() {
		for(int i = 0; i < Level.bomb.size(); i++) {
			if(Level.bomb.get(i).removed) {
				Level.bomb.remove(i);
				bombsInHand++;
			}
		}
		
		for(int i = 0; i < Level.flame.size(); i++) {
			if(Level.flame.get(i).removed) {
				Level.flame.remove(i);
			}
		}
	}
	
	public void render(Screen screen) {
		if(dir == 0) {
			flip = -1;
			sprite = Sprite.player_back_1;
				if(walking) {
					if(anim % 40 > 35) {
						if(isDying) isAlive = false;
						sprite = Sprite.player_back_8;
					}else if(anim % 40 > 30) {
						sprite = Sprite.player_back_7;
					}else if(anim % 40 > 25) {
						sprite = Sprite.player_back_6;
					}else if(anim % 40 > 20) {
						sprite = Sprite.player_back_5;
					}else if(anim % 40 > 15) {
						sprite = Sprite.player_back_4;
					}else if(anim % 40 > 10) {
						sprite = Sprite.player_back_3;
					}else {
						sprite = Sprite.player_back_2;
					}
				}
		}else if(dir == 1) {
			flip = -1;
			sprite = Sprite.player_side_1;
			if(walking) {
				if(anim % 40 > 35) {
					if(isDying) isAlive = false;
					sprite = Sprite.player_side_8;
				}else if(anim % 40 > 30) {
					sprite = Sprite.player_side_7;
				}else if(anim % 40 > 25) {
					sprite = Sprite.player_side_6;
				}else if(anim % 40 > 20) {
					sprite = Sprite.player_side_5;
				}else if(anim % 40 > 15) {
					sprite = Sprite.player_side_4;
				}else if(anim % 40 > 10) {
					sprite = Sprite.player_side_3;
				}else {
					sprite = Sprite.player_side_2;
				}
			}
		}else if(dir == 2) {
			flip = -1;
			sprite = Sprite.player_forward_1;
			if(walking) {
				if(anim % 40 > 35) {
					if(isDying) isAlive = false;
					sprite = Sprite.player_forward_8;
				}else if(anim % 40 > 30) {
					sprite = Sprite.player_forward_7;
				}else if(anim % 40 > 25) {
					sprite = Sprite.player_forward_6;
				}else if(anim % 40 > 20) {
					sprite = Sprite.player_forward_5;
				}else if(anim % 40 > 15) {
					sprite = Sprite.player_forward_4;
				}else if(anim % 40 > 10) {
					sprite = Sprite.player_forward_3;
				}else {
					sprite = Sprite.player_forward_2;
				}
			}
		}else {
			flip = 3;
			sprite = Sprite.player_side_1;
			if(walking) {
				if(anim % 40 > 35) {
					if(isDying) isAlive = false;
					sprite = Sprite.player_side_8;
				}else if(anim % 40 > 30) {
					sprite = Sprite.player_side_7;
				}else if(anim % 40 > 25) {
					sprite = Sprite.player_side_6;
				}else if(anim % 40 > 20) {
					sprite = Sprite.player_side_5;
				}else if(anim % 40 > 15) {
					sprite = Sprite.player_side_4;
				}else if(anim % 40 > 10) {
					sprite = Sprite.player_side_3;
				}else {
					sprite = Sprite.player_side_2;
				}
			}
		}
		
		screen.renderPlayer(x >> sprite.SIZE, y >> sprite.SIZE, sprite, flip);
	}

}
