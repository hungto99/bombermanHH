package de.pascalrost.bomberman.level.first.tile;

import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.level.tile.Tile;

public class BorderTile extends Tile {

	public BorderTile(Sprite sprite) {
		super(sprite);
		removed = false;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
	
	public boolean solid() {
		return true;
	}
	
	public boolean isRemove() {
		return removed;
	}

}
