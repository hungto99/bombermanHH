package de.pascalrost.bomberman.level.tile;

import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;

public class BrickTile extends Tile {

	public BrickTile(Sprite sprite) {
		super(sprite);
		removed = false;
	}
	
	public boolean solid() {
		return true;
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean breakable() {
		return true;
	}
	
	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 6, y << 6, this);
	}

}
