package de.pascalrost.bomberman.level.tile;

import de.pascalrost.bomberman.graphics.Screen;
import de.pascalrost.bomberman.graphics.Sprite;
import de.pascalrost.bomberman.level.first.tile.BorderTile;

public class Tile {
	
	public Sprite sprite;
	protected boolean removed;
	
	public static Tile border_tile = new BorderTile(Sprite.border_sprite);
	public static Tile brick_tile = new BrickTile(Sprite.brick_sprite);
	public static Tile floor_tile = new FloorTile(Sprite.floor_sprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public boolean solid() {
		return false;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean breakable() {
		return false;
	}
	
	public void remove() {
		
	}
	
	public boolean isRemove() {
		return removed;
	}
	
}
