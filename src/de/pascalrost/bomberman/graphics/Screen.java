package de.pascalrost.bomberman.graphics;

import de.pascalrost.bomberman.font.Font;
import de.pascalrost.bomberman.level.tile.Tile;


public class Screen {
	
	public int width;
	public int height;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; 
				if(xa < 0) xa = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderText(int xp, int yp, Font font) {
		for(int y = 0; y < 32; y++) {
			int ya = y + yp;
			for(int x = 0; x < font.SIZE; x++) {
				int xa = x + xp;
				int col = font.pixels[x + y * font.SIZE];
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			int ys = y;
			if(flip == 0 || flip == 2) ys = (sprite.SIZE - 1) - ys;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int xs = x;
				if(flip == 1 || flip == 3) xs = (sprite.SIZE - 1) - xs;
				if(x < -sprite.SIZE || x >= width || y < 0 || y >= height) break; 
				if(x < 0) x = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if(col == 0xFF870087) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderBomb(int xp, int yp, Sprite sprite) {
		xp = xp << 6;
		yp = yp << 6;
		
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col == 0xFF870087) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderFlame(int xp, int yp, Sprite sprite) {
		xp = xp << 6;
		yp = yp << 6;
		
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col == 0xFF870087) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderCreeps(int xp, int yp, Sprite sprite, int flip) {
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = yp + y;
			int ys = y;
			if(flip == 0 || flip == 2) ys = (sprite.SIZE - 1) - ys;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = xp + x;
				int xs = x;
				if(flip == 1 || flip == 3) xs = (sprite.SIZE - 1) - xs;
				if(x < -sprite.SIZE || x >= width || y < 0 || y >= height) break; 
				if(x < 0) x = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if(col == 0xFF870087) continue;
				pixels[xa + ya * width] = col;
			}
		}
	}

}
