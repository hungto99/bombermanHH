package de.pascalrost.bomberman.level.tile;

import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;

public class FloorTile extends Tile {

	public FloorTile(Sprite sprite) {
		super(sprite);
		removed = false;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean isRemove() {
		return removed;
	}

}
